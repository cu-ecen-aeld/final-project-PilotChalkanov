import threading
import time
import logging

AHT21_DEV = "/dev/aht21"
LCD1602_DEV = "/dev/lcd1602"
INTERVAL = 5  # seconds

logging.basicConfig(
    filename="/var/log/weather_app.log",
    level=logging.INFO,
    format="%(asctime)s %(levelname)s: %(message)s"
)

def fetch_and_write():
    while True:
        try:
            with open(AHT21_DEV, "r") as sensor:
                data = sensor.read().strip()
            with open(LCD1602_DEV, "w") as lcd:
                lcd.write(data)
            logging.info(f"Read from {AHT21_DEV}: {data} | Written to {LCD1602_DEV}")
        except Exception as e:
            logging.error(f"Error: {e}")
        time.sleep(INTERVAL)

def main():
   fetch_and_write()

if __name__ == "__main__":
    main()
