    meta-gw96 layer will create "core-image-minimal" running on the Gateway96 board. at91bootstrap will start from QSPI flash, U-Boot and Linux will be loaded from uSD card.

    - follow this how-to up to (but not including) "bitbake core-image-minimal": https://github.com/linux4sam/meta-atmel/
    - actual directory is .../poky/build-microchip . Execute the following:
      $ cp .../meta-gw96/meta-gw96/ ../../ -R
      $ bitbake-layers add-layer ../../meta-gw96/

    - modify ../../meta-atmel/conf/machine/sama5d27-som1-ek-sd.conf based on ../sama5d27-som1-ek1/boot_from_mmc1/meta-atmel/ (relative to the directory of this readme)
      $ cp .../sama5d27-som1-ek1/boot_from_mmc1/meta-atmel/ ../../meta-atmel/ -R

    - build image (will take ~2 hours)
      $ bitbake core-image-minimal

    - follow ../sama5d27-som1-ek1/boot_from_mmc1/readme.txt for creating uSD card image.
    - prepare at91bootstrap vor SAM-BA:
      $ cp tmp/deploy/images/sama5d27-som1-ek-sd/at91bootstrap.bin <SAM-BA folder>


    Writing at91bootstrap into NOR flash

    - on GW96 board remove J3, connect J10 to PC and press nRST
    - on PC execute:
      sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c readcfg:bureg0
      sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c readcfg:bscr

    - shorten J3, then execute
      sam-ba.exe -p serial:COM37 -d sama5d2 -a qspiflash:1:2:66 -c erase::0x100000
      sam-ba.exe -p serial:COM37 -d sama5d2 -a qspiflash:1:2:66 -c write:at91bootstrap.bin
      sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c writecfg:bureg0:QSPI0_IOSET1,QSPI1_IOSET2,SPI0_IOSET1,SPI1_IOSET1,NFC_IOSET1,SDMMC0_DISABLED,SDMMC1,UART1_IOSET1,JTAG_IOSET3,EXT_MEM_BOOT
      sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c writecfg:bscr:bureg0,valid

    - open TeraTerm for COM37
    - press nRST
    - smile

