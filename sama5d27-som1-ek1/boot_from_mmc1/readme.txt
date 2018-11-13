    Create bootable image for ATSAMA5D27-SOM1-EK1 and boot from MMC1

- build core-image-minimal using this how-to: https://github.com/linux4sam/meta-atmel

- $ bitbake at91bootstrap -c menuconfig
  Memory selection -> SD Card Configuration -> select SDHC1

- $ bitbake at91bootstrap -C compile -f
  $ bitbake u-boot-at91 -c clean -f

- modify meta-atmel/conf/machine/sama5d27-som1-ek-sd.conf
  $ bitbake u-boot-at91 -c deploy -f
  $ bitbake core-image-minimal
  $ cd /mnt/work/sama5d27-som1-ek1/poky/build-microchip/tmp/deploy/images/sama5d27-som1-ek-sd
  $ sudo dd if=core-image-minimal-sama5d27-som1-ek-sd.wic bs=1M of=/dev/sdX && sync

- remount uSD card
  $ sudo cp zImage-at91-sama5d27_som1_ek.dtb /media/botond/boot/at91-sama5d27_som1_ek.dtb
  $ sudo cp zImage /media/botond/boot/

- ejct uSD card, reboot som1-ek1

