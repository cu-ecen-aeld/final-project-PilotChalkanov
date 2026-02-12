inherit core-image
IMAGE_INSTALL:append = " i2c-tools"
KERNEL_MODULE_AUTOLOAD:raspberrypi4-64 = " i2c-dev i2c-bcm2711"
CORE_IMAGE_EXTRA_INSTALL += "aesd-i2c-driver"

# Add the custom overlay to the boot partition
IMAGE_BOOT_FILES:append = " lcd1602-overlay.dtbo;overlays/lcd1602-overlay.dtbo"

inherit extrausers
# See https://docs.yoctoproject.org/singleindex.html#extrausers-bbclass
# We set a default password of root to match our busybox instance setup
# Don't do this in a production image
# PASSWD below is set to the output of
# printf "%q" $(mkpasswd -m sha256crypt root) to hash the "root" password
# string

EXTRA_USERS_PARAMS = "usermod -p '\$1\$rTZZJmWV\$b36TxGIDt4YqX/oeoLIYI0' root;"