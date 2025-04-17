# Add this layer to SRC_URI search path
FILESEXTRAPATHS:prepend := "${THISDIR}/u-boot-gta04-2016.11:"

HOMEPAGE = "https://projects.goldelico.com/p/gta04-uboot/"
DESCRIPTION = "U-Boot fork for GTA04."
SECTION = "bootloaders"
DEPENDS += "bc-native flex-native bison-native python3-pyelftools-native python3-setuptools-native"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
PE = "1"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "21d5cc022f3c22081facb68ff511e532946c0d49"

SRC_URI = "git://github.com/OpenPhoenux/gta04-uboot.git;branch=letux-2016.11;protocol=https \
           file://0001-letux-gta04-increase-maximum-gunzip-size.patch"

#PV = "2016.11+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# this u-boot does not use initialenv
UBOOT_INITIAL_ENV = ""

inherit pkgconfig

do_configure[cleandirs] = "${B}"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "u-boot-mkimage-native"

do_compile:append() {
    # prepare boot-menu support files
    ${UBOOT_MKIMAGE} -C none -A ${UBOOT_ARCH} -T script -d ${S}/Letux/boot-scr/boot-gta04.txt ${WORKDIR}/boot.scr
    gzip -9 -c ${S}/Letux/boot-scr/menu.rgb16 > ${WORKDIR}/menu.rgb16z
    gzip -9 -c ${S}/Letux/boot-scr/splash.rgb16 > ${WORKDIR}/splash.rgb16z
}

# TODO: include support files in u-boot package for opkg upgrade at /boot/loader/
# TODO: install MLO, u-boot.img to /boot/loader/

do_deploy:append() {
    # deploy boot-menu support files
    install -m 644 ${WORKDIR}/boot.scr ${DEPLOYDIR}/
    install -m 644 ${WORKDIR}/menu.rgb16z ${DEPLOYDIR}/
    install -m 644 ${WORKDIR}/splash.rgb16z ${DEPLOYDIR}/
}
