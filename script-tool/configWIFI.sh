if [ -z "$1"]
	then 
	echo "iwconfig [interface]"
	exit -1
fi
sudo iwconfig wlan1 ap $1

