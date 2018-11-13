DESCRIPTION = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-base-wifi \
    kernel-modules \
    iw \
    kmod \
    dhcp-client \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image
