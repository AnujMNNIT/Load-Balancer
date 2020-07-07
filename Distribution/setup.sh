#!/bin/bash

echo "Compiling files..."
javac -cp ./dist/MiniProject.jar:./dist/lib/mysql-connector-java-5.1.23-bin.jar *.java

echo "Starting Worker processes..."
count=0
while IFS=, read _ port;do
	gnome-terminal --title="Worker $count" -e "bash -c 'java -cp ./dist/MiniProject.jar:./dist/lib/mysql-connector-java-5.1.23-bin.jar:./ Worker $port'" 1>/dev/null
	count=$((count+1))
done < worker_list.txt	

# Note: Small delay needed so as to let Workers launch completely and open Server Sockets.
sleep 0.5s

echo "Starting Load Balancer process..."
#Commandline parameter options for LoadBalancer: "RR" for Round Robin scheduling, "LC" for Least Connections scheduling.
gnome-terminal --title="Load Balancer" -e "bash -c 'java -cp ./dist/MiniProject.jar:./dist/lib/mysql-connector-java-5.1.23-bin.jar:./ LoadBalancer LC'" 1>/dev/null 
echo "Starting Client process..."
gnome-terminal --title="Client" -e "bash -c 'java -cp ./dist/MiniProject.jar:./dist/lib/mysql-connector-java-5.1.23-bin.jar:./ Client'" 1>/dev/null
echo "All processes running."
