    meta-gw96 layer will create "core-image-minimal" running on the Gateway96
board. at91bootstrap will start from QSPI flash, U-Boot and Linux will be
loaded from uSD card.

    - follow this how-to up to (but not including) "bitbake core-image-minimal":
        https://github.com/linux4sam/meta-atmel/
    - current directory is .../poky/build-microchip . Execute the following:
      $ cp .../meta-gw96/meta-gw96/ ../../ -R
      $ bitbake-layers add-layer ../../meta-gw96/

    - modify ../../meta-atmel/conf/machine/sama5d27-som1-ek-sd.conf based on
../meta-atmel/ (relative to the directory of this readme)
      $ cp .../meta-gw96/meta-atmel/ ../../meta-atmel/ -R

    - build image (will take ~2 hours)
      $ bitbake iw-image



    Creating uSD card image

    - copy initial filesystem image to uSD card:
      $ cd tmp/deploy/images/sama5d27-som1-ek-sd
      $ sudo dd if=core-image-minimal-sama5d27-som1-ek-sd.wic bs=1M of=/dev/sdX && sync

    - remount uSD card and copy missing files:
      ( /media/<user> is the folder where the uSD card is mounted)
      $ sudo cp zImage-at91-sama5d27_som1_ek.dtb /media/<user>/boot/at91-sama5d27_som1_ek.dtb
      $ sudo cp zImage /media/<user>/boot/

    - unmount uSD card



    Writing at91bootstrap into NOR flash

    - prepare at91bootstrap for SAM-BA (still in .../tmp/deploy/images/sama5d27-som1-ek-sd folder ):
      $ cp at91bootstrap.bin <SAM-BA folder>

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
    - insert uSD card into Gateway96
    - press nRST
    - smile
    (sometimes Linux gets faster to the login prompt if an ethernet cable is connected)



    Testing WiFi on Gateway96

    - log in to Linux as user 'root'
    - execute these:
      # ifconfig eth0 down
      # ifconfig wlan0 up

    Sometimes the last step fails with the following error message:
      ifconfig: SIOCSIFFLAGS: Input/output error
    In this case please repeat the following commands until the error message disappears:
      # ifconfig wlan0 down
      # ifconfig wlan0 up

    - scan for WiFi networks:
      # iw dev wlan0 scan | grep ssid -i

    - connect to one SSID:
      # cp /etc/wpa_supplicant.conf .
      # wpa_passphrase <SSID> <passphrase> >> wpa_supplicant.conf
      # wpa_supplicant -B -Dnl80211 -iwlan0 -cwpa_supplicant.conf

    - check connection
      # iw dev wlan0 link

    - acquire IP address and test connection
      # dhclient wlan0
      # ifconfig wlan0
      # ping amazon.com

    WILC1000 driver dumps a lot of LOG messages which make normal work difficult.
But as we already have IP address it is possible the SSH into the board. On a
remote machine execute:
      $ ssh root@<IP of Gateway96>


