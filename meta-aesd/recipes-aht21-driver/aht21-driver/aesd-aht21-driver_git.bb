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
LIC_FILES_CHKSUM = "file://LICENSE;md5=fb90819ede98782f3960734292a6249c"
INSANE_SKIP_${PN} += "license license-checksum"
INSANE_SKIP_${PN} += "license"

SRC_URI = "git://git@github.com/PilotChalkanov/i2c-aht21-drivers.git;protocol=ssh;branch=feature/initial-temp-driver-implementation \
           file://aht21_start_stop.sh \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "1fc6033bb822ad31581c1c830ac35f2b443b0fee"

S = "${WORKDIR}/git"

inherit module

RPROVIDES:${PN} += "kernel-module-aht21-${KERNEL_VERSION}"
EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_DIR} -C ${STAGING_KERNEL_DIR} M=${S}/"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aht21_start_stop"

FILES:${PN} += "${sysconfdir}/init.d/aht21_start_stop"
FILES:${PN} += "${bindir}/aht21_load ${bindir}/aht21_unload"
FILES:${PN} += "${base_libdir}/modules/${KERNEL_VERSION}/extra/*"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install() {
      install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
      install -m 0644 ${S}/aht21/*.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

      install -d ${D}${sysconfdir}/init.d
      install -m 0755 ${WORKDIR}/lcd_start_stop.sh ${D}${sysconfdir}/init.d/aht21_start_stop

      install -d ${D}${bindir}
      install -m 0755 ${S}/aht21/aht21_load.sh ${D}${bindir}/aht21_load
      install -m 0755 ${S}/aht21/aht21_unload.sh ${D}${bindir}/aht21_unload
}