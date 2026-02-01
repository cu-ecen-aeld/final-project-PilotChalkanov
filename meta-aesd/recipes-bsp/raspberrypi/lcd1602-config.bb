PACKAGES = "${PN}"
FILES_${PN} = "/"
SUMMARY = "Append lcd1602 overlay to config.txt"
DESCRIPTION = "Appends dtoverlay=lcd1602-overlay to /boot/config.txt on Raspberry Pi images."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://lcd1602-config.txt"

S = "${WORKDIR}"

inherit rootfs-postcommands

ROOTFS_POSTPROCESS_COMMAND += "append_lcd1602_overlay_to_config_txt;"

append_lcd1602_overlay_to_config_txt() {
    if [ -f ${IMAGE_ROOTFS}/boot/config.txt ]; then
        echo 'dtoverlay=lcd1602-overlay' >> ${IMAGE_ROOTFS}/boot/config.txt
    fi
}
