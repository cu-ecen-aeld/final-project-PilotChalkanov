#!/bin/sh
### BEGIN INIT INFO
# Provides:          weather_app
# Required-Start:    $network $remote_fs $syslog
# Required-Stop:     $network $remote_fs $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: AHT21 to LCD1602 daemon
### END INIT INFO

DAEMON=/usr/bin/python3
DAEMON_ARGS="-m aesd_weather_app.main"
PIDFILE=/var/run/weather_app.pid
NAME=weather_app

case "$1" in
    start)
        echo "Starting $NAME"
        start-stop-daemon -S -b -m -p $PIDFILE -a $DAEMON -- $DAEMON_ARGS
        ;;
    stop)
        echo "Stopping $NAME"
        start-stop-daemon -K -p $PIDFILE
        ;;
    restart)
        $0 stop
        sleep 1
        $0 start
        ;;
    *)
        echo "Usage: $0 {start|stop|restart}"
        exit 1
esac

exit 0
