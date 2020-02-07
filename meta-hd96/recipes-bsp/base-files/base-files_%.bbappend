FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://protect_links.sh"
SRC_URI += "file://issue"
SRC_URI += "file://udhcpd.conf"
SRC_URI += "file://iptables/share_eth0"
SRC_URI += "file://iptables/share_ppp0"
SRC_URI += "file://network/powerkey_bg96.py"
SRC_URI += "file://modules-load.d/wilc-sdio.conf"
SRC_URI += "file://modules-load.d/ppp-generic.conf"
SRC_URI += "file://root_README_WIFI.txt"
SRC_URI += "file://root_README_NB-IoT.txt"
SRC_URI += "file://ppp/peers/quectel-chat-connect"
SRC_URI += "file://ppp/peers/quectel-chat-disconnect"
SRC_URI += "file://ppp/peers/quectel-ppp"
SRC_URI += "file://ppp/peers/quectel-ppp-kill"

RDEPENDS_${PN} += "python3-core"

hostname = "shield96"

do_install_append () {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc5.d
    install -m 755 ${WORKDIR}/protect_links.sh ${D}${sysconfdir}/init.d/
    ln -s ../init.d/protect_links.sh ${D}${sysconfdir}/rc5.d/S21protect_links

    install -m 644 ${WORKDIR}/issue ${D}${sysconfdir}/
    install -m 644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/

    install -d ${D}${sysconfdir}/iptables
    install -m 644 ${WORKDIR}/iptables/share* ${D}${sysconfdir}/iptables/

    install -d ${D}${sysconfdir}/network/if-up.d
    install -m 755 ${WORKDIR}/network/powerkey_bg96.py ${D}${sysconfdir}/network/

    install -d ${D}${sysconfdir}/modules-load.d
    install -m 644 ${WORKDIR}/modules-load.d/* ${D}${sysconfdir}/modules-load.d/

    install -d ${D}${base_prefix}/home/root
    install -m 644 ${WORKDIR}/root_* ${D}${base_prefix}/home/root/

    install -d ${D}${sysconfdir}/ppp/peers
    install -m 644 ${WORKDIR}/ppp/peers/* ${D}${sysconfdir}/ppp/peers/

    cat >> ${D}${sysconfdir}/fstab <<EOF

/dev/mmcblk1p3 swap swap defaults 0 0

EOF
}

FILES_${PN} += "${sysconfdir}/init.d/protect_links.sh"
FILES_${PN} += "${sysconfdir}/issue"
FILES_${PN} += "${sysconfdir}/rc5.d/S21protect_links"
FILES_${PN} += "${sysconfdir}/udhcpd.conf"
FILES_${PN} += "${sysconfdir}/iptables/share_eth0"
FILES_${PN} += "${sysconfdir}/iptables/share_ppp0"
FILES_${PN} += "${sysconfdir}/network/powerkey_bg96.py"
FILES_${PN} += "${sysconfdir}/modules-load.d/wilc-sdio.conf"
FILES_${PN} += "${sysconfdir}/modules-load.d/ppp-generic.conf"
FILES_${PN} += "${base_prefix}/home/root}/root_README_WIFI.txt"
FILES_${PN} += "${base_prefix}/home/root}/root_README_NB-IoT.txt"
FILES_${PN} += "${sysconfdir}/ppp/peers/quectel-chat-connect"
FILES_${PN} += "${sysconfdir}/ppp/peers/quectel-chat-disconnect"
FILES_${PN} += "${sysconfdir}/ppp/peers/quectel-ppp"
FILES_${PN} += "${sysconfdir}/ppp/peers/quectel-ppp-kill"

