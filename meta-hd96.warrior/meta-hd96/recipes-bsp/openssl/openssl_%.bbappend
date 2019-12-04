FILESEXTRAPATHS_append := "${THISDIR}/files:"

SRC_URI += "file://openssl.cnf.append"

do_install_append() {
    sed -i '1s/^/openssl_conf = openssl_init\n\n/' ${D}${libdir}/ssl-1.1/openssl.cnf
    cat ${WORKDIR}/openssl.cnf.append >> ${D}${libdir}/ssl-1.1/openssl.cnf 
}

