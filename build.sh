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


# Set MACHINE variable
MACHINE="${1:-raspberrypi4-64}"
CONF_FILE="conf/local.conf"

# Function to append a line if not present
add_conf_line() {
    local line="$1"
    grep -F -- "$line" "$CONF_FILE" > /dev/null || echo "$line" >> "$CONF_FILE"
}

# Ensure MACHINE is set
add_conf_line "MACHINE = \"${MACHINE}\""

# Ensure I2C and related config is present
add_conf_line ''
add_conf_line '#extra-packages for i2c tools'
add_conf_line ''
add_conf_line '# Enable I2C hardware interface in device tree'
add_conf_line 'ENABLE_I2C = "1"'
add_conf_line ''
add_conf_line '# Enable I2C on GPIO pins (I2C1)'
add_conf_line 'RPI_EXTRA_CONFIG:append = "\n dtparam=i2c_arm=on"'
add_conf_line ''
add_conf_line 'KERNEL_MODULE_AUTOLOAD:raspberrypi4-64 = " i2c-dev i2c-bcm2708"'
add_conf_line 'IMAGE_INSTALL:append = " i2c-tools"'
# add login
add_conf_line 'EXTRA_USERS_PARAMS = "usermod -p '\$1\$rTZZJmWV\$b36TxGIDt4YqX/oeoLIYI0' root;"'

echo "Adding meta-raspberrypi layer"
bitbake-layers add-layer ../meta-raspberrypi


bitbake core-image-full-cmdline
