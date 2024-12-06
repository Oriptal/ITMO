#!/bin/bash

function my_tree() {
    local directory="$1"
    local level="${2:-0}"

    if [ -z "$directory" ]; then
        directory="."
    fi

    for entry in "$directory"/*; do
        base_entry=$(basename "$entry")
        for ((i=0; i<level; i++)); do
            echo -n "|   "
        done
        echo "|-- $base_entry"

        if [ -d "$entry" ]; then
            my_tree "$entry" $((level+1))
        fi
    done
}

# Указать путь к директории, которую нужно отобразить
read -p "Введите путь к директории: " directory_path
my_tree "$directory_path"
