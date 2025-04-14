# Yocto BSP Layer for OpenPhoenux Devices

This layer aspires to support devices of the OpenPhoenux Community.
Initial focus is on the [GTA04](https://projects.goldelico.com/p/gta04-main/).

## HW Compatibility

- [GTA04](https://projects.goldelico.com/p/gta04-main/) A5
  - Serial Console: Untested
  - microSD: Untested
  - OneNAND Flash: Untested
  - LCD: Untested
  - Touchscreen: Untested
  - WiFi: Untested
  - Bluetooth: Untested
  - OpenGL-ES: Untested
  - USB-OTG: Untested
  - GPS: Untested
  - Camera: Untested
  - Accelerometer: Untested
  - Compass: Untested
  - Gyroscope: Untested
  - Pressure Sensor: Untested
  - Thermal Sensor: Untested
  - Ambient Light Sensor: Untested
  - Speaker: Untested
  - Microphone: Untested
  - Headphone Jack: Untested
  - Battery Charger: Untested
  - Buttons: Untested
  - LEDs: Untested
  - RFID: Untested
  - IR Transceiver: Untested

## Compile base image

Start in a new empty directory with plenty of free disk space - at least 100GB.
Then download the build recipes:

    git clone -b scarthgap git://git.yoctoproject.org/poky
    git clone -b scarthgap https://github.com/Artox/meta-openphoenux.git

Initialise a build directory with example configuration files and appropriate shell environment variables.
Note that this step may be repeated without losing the contents of the build directory, to reinitialise the required environment variables (e.g. `$PATH`):

    cd poky
    . ./oe-init-build-env ../build

The script will change directory to the specified build directory (`../build`), and create an example configuration inside a `conf` directory:

    ❯ LANG=C ls -ln conf
    total 32
    -rw-r--r-- 1 1000 100   428 Apr  8 22:37 bblayers.conf
    -rw-r--r-- 1 1000 100   516 Apr  8 22:36 conf-notes.txt
    -rw-r--r-- 1 1000 100    77 Apr  8 22:36 conf-summary.txt
    -rw-r--r-- 1 1000 100 12680 Apr  8 22:36 local.conf
    -rw-r--r-- 1 1000 100    33 Apr  8 22:36 templateconf.cfg

It is **neccessary** to edit `bblayers`.conf. It lists all of the individual folders to import recipes from.
Add`meta-openphoenux` so that the file looks similar to the example below:

    # POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
    # changes incompatibly
    POKY_BBLAYERS_CONF_VERSION = "2"

    BBPATH = "${TOPDIR}"
    BBFILES ?= ""

    BBLAYERS ?= " \
      /opt/workspace/YOCTO/openmoko/scarthgap/poky/meta \
      /opt/workspace/YOCTO/openmoko/scarthgap/poky/meta-poky \
      /opt/workspace/YOCTO/openmoko/scarthgap/poky/meta-yocto-bsp \
      /opt/workspace/YOCTO/openmoko/scarthgap/meta-openphoenux \
      "
      "

To create a bootable image with default configuration it is enough to define the target machine and invoke `bitbake`:

    export MACHINE=gta04a
    bitbake core-image-minimal

Results are available in **`tmp/deploy/images/gta04a5`**.

## Compatible Layers

- [meta-virtualization](git://git.yoctoproject.org/meta-virtualization)

  To enable Docker, add to `conf/local.conf`:

      IMAGE_INSTALL:append = " docker-ce python3-docker-compose kernel-modules"
      DISTRO_FEATURES:append = " virtualization"

## Options

TBD.

## HW Interface Basic Tests

TBD.

## Common Issues

### make version 4.2.1 is known to have issues

```
ERROR:  OE-core's config sanity checker detected a potential misconfiguration.
    Either fix the cause of this error or at your own risk disable the checker (see sanity.conf).
    Following is the list of potential problems / advisories:

    make version 4.2.1 is known to have issues on Centos/OpenSUSE and other non-Ubuntu systems. Please use a buildtools-make-tarball or a newer version of make.
```

Prebuilt buildtools with compatible versions are available for download from the yocto project: [x86_64-buildtools-extended-nativesdk-standalone-5.0.3.sh](https://downloads.yoctoproject.org/releases/yocto/yocto-5.0.3/buildtools/x86_64-buildtools-extended-nativesdk-standalone-5.0.3.sh)
Follow the Yocto Instructions on [Downloading a Pre-Built buildtools Tarball](https://www.rpsys.net/yocto-docs/ref-manual/ref-system-requirements.html#downloading-a-pre-built-buildtools-tarball).
