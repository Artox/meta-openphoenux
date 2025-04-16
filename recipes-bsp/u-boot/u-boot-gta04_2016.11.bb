# Add this layer to SRC_URI search path
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

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
           file://bootargs.txt"

#PV = "2016.11+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit pkgconfig

do_configure[cleandirs] = "${B}"

require recipes-bsp/u-boot/u-boot.inc

UBOOT_INITIAL_ENV = ""

# u-boot looks for special boot-script "bootargs.scr"
UBOOT_ENV = "bootargs"
UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV_SRC_SUFFIX = "txt"
