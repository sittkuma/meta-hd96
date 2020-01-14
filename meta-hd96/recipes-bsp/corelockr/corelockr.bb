DESCRIPTION = "Sequitur Labs' CoreLockr"
SECTION = "examples"
LICENSE = "CLOSED"

S = "${WORKDIR}"

# inherit allarch
do_package_qa[noexec] = "1"

SRC_URI += "file://data/tee/138A1951-2A00-BF5A-A463E61F402EBE1D.stp"
SRC_URI += "file://data/tee/222A521C-62CB-0653-BCE5DD727660FAF0.stp"
SRC_URI += "file://data/tee/38C7D5D6-6487-4B55-99C6479C2B6AD457.stp"
SRC_URI += "file://data/tee/5840EE82-131E-4259-BB1F2A9286DA48A8.stp"
SRC_URI += "file://lib/libseqr_corelockr_crypto.so"
SRC_URI += "file://lib/libteec.so.1.0"
SRC_URI += "file://lib/libseqr_corelockr_cert.so"
SRC_URI += "file://usr/bin/tee-supplicant"

do_install () {
    install -d ${D}/data/tee
    install -m 644 ${WORKDIR}/data/tee/* ${D}/data/tee/

    install -d ${D}/lib
    install -m 755 ${WORKDIR}/lib/* ${D}/lib/
    lnr ${D}/lib/libteec.so.1 ${D}/lib/libteec.so
    lnr ${D}/lib/libteec.so.1.0 ${D}/lib/libteec.so.1

    install -d ${D}${bindir}
    install -m 755 ${WORKDIR}/usr/bin/* ${D}${bindir}
}

INSANE_SKIP_${PN} = "already-stripped"

FILES_${PN}-dev += "/lib/libteec.so"
FILES_${PN}-dev += "/lib/libseqr_corelockr_cert.so"
FILES_${PN}-dev += "/lib/libseqr_corelockr_crypto.so"
FILES_${PN} += "/lib/libteec.so.1"
FILES_${PN} += "/lib/libteec.so.1.0"

FILES_${PN} += "/data"
FILES_${PN} += "${bindir}"

