#!/bin/bash
WLAN0=192.168.1.3
WLAN1=192.168.1.4
DEV=wlan0

echo "reset interface qdisc"
tc qdisc del dev $DEV root

echo "install HTB qdisc"
tc qdisc add dev $DEV root handle 1: htb default 10

echo "root class"
tc class add dev $DEV parent 1: classid 1:1 htb rate 102400kbit

echo "LAN traffic"
#tc class add dev $DEV parent 1:1 classid 1:10 htb rate 102400kbit
#tc filter add dev $DEV parent 1:0 protocol ip prio 1 u32 match ip src 192.168.1.0/24 flowid 1:10

echo "wlan0 traffic"
tc class add dev $DEV parent 1:1 classid 1:11 htb rate 1000kbps ceil 1200kbps
#tc qdisc add dev $DEV parent 1:11 handle 10: netem loss 10%
tc filter add dev $DEV parent 1:0 protocol ip prio 1 u32 match ip dst $WLAN0 flowid 1:11

echo "wlan1 traffic"
tc class add dev $DEV parent 1:1 classid 1:12 htb rate 1000kbps ceil 1200kbps
#tc qdisc add dev $DEV parent 1:12 handle 20: netem loss 10%
tc filter add dev $DEV parent 1:0 protocol ip prio 1 u32 match ip dst $WLAN1 flowid 1:12

echo "other devices traffic"
tc class add dev $DEV parent 1:1 classid 1:10 htb rate 1000kbps ceil 1000kbps

echo "enable SFQ on leaves"
#tc qdisc add dev $DEV parent 1:10 handle 20: sfq perturb 60
#tc qdisc add dev $DEV parent 1:11 handle 21: sfq perturb 60
#tc qdisc add dev $DEV parent 1:12 handle 22: sfq perturb 60
