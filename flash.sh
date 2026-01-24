
set -e

MACHINE="${MACHINE:-${1:-raspberrypi4-64}}"
SD_DEVICE="${SD_DEVICE:-/dev/sdb}"
IMAGE_PATH="${PWD}/build/tmp/deploy/images/${MACHINE}/core-image-aesd-${MACHINE}.wic.bz2"

echo "Flashing image for MACHINE: $MACHINE to device: $SD_DEVICE"

if [ ! -f "$IMAGE_PATH" ]; then
	echo "Error: Image file not found: $IMAGE_PATH" >&2
	exit 1
fi

if [ ! -b "$SD_DEVICE" ]; then
	echo "Error: SD device not found: $SD_DEVICE" >&2
	exit 1
fi

bzcat "$IMAGE_PATH" | sudo dd of="$SD_DEVICE" bs=4M conv=fsync status=progress
echo "Flashing completed successfully."
