#!/bin/bash
target=$1
total=$2
current_folder=`pwd`
echo $current_folder
for ((i=0;i<$total;i++)) 
do
    mkdir $i
    ln_target=$current_folder"/"$i
    ln -s $current_folder"/"$i $current_folder"/"$target
    #echo $current_folder
done




