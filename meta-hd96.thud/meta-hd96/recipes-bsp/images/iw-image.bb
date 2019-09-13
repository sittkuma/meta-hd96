DESCRIPTION = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-base-wifi \
    packagegroup-core-buildessential \
    kernel-modules \
    iw \
    kmod \
    python-pip \
    python-smbus \
    python3 \
    openssl \
    cmake \
    ppp \
    wilc1000-firmware \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image
