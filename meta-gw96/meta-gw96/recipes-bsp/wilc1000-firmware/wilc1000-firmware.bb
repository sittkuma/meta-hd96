DESCRIPTION = "Microchip WILC1000 firmware"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.wilc_fw;md5=89ed0ff0e98ce1c58747e9a39183cc9f"

PACKAGE_ARCH = "all"

SRC_URI += "file://wilc1000_wifi_firmware.bin"

do_install () {
    install -d ${D}${base_libdir}/firmware/mchp
    install -m 755 ${WORKDIR}/wilc1000_wifi_firmware.bin ${D}${base_libdir}/firmware/mchp
}

FILES_${PN} += "${base_libdir}/firmware/mchp/wilc1000_wifi_firmware.bin"

