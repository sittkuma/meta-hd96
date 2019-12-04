DESCRIPTION = "cryptoauthlib" 
SECTION = "examples" 
LICENSE = "CLOSED" 
PR = "r0" 

DEPENDS += "udev libp11"

SRC_URI = "git://github.com/MicrochipTech/cryptoauthlib.git;branch=pkcs11"
SRC_URI += "file://cryptoauthlib.conf"
SRC_URI += "file://cryptoauthlib.module"
SRC_URI += "file://0.conf"
SRC_URI += "file://slot.conf.tmpl"
SRC_URI += "file://pkcs11.conf"
SRCREV = "a0007d2f6c42fddab5dca1575e0f404788829ddc"

S = "${WORKDIR}/git/"

inherit cmake
EXTRA_OECMAKE += "-DDEFAULT_LIB_PATH=/usr/lib"

do_install_append() {
    install -d ${D}${sysconfdir}/cryptoauthlib
    install -m 755 ${WORKDIR}/cryptoauthlib.conf ${D}${sysconfdir}/cryptoauthlib/
    install -d ${D}${localstatedir}/lib/cryptoauthlib
    install -m 755 ${WORKDIR}/0.conf ${D}${localstatedir}/lib/cryptoauthlib/
    install -m 755 ${WORKDIR}/slot.conf.tmpl ${D}${localstatedir}/lib/cryptoauthlib/
    install -d ${D}${base_prefix}/home/root/.config/pkcs11/modules
    install -m 755 ${WORKDIR}/cryptoauthlib.module ${D}${base_prefix}/home/root/.config/pkcs11/modules/
    install -d ${D}${sysconfdir}/pkcs11
    install -m 755 ${WORKDIR}/pkcs11.conf ${D}${sysconfdir}/pkcs11/
    install -d ${D}${libdir}/python3.5/site-packages/cryptoauthlib
    lnr ${D}${libdir}/libcryptoauth.so ${D}${libdir}/python3.5/site-packages/cryptoauthlib/libcryptoauth.so
}

FILES_${PN} = "/usr/lib/libcryptoauth.so"
FILES_${PN} += "/etc/cryptoauthlib/cryptoauthlib.conf"
FILES_${PN} += "/var/lib/cryptoauthlib/0.conf"
FILES_${PN} += "/var/lib/cryptoauthlib/slot.conf.tmpl"
FILES_${PN} += "/home/root/.config/pkcs11/modules/cryptoauthlib.module"
FILES_${PN} += "/etc/pkcs11/pkcs11.conf"
FILES_${PN}-dev = "/usr/lib/python3.5/site-packages/cryptoauthlib/libcryptoauth.so"

