SUMMARY = "A receipe to build device tree overlay"
LICENSE = "MIT"

inherit devicetree

COMPATIBLE_MACHINE = "raspberrypi4"
SRC_URI = "file://lcd1602-overlay.dts"

do_install:append() {
    install -d ${D}/boot/overlays
    install -m 0644 "${B}/lcd1602-overlay.dtbo" "${D}/boot/overlays/lcd1602-overlay.dtbo"
}

do_deploy:append() {
    install -d ${DEPLOYDIR}/overlays
    install -m 0644 "${B}/lcd1602-overlay.dtbo" "${DEPLOYDIR}/overlays/lcd1602-overlay.dtbo"
}