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

MACHINE="${1:-raspberrypi4-64}"
CONFLINE="MACHINE = \"${MACHINE}\""

cat conf/local.conf | grep "${CONFLINE}" > /dev/null
local_conf_info=$?

if [ $local_conf_info -ne 0 ];then
	echo "Append ${CONFLINE} in the local.conf file"
	echo ${CONFLINE} >> conf/local.conf
	
else
	echo "${CONFLINE} already exists in the local.conf file"
fi


# bitbake-layers show-layers | grep "meta-raspberrypi" > /dev/null
# layer_info=$?

if [ $layer_info -ne 0 ];then
	echo "Adding meta-raspberrypi layer"
	bitbake-layers add-layer ../meta-raspberrypi
	bitbake-layers add-layer ../meta-openembedded/meta-oe
	bitbake-layers add-layer ../meta-openembedded/meta-python
	# bitbake-layers add-layer ../meta-aesd
else
	echo "meta-raspberrypi layer already exists"
fi

bitbake core-image-full-cmdline
cp -p ${PWD}/tmp/deploy/images/raspberrypi4-64/core-image-full-cmdline-raspberrypi4-64.wic.bz2 /media/sf_shared/core-image-full-cmdline-raspberrypi4-64.wic.bz2