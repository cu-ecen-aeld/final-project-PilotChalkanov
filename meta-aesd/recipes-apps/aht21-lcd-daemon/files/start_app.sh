#!/bin/sh
# Start the AHT21-LCD daemon as a background process (daemon)
nohup python3 /usr/bin/weather_app.py > /var/log/weather_app.log 2>&1 &
