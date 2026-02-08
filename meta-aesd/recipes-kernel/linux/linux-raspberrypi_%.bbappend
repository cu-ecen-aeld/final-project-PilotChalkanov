FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://lcd1602-overlay.dts"

KERNEL_DEVICETREE += "overlays/lcd1602-overlay.dtbo"

do_configure:append() {
    install -d ${S}/arch/arm64/boot/dts/overlays
    install -m 0644 ${WORKDIR}/lcd1602-overlay.dts ${S}/arch/arm64/boot/dts/overlays/lcd1602-overlay.dts
}

