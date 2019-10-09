LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=8284c8bb0e485624906d15970a3dc765"

SRC_URI = "https://files.pythonhosted.org/packages/95/1c/0e111ccb78e0ca556d1da1d63ec96826c79cb33fa788acb4a20ba586d860/cryptoauthlib-${PV}.tar.gz"
SRC_URI[md5sum] = "1b8630627a080c5521c758b336478fb2"
SRC_URI += "file://0001-py-cryptoauth-setup.patch"

PYPI_PACKAGE = "cryptoauthlib"

DEPENDS += "cmake-native"

inherit pypi setuptools3

do_install_append() {
    rm ${D}/usr/lib/python3.5/site-packages/cryptoauthlib/libcryptoauth.so
}

