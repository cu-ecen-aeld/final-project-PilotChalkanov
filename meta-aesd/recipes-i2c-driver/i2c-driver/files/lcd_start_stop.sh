#!/bin/sh

case "$1" in
    start)
        echo "Start lcd"
        /usr/bin/lcd1602_load
        ;;
    stop)
        echo "Stop lcd"
        /usr/bin/lcd1602_unload
        ;;
    *)
        echo "Usage: $0 {start|stop}"
    exit 1
esac

exit 0