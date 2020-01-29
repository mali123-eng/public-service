## Building a public-service with Spring Boot, Spring Security, JWT

![App Screenshot](screenshot.png)



## Steps to Setup the Spring Boot Back end app

1. **Clone the application**

	```bash
	git clone https://github.com/raushan-kumar802/public-service.git
	cd public-service
	```

2. **Create MySQL database**

	```bash
	create database public_service
	```

3. **Change MySQL username and password as per your MySQL installation**

	+ open `src/main/resources/application.properties` file.

	+ change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation

4. **Run the app**

	You can run the spring boot app by typing the following command -

	```bash
	mvn spring-boot:run
	```

	The server will start on port 8080.

	You can also package the application in the form of a `jar` file and then run it like so -

	```bash
	mvn package
	java -jar target/public-service-0.0.1-SNAPSHOT.jar
	```



