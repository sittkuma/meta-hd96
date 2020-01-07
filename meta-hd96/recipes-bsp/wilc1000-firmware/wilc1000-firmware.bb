DESCRIPTION = "Microchip WILC1000 firmware"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.wilc_fw;md5=89ed0ff0e98ce1c58747e9a39183cc9f"

S = "${WORKDIR}"

inherit allarch

SRC_URI += "file://wilc1000_wifi_firmware.bin"
SRC_URI += "file://LICENCE.wilc_fw"

do_install () {
    install -d ${D}${base_libdir}/firmware/mchp
    install -m 755 ${WORKDIR}/wilc1000_wifi_firmware.bin ${D}${base_libdir}/firmware/mchp
}

FILES_${PN} += "${base_libdir}/firmware/mchp/wilc1000_wifi_firmware.bin"

