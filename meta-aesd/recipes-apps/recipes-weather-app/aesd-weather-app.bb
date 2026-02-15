DESCRIPTION = "AHT21 to LCD1602 daemon Python app"
SECTION = "apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bf72105a69d303b78352c6a39239bc69"

SRC_URI = "file://weather_app.py file://start_app.sh file://weather_app.service file://LICENSE"

S = "${WORKDIR}"
FILES:${PN} += "${systemd_unitdir}/system/weather_app.service"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/weather_app.py ${D}${bindir}/weather_app.py
    install -m 0755 ${WORKDIR}/start_app.sh ${D}${bindir}/start_app.sh
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/weather_app.service ${D}${systemd_unitdir}/system/weather_app.service
}

RDEPENDS_${PN} = "python3 python3-requests"
