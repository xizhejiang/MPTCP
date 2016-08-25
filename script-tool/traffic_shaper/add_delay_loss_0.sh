#!/bin/bash 

DEV=wlan0
DELAY=200ms

LOSS=4%


if [ "$1" = "loss" ]
then
    tc qdisc add dev $DEV root netem loss $LOSS
    exit
fi

if [ "$1" = "delay" ]
then
    tc qdisc add dev $DEV root netem delay $DELAY
    exit
fi


if [ "$1" = "status" ]
then
    tc -s qdisc ls dev $DEV
    tc -s class ls dev $DEV
    exit
fi


if [ "$1" = "stop" ] 
then
    # clean existing down- and uplink qdiscs, hide errors
    tc qdisc del dev $DEV root    
    tc -s qdisc ls dev $DEV
    exit
fi


