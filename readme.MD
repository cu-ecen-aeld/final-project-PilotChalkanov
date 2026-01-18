# i2c-yocto-build-main

This project provides a Yocto-based build system for embedded Linux development, targeting platforms such as the Raspberry Pi 4.
## Project Overview
[ECEA 5307 Final Project Overview](https://github.com/cu-ecen-aeld/final-project-PilotChalkanov/wiki/Project-Overview)


## Features
- Yocto build scripts and configuration
- Support for Raspberry Pi 4 and other platforms
- Automated build and flash scripts
- Custom meta-layers for additional functionality

## Getting Started
1. Clone the repository.
2. Run `build.sh` to build the image for your target device.
3. Use `flash.sh` to flash the built image to an SD card.

## Directory Structure
- `meta-*` : Custom Yocto meta-layers
- `poky/` : Poky build system
- `conf-templates/` : Example configuration files
- `build.sh`, `flash.sh` : Automation scripts

## Requirements
- Linux host system
- Yocto Project dependencies (see Yocto documentation)

## License
See individual meta-layer directories for license information.