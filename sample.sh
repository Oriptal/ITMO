#!/bin/bash
read -p 'Enter the name of the subject: ' subject_name
if [ -d "$subject_name" ]; then
	if [ -r "$subject_name" ] && [ -x "$subject_name" ]; then
        	cd "$subject_name"
    		echo "Success! You've chosen a '$subject_name' property!"
	else
        	echo "Error: You haven't got permission for '$subject_name'!"
    	fi
	read -p 'Enter the name for the topic: ' topic_name
	read -p 'Enter the date for the topic: ' topic_date
	topic_number=$(ls -l | wc -l)
	full_name="$topic_number. $topic_name ($topic_date)"
	mkdir "$full_name"
	cd "$full_name"
	mkdir Report
	cp ~/Labs/Samples/report.tex Report/report.tex
	if [ "$subject_name" == "FPA" ] || [ "$subject_name" == "Programming" ] || [ "$subject_name" == "Informatics" ]; then
		mkdir Code
	fi
	if [ "$subject_name" == "Informatics" ]; then
		cp ~/Labs/Samples/Annotation.doc Annotation.doc
		cp ~/Labs/Samples/main.cpp Code/
	fi
	if [ "$subject_name" == "Programming" ]; then
		cp ~/Labs/Samples/Main.java Code/
	fi
	cd ..
	echo "Success! New topic was added."
else
    echo "Error: The directory '$subject_name' doesn't exist!"
fi
