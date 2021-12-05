#!/bin/bash
count=0
while read line; do
    arr=($line)
    length=${#arr[@]}
    for ((i=3;i<length;i++)); do
        if [ ${arr[i]} -gt ${arr[i-3]} ]; then
            ((count+=1))
        fi
    done
done <"day1.txt"
echo "$count"
