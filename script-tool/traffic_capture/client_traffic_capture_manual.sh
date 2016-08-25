#!/bin/bash

counter=1
test_name="test.pcap"
output_file=${counter}.${test_name}
#once start the script, start tcpdump
echo "start"
tcpdump -i any -s 0 -w $output_file & 
counter=$((counter+1))


while :
do
    read -t 1 -n 1 key

    if [[ $key = s  ]]
    then
        #kill previous tcpdump process
        #start a new tcpdump
        echo "log #$counter done"
        echo "close and start"
        killall tcpdump
        tcpdump -i any -s 0 -w $output_file & 
        counter=$((counter+1))
        output_file=${counter}.${test_name}
    fi
    key=x
done

