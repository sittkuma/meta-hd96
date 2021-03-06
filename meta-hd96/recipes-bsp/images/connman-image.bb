DESCRIPTION = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_FEATURES += "ssh-server-openssh"
IMAGE_ROOTFS_SIZE = "3670016"

PACKAGECONFIG_append_pn-gnutls = " p11-kit"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-base-wifi \
    packagegroup-core-buildessential \
    kernel-modules \
    iw \
    kmod \
    python-pip \
    python3 \
    python3-pip \
    openssl \
    openssl-bin \
    libssl10 \
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
    ethtool \
    p11-kit \
    hidapi \
    python3-asn1crypto \
    python3-cffi \
    python3-click \
    python3-cryptography \
    python3-cryptoauthlib \
    cryptoauthlib \
    cryptoauthlib-dev \
    python3-flask \
    python3-pyserial \
    python3-can \
    python3-smbus \
    python3-spidev \
    python3-wrapt \
    mpio \
    python3-evdev \
    boost \
    boost-dev \
    connman \
    connman-client \
    connman-tools \
    cronie \
    lighttpd \
    lighttpd-module-fastcgi \
    openjdk-8 \
    docker-ce \
    python3-docker-compose \
    corelockr corelockr-dev \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image
