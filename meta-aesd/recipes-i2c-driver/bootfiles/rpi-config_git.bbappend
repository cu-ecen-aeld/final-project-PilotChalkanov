# Enable I2C for LCD1602
RPI_EXTRA_CONFIG:append = "\n\
# Enable I2C for LCD1602\n\
dtparam=i2c_arm=on\n\
dtparam=i2c_arm_baudrate=100000\n\
dtoverlay=lcd1602-overlay\n\
"
