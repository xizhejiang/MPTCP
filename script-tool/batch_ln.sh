#!/bin/bash
#tcp = 0, mptcp = 1
protocal=$1%2
echo $protocal
target=$2
total=$3
current_folder=`pwd`
echo $current_folder

for ((i=$protocal;i<$total;i=i+2)) 
do
    mkdir $i
    ln_target=$current_folder"/"$i
    ln -s $current_folder"/"$i $current_folder"/"$target
    #echo $current_folder
done




#!/bin/bash
#tcp = 0, mptcp = 1
protocal=$1%2
echo $protocal
target=$2
total=$3
current_folder=`pwd`
echo $current_folder

for ((i=$protocal;i<$total;i=i+2)) 
do
    mkdir $i
    ln_target=$current_folder"/"$i
    ln -s $current_folder"/"$i $current_folder"/"$target
    #echo $current_folder
done




#!/bin/bash
#tcp = 0, mptcp = 1
protocal=$1%2
echo $protocal
target=$2
total=$3
current_folder=`pwd`
echo $current_folder

for ((i=$protocal;i<$total;i=i+2)) 
do
    mkdir $i
    ln_target=$current_folder"/"$i
    ln -s $current_folder"/"$i $current_folder"/"$target
    #echo $current_folder
done




#!/bin/bash
#tcp = 0, mptcp = 1
protocal=$1%2
echo $protocal
target=$2
total=$3
current_folder=`pwd`
echo $current_folder

for ((i=$protocal;i<$total;i=i+2)) 
do
    mkdir $i
    ln_target=$current_folder"/"$i
    ln -s $current_folder"/"$i $current_folder"/"$target
    #echo $current_folder
done

