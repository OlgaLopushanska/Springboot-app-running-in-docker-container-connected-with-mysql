 # Command to build docker image
docker build -t springboot-rest-api-with-mysql .

# Command to run this image in already created network(springboot-mysql-network it`s network name where i have mysql database)
docker run --network springboot-mysql-network --name springboot-mysql-contain -p 8080:8080 springboot-rest-api-with-mysql
It`s possible to interact with app through the port 8080 with postman
get request http://localhost:8080/api/users/1
post request http://localhost:8080/api/users


# Pull mysql from docker hub
docker pull mysql
# Create network
docker network create springboot-mysql-network
# Run mysql iin container in network
docker run --name mysqldb --network springboot-mysql-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=employeedb mysql
