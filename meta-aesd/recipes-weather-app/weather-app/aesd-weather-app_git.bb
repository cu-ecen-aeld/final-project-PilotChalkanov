DESCRIPTION = "AHT21 to LCD1602 daemon Python app"
SECTION = "apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fb90819ede98782f3960734292a6249c"

SRC_URI = "git://github.com/PilotChalkanov/aesd-weather-app.git;branch=feature/add-yocto-python-compatibility;protocol=https \
           file://weather_app_start_stop.sh"

SRCREV = "567cc11e61cd0e87bbc1dff9d5d3e3bad285be25"
PV = "0.1+git${SRCPV}"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta