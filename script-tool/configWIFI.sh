if [ -z "$1"]
	then 
	echo "iwconfig [interface] [mac]"
	exit -1
fi
sudo iwconfig $1 ap $2

