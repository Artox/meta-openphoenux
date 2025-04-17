SRC_URI:append = "https://git.ti.com/cgit/ti-bt/service-packs/plain/initscripts/TIInit_12.8.32.bts;name=wl1835bt \
                  https://git.ti.com/cgit/ti-bt/service-packs/plain/LICENSE;downloadfilename=LICENSE.wl1835-bluetooth;name=wl1835btlic \
"
SRC_URI[wl1835bt.sha256sum] = "26ab0608e39fab95a6a55070c2f8364c92aad34442e8349abda71cee4da3277a"
SRC_URI[wl1835btlic.sha256sum] = "21fd99ce784dc33b39ec0b4a383a9a9b8dafea261d73ad4548683c4eecd87f37"

NO_GENERIC_LICENSE[Firmware-wl1835-bluetooth] = "LICENSE.wl1835-bluetooth"
LIC_FILES_CHKSUM:append = "file://LICENSE.wl1835-bluetooth;md5=f39eac9f4573be5b012e8313831e72a9 \
"
LICENSE:append = "    & Firmware-wl1835-bluetooth \
"

PACKAGES =+ "${PN}-wl1835-bluetooth \
"
FILES:${PN}-wl1835-bluetooth = "${nonarch_base_libdir}/firmware/ti-connectivity/TIInit_12.8.32.bts \
                                ${nonarch_base_libdir}/firmware/LICENSE.wl1835-bluetooth"
LICENSE:${PN}-wl1835-bluetooth = "Firmware-wl1835-bluetooth"

do_install:prepend() {
    cp -v ${WORKDIR}/LICENSE.wl1835-bluetooth ${S}/
}
