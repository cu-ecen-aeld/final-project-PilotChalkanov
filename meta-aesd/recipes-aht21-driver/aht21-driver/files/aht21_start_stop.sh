#!/bin/sh

case "$1" in
    start)
        echo "Start aht21"
        /usr/bin/aht21_load
        ;;
    stop)
        echo "Stop aht21"
        /usr/bin/aht21_unload
        ;;
    *)
        echo "Usage: $0 {start|stop}"
    exit 1
esac

exit 0