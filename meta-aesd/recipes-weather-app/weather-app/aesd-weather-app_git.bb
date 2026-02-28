DESCRIPTION = "AHT21 to LCD1602 daemon Python app"
SECTION = "apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fb90819ede98782f3960734292a6249c"

SRC_URI = "git://github.com/PilotChalkanov/aesd-weather-app.git;branch=main;protocol=https \
           file://weather_app_start_stop.sh"

SRCREV = "fa4b9d9c4bdb51ead41643b380cfae1f6f164b8e"
PV = "0.1+git${SRCPV}"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "weather_app_start_stop"
INITSCRIPT_PARAMS:${PN} = "defaults 99"

FILES:${PN} += "${sysconfdir}/init.d/weather_app_start_stop ${PYTHON_SITEPACKAGES_DIR}/aesd_weather_app"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/weather_app_start_stop.sh ${D}${sysconfdir}/init.d/weather_app_start_stop
}

RDEPENDS:${PN} = "python3-core python3-requests python3-certifi ca-certificates"