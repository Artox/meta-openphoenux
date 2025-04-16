SUMMARY = "Linux fork for GTA04"
HOMEPAGE = "https://projects.goldelico.com/p/gta04-kernel/"

# Add this layer to SRC_URI search path
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-gta04-6.6.y:"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

#SRC_URI += "git://github.com/goldelico/letux-kernel.git;branch=letux-6.6.y;protocol=https;nocheckout=1"
SRC_URI += "git://git.goldelico.com/letux-kernel.git;branch=letux-6.6.y;protocol=https;nocheckout=1 \
            file://0001-staging-rtl8189es-fix-include-paths-for-out-of-tree-.patch \
           "
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.6.87"
LINUX_VERSION_EXTENSION:append = "-letux"
# tag: letux-6.6.87 1c55b5923949e775d148f2409d225f5bb047c901
SRCREV = "1c55b5923949e775d148f2409d225f5bb047c901"

PV = "${LINUX_VERSION}+git"

# choose correct defconfig
KBUILD_DEFCONFIG = "letux_defconfig"

# defconfig includes vfat by default, we don't have snippet for it
KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
