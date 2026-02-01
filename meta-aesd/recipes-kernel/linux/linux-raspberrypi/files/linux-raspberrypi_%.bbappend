FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://overlays/lcd1602-overlay.dts"
KERNEL_DEVICETREE += "overlays/lcd1602-overlay.dtbo"
