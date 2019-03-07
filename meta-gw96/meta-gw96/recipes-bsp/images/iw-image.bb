DESCRIPTION = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-base-wifi \
    kernel-modules \
    iw \
    kmod \
    dhcp-client \
    wilc1000-firmware \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image
