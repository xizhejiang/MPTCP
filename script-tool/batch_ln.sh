#!/bin/bash
#tcp = 0, mptcp = 1
protocal=$1%2
target=$2
total=$3
current_folder=`pwd`
echo $current_folder

for ((i=$protocal;i<$total;i=i+2)) 
do
    if [ ! -d "$i" ];then
        mkdir $i
    fi
    #ln_target=$current_folder"/"$i
    ln -s $target $current_folder"/"$i
    #echo $current_folder
done




