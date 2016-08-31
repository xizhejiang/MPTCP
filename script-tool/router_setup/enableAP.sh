#!/bin/sh

#set up wifi card
ifconfig wlan0 192.168.1.1 netmask 255.255.255.0
#setup dhcp server
dhcpd wlan0 -pf /var/run/dhcp-server/dhcpd.pid
#open ip forward
bash -c "echo 1 > /proc/sys/net/ipv4/ip_forward"
#set up nat
iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE



/bin/sleep 5
/usr/sbin/service hostapd stop
nmcli nm wifi off
rfkill unblock wlan
/bin/sleep 5
hostapd ~/hostapd.conf
