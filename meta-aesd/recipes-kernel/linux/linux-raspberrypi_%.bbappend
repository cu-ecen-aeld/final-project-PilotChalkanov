FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://lcd1602-overlay.dts;subdir=git/arch/${ARCH}/boot/dts/overlays"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Add custom overlay to the kernel device tree list
RPI_KERNEL_DEVICETREE_OVERLAYS:append = " overlays/lcd1602-overlay.dtbo"
