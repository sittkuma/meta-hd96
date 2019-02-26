    Create at91bootstrap which can be written into NOR flash and which will load U-Boot from SDMMC1

$ cd /mnt/work/sama5d27-som1-ek1/poky/
$ source oe-init-build-env build-microchip

>> now in .../poky/build-microchip

$ cp .../meta-gw96/meta-gw96/ ../../ -R
$ bitbake-layers add-layer ../../meta-gw96/

Follow ../sama5d27-som1-ek1/boot_from_mmc1/readme.txt for creating uSD card image.


    Writing image into NOR flash

    - on GW96 board remove J3, connect J10 and press nRST
    - on PC execute:
      .../sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c readcfg:bureg0
      .../sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c readcfg:bscr
      .../sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c readcfg:bureg0

    - shorten J3, then execute
      .../sam-ba.exe -p serial:COM37 -d sama5d2 -a qspiflash:1:2:66 -c erase::0x10000
      .../sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c writecfg:bureg0:QSPI0_IOSET1,QSPI1_IOSET2,SPI0_IOSET1,SPI1_IOSET1,NFC_IOSET1,SDMMC0_DISABLED,SDMMC1,UART1_IOSET1,JTAG_IOSET3,EXT_MEM_BOOT
      .../sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c writecfg:bscr:bureg0,valid
      .../sam-ba.exe -p serial:COM37 -d sama5d2 -a qspiflash:1:2:66 -c write:bootstrap_gw96.bin

    - open TeraTerm for COM37
    - press nRST

