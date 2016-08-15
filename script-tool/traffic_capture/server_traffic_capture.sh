#!/bin/bash

#host="128.86.177.96"
#host="45.55.173.10:80"

#host="178.62.42.127:80"
flag="ESTABLISHED"
#flag="SYN_SENT"
counter=0
trigger=0
limit=$1 #no of test
mkdir $2 #set the name of conductor
host1=$3 #set the ip of wlan0
host2=$4 #set the ip of wlan1
test_name="tcp.pcap" #tcp or mptcp

#add the filter to the tcpdump to cap only source ips
filter="(host $host1 or host $host2)"
output_file=$2"/"${counter}.${test_name}
tcpdump -i any -s 0 -w $output_file $filter & 
counter=$((counter+1))

while [ $counter -lt $limit ]
do 
    #add 'apache2' to only cap the server traffic
    command=$(netstat -nputw | grep -e $host1 -e $host2 | grep 'apache2' | grep $flag | wc -l)
    output_file=$2"/"${counter}.${test_name}
    if [ $command -gt 0 ] && [ $trigger -eq 0 ]; then
        echo "setup"
        #tcpdump -i any -s 0 -w $output_file & 
        trigger=1
        #counter=$((counter+1))
    fi

    if [ $command -eq 0 ] && [ $trigger -eq 1 ]; then
        echo "close"
        sleep 1
        killall tcpdump
        tcpdump -i any -s 0 -w $output_file $filter & 
        counter=$((counter+1))
        trigger=0
    fi

    
    #counter=$((counter+1))
    #echo $counter
    sleep 1
done




