FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://protect_links.sh"
SRC_URI += "file://issue"
SRC_URI += "file://udhcpd.conf"
SRC_URI += "file://iptables/sharing"
SRC_URI += "file://network/iptables.sh"
SRC_URI += "file://modules-load.d/wilc-sdio.conf"
SRC_URI += "file://root_README_WIFI.txt"

do_install_append () {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc5.d
    install -m 755 ${WORKDIR}/protect_links.sh ${D}${sysconfdir}/init.d/
    ln -s ../init.d/protect_links.sh ${D}${sysconfdir}/rc5.d/S21protect_links

    install -m 644 ${WORKDIR}/issue ${D}${sysconfdir}/
    install -m 644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/

    install -d ${D}${sysconfdir}/iptables
    install -m 644 ${WORKDIR}/iptables/sharing ${D}${sysconfdir}/iptables/

    install -d ${D}${sysconfdir}/network/if-up.d
    install -m 755 ${WORKDIR}/network/iptables.sh ${D}${sysconfdir}/network/

    install -d ${D}${sysconfdir}/modules-load.d
    install -m 644 ${WORKDIR}/modules-load.d/wilc-sdio.conf ${D}${sysconfdir}/modules-load.d/

    install -d ${D}${base_prefix}/home/root
    install -m 755 ${WORKDIR}/root_README_WIFI.txt ${D}${base_prefix}/home/root/README_WIFI.txt

    cat >> ${D}${sysconfdir}/fstab <<EOF

/dev/mmcblk1p3 swap swap defaults 0 0

EOF
}

FILES_${PN} += "${sysconfdir}/init.d/protect_links.sh"
FILES_${PN} += "${sysconfdir}/issue"
FILES_${PN} += "${sysconfdir}/rc5.d/S21protect_links"
FILES_${PN} += "${sysconfdir}/udhcpd.conf"
FILES_${PN} += "${sysconfdir}/iptables/sharing"
FILES_${PN} += "${sysconfdir}/network/iptables.sh"
FILES_${PN} += "${sysconfdir}/modules-load.d/wilc-sdio.conf"
FILES_${PN} += "${base_prefix}/home/root}/root_README_WIFI.txt"

