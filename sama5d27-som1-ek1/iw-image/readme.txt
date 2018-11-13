    Support for WILC1000 SD module

- follow instructions in ../boot_from_mmc1
- add iw-image.bb 

- add WILC1000 driver to the kernel: https://github.com/linux4wilc/driver/wiki
  (don't forget Makefile)
  $ bitbake virtual/kernel -c menuconfig -f
  add WILC_SDIO (Device drivers -> Staging drivers -> Microchip)

- $ bitbake virtual/kernel -C compile -f
  $ bitbake iw-image

- $ cd /mnt/work/sama5d27-som1-ek1/poky/build-microchip/tmp/deploy/images/sama5d27-som1-ek-sd
  $ sudo dd if=iw-image-sama5d27-som1-ek-sd.wic bs=1M of=/dev/sdX && sync

- remount uSD card
  $ sudo cp zImage-at91-sama5d27_som1_ek.dtb /media/botond/boot/at91-sama5d27_som1_ek.dtb
  $ sudo cp zImage /media/botond/boot/
  $ sudo mkdir -p /media/botond/root/lib/firmware/mchp
  $ sudo cp .../wilc1000_wifi_firmware.bin /media/botond/root/lib/firmware/mchp/

- eject uSD card, reboot board

- log in as 'root' and enter
  # ifconfig eth0 down
  # depmod -a
  # modprobe wilc-sdio
  # iw dev

- now we should see something like this:
phy#1
  Interface p2p0 ...
phy#0
  Interface wlan0 ...

- # cp /etc/wpa_supplicant.conf .
  # wpa_passphrase ESC-Bp Ourdirtysecret >> wpa_supplicant.conf
  # ifconfig wlan0 up
  # ifconfig wlan0

- wlan0 shall now be up now

- # wpa_supplicant -B -Dnl80211 -iwlan0 -cwpa_supplicant.conf

- check connection status:
  # iw wlan0 link
  # wpa_cli -iwlan0 status

- get IP address
  # dhclient wlan0
  # ping amazon.com

