    meta-hd96 layer will create various uSD card images running on the Shield96
board. It is possible to boot from NOR flash + uSD card or only from uSD card.



    Creating connman-image

    For creating the build environment one needs to use the 'repo' tool which
is available in most Linux distributions. The build process will follow the
"meta-atmel" way, only the build environment initialization is different and
simpler. See meta-atmel: https://github.com/linux4sam/meta-atmel/tree/warrior

    The required steps:
    - create a working directory and enter it
    - initialize the build folders by issuing:
      $ repo init -u https://github.com/bkardosa/meta-hd96.git -b warrior_v1.3
      $ repo sync

    - the above commands may display various warning or error messages which can be ignored if the
      last displayed lines look like this:
        Fetching projects: 100% (8/8), done.
        Checking out projects: 100% (8/8), done.

    - the following commands need to be executed for rebuilding the image:
      $ cd poky
      $ source oe-init-build-env build-microchip
      $ bitbake connman-image

    Rebuilding the image will take 1-2 hours or more depending on machine and
internet speed and require around 25GiB disk space.




    Creating uSD card image

    - copy initial filesystem image to uSD card:
      $ cd tmp/deploy/images/sama5d27-hd96/
      $ sudo dd if=connman-image-sama5d27-hd96.wic bs=1M of=/dev/sdX && sync
      where /dev/sdX is the location of an UNMOUNTED uSD card

    Booting from uSD card

    - insert uSD card
    - disconnect J3
    - connect J10 to PC
    - start TeraTerm or similar terminal emulator, configure the COM port to
      115200,N81
    - after reboot one can log in as 'root', no password



    Writing at91bootstrap into NOR flash and booting from NOR flash

    - prepare at91bootstrap for SAM-BA (still in .../tmp/deploy/images/sama5d27-hd96 folder ):
      $ cp at91bootstrap.bin <SAM-BA folder>

    - on Shield96 board remove J3, connect J10 to PC and press nRST
    - on PC execute:
      sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c readcfg:bscr -c readcfg:bureg0

    - shorten J3, then execute
      sam-ba.exe -p serial:COM37 -d sama5d2 -a qspiflash:1:2:66 -c erase::0x100000
      sam-ba.exe -p serial:COM37 -d sama5d2 -a qspiflash:1:2:66 -c write:at91bootstrap.bin
      sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c writecfg:bureg0:QSPI0_DISABLED,QSPI1_IOSET2,SPI0_DISABLED,SPI1_DISABLED,NFC_DISABLED,SDMMC0_DISABLED,SDMMC1,UART1_IOSET1,JTAG_IOSET3,EXT_MEM_BOOT
      sam-ba.exe -p serial:COM37 -d sama5d2 -a bootconfig -c writecfg:bscr:bureg0,valid

    - open TeraTerm for COM37, configure it to 115200,N81
    - insert uSD card into Shield96
    - press nRST
    - smile :)
    (sometimes Linux gets faster to the login prompt if an ethernet cable is connected)



    Testing WiFi on Shield96

    - log in to Linux as user 'root'
    - execute these:
      # ifconfig eth0 down
      # iw list | grep phy

    - if the WiFi module was found the last command should display:
      Wiphy phy1
      Wiphy phy0
      # ifconfig wlan0 up

    - scanning for WiFi networks:
      # ifconfig wlan0 up
      # iw dev wlan0 scan | grep ssid -i

    - connect to one SSID:
      # wpa_passphrase <SSID> <passphrase> > wpa_supplicant.conf
      # wpa_supplicant -B -Dnl80211 -iwlan0 -cwpa_supplicant.conf

    - check connection
      # iw dev wlan0 link

    - acquire IP address and test connection
      # udhcpc -i wlan0
      # ifconfig wlan0
      # ping amazon.com

    WILC1000 driver dumps a lot of LOG messages which make normal work difficult.
But as we already have IP address it is possible to SSH into the board. On a
remote machine execute:
      $ ssh root@<IP of Shield96>



    Automatic WiFi configuration with connman

    - reboot, login as root and enter
      # connmanctl
      connmanctl> enable wifi
      connmanctl> scan wifi
      connmanctl> services

    - some WiFi services will be listed like
      SSID1                wifi_000000000000_4d696368656c696e204775657374_managed_none
      SSID2                wifi_000000000000_494e455443464156_managed_psk
      ...

      connmanctl> agent on
      connmanctl> connect wifi_000   # TAB can be used to autocomplete network name
      Agent RequestInput wifi_000000000000_47355f37353635_managed_psk
        Passphrase = [ Type=psk, Requirement=mandatory ]
      Passphrase? ********

    - after a few seconds connmanctl displays this
      Connected wifi_00..._managed_psk
      connmanctl> ^D

    - in a few seconds connman requests IP address and the connection will be
      started automatically after reboot



    Building a WiFi access point with hostapd-image

    - follow the steps at "Creating connman-image"
    - the last command should be replaced by this:
      $ bitbake hostapd-image

    - the uSD card image will be written to:
      tmp/deploy/images/sama5d27-hd96/hostapd-image-sama5d27-hd96.wic

    - after booting the board the file /home/root/README_WIFI.txt will contain
      instructions about setting up a WiFi access point
 
