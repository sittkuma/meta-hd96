FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0002-at91bootstrap-hd96.patch"

COMPATIBLE_MACHINE = 'sama5d27-hd96'

AT91BOOTSTRAP_CONFIG_sama5d27-hd96 ??= "${AT91BOOTSTRAP_MACHINE}sd_uboot"
AT91BOOTSTRAP_LOAD_sama5d27-hd96 ??= "sdboot-uboot"

