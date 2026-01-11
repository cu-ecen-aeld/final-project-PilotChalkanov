#!/bin/bash
# Setup script to initialize build configuration files
# This script copies template configuration to build/conf/ if needed
# Author: Chalkanov Nikolay

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
TEMPLATE_DIR="${SCRIPT_DIR}/conf-templates"
BUILD_CONF_DIR="${SCRIPT_DIR}/build/conf"

echo "=========================================="
echo "Yocto Build Configuration Setup"
echo "=========================================="

# Ensure build directory structure exists
if [ ! -d "${BUILD_CONF_DIR}" ]; then
    echo "Creating build/conf directory..."
    mkdir -p "${BUILD_CONF_DIR}"
fi

# Check if local.conf already exists
if [ -f "${BUILD_CONF_DIR}/local.conf" ]; then
    echo ""
    echo "WARNING: local.conf already exists at ${BUILD_CONF_DIR}/local.conf"
    echo ""
    read -p "Do you want to overwrite it with the template? [y/N]: " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        echo "Skipping local.conf - keeping existing file"
    else
        echo "Backing up existing local.conf to local.conf.backup"
        cp "${BUILD_CONF_DIR}/local.conf" "${BUILD_CONF_DIR}/local.conf.backup"
        echo "Copying template local.conf..."
        cp "${TEMPLATE_DIR}/local.conf.sample" "${BUILD_CONF_DIR}/local.conf"
        echo "✓ local.conf installed from template"
    fi
else
    echo "Installing local.conf from template..."
    cp "${TEMPLATE_DIR}/local.conf.sample" "${BUILD_CONF_DIR}/local.conf"
    echo "✓ local.conf installed"
fi

echo ""
echo "=========================================="
echo "Configuration setup complete!"
echo "=========================================="
echo ""
echo "Next steps:"
echo "  1. Review and customize build/conf/local.conf if needed"
echo "  2. Run ./build.sh [MACHINE] to start the build"
echo "     Example: ./build.sh raspberrypi4-64"
echo ""
