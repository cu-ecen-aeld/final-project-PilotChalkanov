SRC_URI += "file://lcd1602-overlay.dts;subdir=git/arch/${ARCH}/boot/dts/overlays"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

KERNEL_DEVICETREE += "overlays/lcd1602-overlay.dtbo"

