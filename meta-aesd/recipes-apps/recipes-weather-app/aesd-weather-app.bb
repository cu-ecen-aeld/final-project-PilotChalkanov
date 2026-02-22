DESCRIPTION = "AHT21 to LCD1602 daemon Python app"
SECTION = "apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bf72105a69d303b78352c6a39239bc69"

SRC_URI = "file://weather_app.py file://start_app.sh file://weather_app_start_stop.sh file://LICENSE"

S = "${WORKDIR}"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "weather_app_start_stop"
INITSCRIPT_PARAMS:${PN} = "defaults 99"

FILES:${PN} += "${sysconfdir}/init.d/weather_app_start_stop"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/weather_app.py ${D}${bindir}/weather_app.py
    install -m 0755 ${WORKDIR}/start_app.sh ${D}${bindir}/start_app.sh
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/weather_app_start_stop.sh ${D}${sysconfdir}/init.d/weather_app_start_stop
}

RDEPENDS_${PN} = "python3 python3-requests"
