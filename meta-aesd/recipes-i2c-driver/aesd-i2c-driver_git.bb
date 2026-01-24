# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ed1a118f474eea5e159b560c339329b"
INSANE_SKIP_${PN} += "license license-checksum"
INSANE_SKIP_${PN} += "license"

SRC_URI = "git://git@github.com/PilotChalkanov/i2c-lcd-drivers.git;protocol=ssh;branch=develop \
           file://lcd_start_stop.sh \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "e3c8cd8e6a75f7d5fe514923aa462713535279e4"

S = "${WORKDIR}/git"

inherit module

RPROVIDES:${PN} += "kernel-module-lcd1602-${KERNEL_VERSION}"
EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_DIR} -C ${STAGING_KERNEL_DIR} M=${S}/lcd1602"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "lcd_start_stop"

FILES:${PN} += "${sysconfdir}/init.d/lcd_start_stop"
FILES:${PN} += "${bindir}/lcd1602_load ${bindir}/lcd1602_unload"
FILES:${PN} += "${base_libdir}/modules/${KERNEL_VERSION}/extra/*"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install() {
      install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
      install -m 0644 ${S}/driver/*.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

      install -d ${D}${sysconfdir}/init.d
      install -m 0755 ${WORKDIR}/lcd_start_stop.sh ${D}${sysconfdir}/init.d/lcd_start_stop

      install -d ${D}${bindir}
      install -m 0755 ${S}/driver/lcd1602_load ${D}${bindir}/lcd1602_load
      install -m 0755 ${S}/driver/lcd1602_unload ${D}${bindir}/lcd1602_unload
}