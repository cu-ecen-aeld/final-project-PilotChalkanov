#!/bin/bash
# Script to build image for a chosen MACHINE (defaults to qemuarm64).
# Author: Siddhant Jajoo.
# Modified by: Chalkanov Nikolay

# Update and initialize submodules
git submodule init
git submodule sync
git submodule update --init --recursive

# local.conf won't exist until this step on first execution
source poky/oe-init-build-env



# Set MACHINE variable and overlay in local.conf
MACHINE="${1:-raspberrypi4-64}"
CONF_FILE="conf/local.conf"

# Function to append a line if not present
add_conf_line() {
    local line="$1"
    grep -F -- "$line" "$CONF_FILE" > /dev/null || echo "$line" >> "$CONF_FILE"
}

# Ensure MACHINE is set in local.conf
add_conf_line '# This sets the default machine to be raspberrypi4-64:'
add_conf_line "MACHINE ??= \"$MACHINE\""
add_conf_line ''
add_conf_line '# Enable I2C and LCD1602 overlay'
add_conf_line 'ENABLE_I2C = "1"'
add_conf_line 'RPI_EXTRA_CONFIG += "dtparam=i2c_arm=on"'
add_conf_line 'RPI_EXTRA_CONFIG += "dtparam=i2c_arm_baudrate=100000"'
add_conf_line 'RPI_EXTRA_CONFIG += "dtoverlay=lcd1602-overlay"'

echo "Adding meta-raspberrypi layer"
bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers add-layer ../meta-aesd

bitbake core-image-aesd
