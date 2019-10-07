DESCRIPTION = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_FEATURES += "ssh-server-openssh"

PACKAGECONFIG_append_pn-gnutls = " p11-kit"

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
    python3-pip \
    openssl \
    openssl-bin \
    cmake \
    libp11 \
    libp11-dev \
    gnutls-bin \
    opkg \
    opkg-utils \
    ppp \
    wilc1000-firmware \
    udev \
    git \
    p11-kit \
    hidapi \
    cryptoauthlib \
    ethtool \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image
