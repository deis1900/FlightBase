# FlightBase
Flight list (with airplanes and air_companies)

# configuration (for create MySQL database)
docker run -d --name air -p 3306:3306 -e MYSQL_ROOT_PASSWORD=Security123 -e MYSQL_DATABASE=flightbase mysql:5.7.33

# go to mysql terminal
docker exec -it air bash
# root - username
mysql -u root -p
# enter - password from mysql
Security123

## create database
CREATE DATABASE IF NOT EXISTS flightbase;

## check databases
show databases;






