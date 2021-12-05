#!/bin/bash
last=999999999
count=0
while read line; do
    for word in $line; do
        if [ $word -ge $last ]; then
            ((count+=1))
        fi
        last=$word
    done
done <"day1.txt"
echo "$count"
