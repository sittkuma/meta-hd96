FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
  
SRC_URI += "file://0001-python3-fix.patch"

inherit setuptools3
