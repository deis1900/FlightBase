# FlightBase
Flight list (with airplanes and air_companies)

## All request mapping (and detalization) you can see by the link:
http://localhost:8082/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

## configuration (for create MySQL database)
docker run -d --name air -p 3306:3306 -e MYSQL_ROOT_PASSWORD=Security123 -e MYSQL_DATABASE=flightbase mysql:5.7.33

### go to mysql terminal
docker exec -it air bash
### root - username
mysql -u root -p
### enter - password from mysql
Security123

### create database
CREATE DATABASE IF NOT EXISTS flightbase;

### check databases
show databases;

##Download flightbase-0.0.1-SNAPSHOT.war
##Download Postman collection flightbase.postman_collection.json
##open terminal end enter
java -jar -flightbase-0.0.1-SNAPSHOT.war

##open file in Postman
flightbase.postman_collection.json




#####Not ready
###### create docker image (with flightbase application) where name is deis/flight
docker build -t deis/flight .

###### Run image with application(name is flight/App)
docker run --name flightApp -p 8082:8082 deis/flight --link air:flightbase









