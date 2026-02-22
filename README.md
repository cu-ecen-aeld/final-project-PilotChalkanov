# i2c-yocto-build-main

This project provides a Yocto-based build system for embedded Linux development, targeting platforms such as the Raspberry Pi 4.
## Project Overview
[ECEA 5307 Final Project Overview](https://github.com/cu-ecen-aeld/final-project-PilotChalkanov/wiki/Project-Overview)

[ECEA 5307 Final Project Schedule](https://github.com/users/PilotChalkanov/projects/4/views/1)


## Features
- Yocto build scripts and configuration
- Support for Raspberry Pi 4
- Automated build and flash scripts
- Custom meta-layers for additional I2C functionality

## Getting Started
1. Clone the repository.
2. Run `build.sh` to build the image for your target device.
3. Use `flash.sh` to flash the built image to an SD card.

## Directory Structure
### Submodules
- `meta-raspberry`: This is the general hardware specific BSP overlay for the RaspberryPi device. 
- `poky/` : Poky build system
- `meta-openembedded` - Collection of layers for the OE-core universe
### Custom layers
- `meta-aesd` : Custom Yocto meta-layers with client drivers for various I2C devices
- `conf-templates/` : Example configuration files
- `build.sh`, `flash.sh` : Automation scripts

## Requirements
- Linux host system, preferably debian distro
- Yocto Project dependencies (see Yocto documentation)

## License
See individual meta-layer directories for license information.
