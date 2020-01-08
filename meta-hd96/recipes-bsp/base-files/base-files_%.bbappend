FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://protect_links.sh"
SRC_URI += "file://issue"

do_install_append () {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc5.d
    install -m 755 ${WORKDIR}/protect_links.sh ${D}${sysconfdir}/init.d/
    ln -s ../init.d/protect_links.sh ${D}${sysconfdir}/rc5.d/S21protect_links
    install -m 644 ${WORKDIR}/issue ${D}${sysconfdir}/

    cat >> ${D}${sysconfdir}/fstab <<EOF

/dev/mmcblk1p3 swap swap defaults 0 0

EOF
}

FILES_${PN} += "/etc/init.d/protect_links.sh"
FILES_${PN} += "/etc/issue"
FILES_${PN} += "/etc/rc5.d/S21protect_links"

