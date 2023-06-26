# Multi-MicroServices Project
#### This is a Multi-MicroServices Project with a Config server, API gateway, Discovery Service, Ribbon load balancer, Hystrix Circuit Breaker, and Swagger Documentation. Hibernate-Student and University Service is integrated into this Project.

## Config Service
#### Port: 8100
All other services fetch config from this service: 
- details service: details-dev.properties
- discovery service: discovery-service-dev.properties
- gateway service: gateway-dev.properties
- hibernate-student service: hibernate-student-dev.properties
- search service: search-dev.properties
- university service: university-dev.properties

## Discovery Service
#### Port: 8761/8200
#### Api Gateway is implemented for this Hibernate-Student service. We can use port localhost:8200/eureka/web to access the Eureka portal.
All other services register themself and discover other services with discovery service.

## API gateway Service
#### Port: 8200
#### service name: gateway
Entry point for other services:
- routes[0] for search service, path:/weather/**
- routes[1] for details service, path:/details/**
- routes[2] for hibernate-student service, path:/students/**
- routes[3] for discovery service, path:/eureka/web

## Hibernate-Student Service
To run this service, you need to config your data source and use the script in the resources folder to create tables and insert data.

### API Endpoints

#### Base URL: /students
#### Port: 8200/8081
#### Api Gateway is implemented for this Hibernate-Student service. We can use port 8200 to access it.
#### service name: hibernate-student

#### Create a new student
- Endpoint: /students
- Method: POST
- Body: StudentDTO
- Description: Adds a new student.<br><br>

Request body:
```
{
    "first_name": "weidong",
    "last_name": "wang",
    "email": "weidongwang@brandeis.edu",
}
```
#### Get all students
- Endpoint: /students
- Method: GET
- Description: Retrieves all students.
- Return: A list of StudentDTO.

#### Get a student
- Endpoint: /students/{studentId}
- Method: GET
- Description: Retrieves the student with the provided id.
- Return: The StudentDTO.

#### Update a student
- Endpoint: /students/{studentId}
- Method: PUT
- Body: StudentDTO
- Description: Updates the details of the student with the provided ID.
- Return: The updated StudentDTO.
Request body:
```
{
    "first_name": "weidong",
    "last_name": "wang",
    "email": "weidongwang@brandeis.edu",
}
```

#### Delete a student
- Endpoint: /students/{studentId}
- Method: DELETE
- Description: Deletes the student with the provided ID.
- Return: A confirmation message.
  
## University Service

### API Endpoints
#### Base URL: /university
#### Port: 8086
#### service name: university

#### Get all universities with a specific name
- Endpoint: /univeristy/search?name=<university_name>
- Method: GET
- Description: Get all universities with a specific name.<br><br>
Example:
```
http://localhost:8086/university/search?name=middle
```

#### Get all universities in a specific country
- Endpoint: /univeristy/search?country=<country>
- Method: GET
- Description: Get all universities in a specific country.<br><br>
Example:
```
http://localhost:8086/university/search?country=china
```

## Search Service
#### Base URL: /
#### Port: 9001
#### service name: search


#### Get all universities in a specific country and all students in DB
- Endpoint: /studentanduniversity/search/{country}
- Method: GET
- Description: Get all universities in a specific country and all students in DB with CompletableFuture + restTemplate
- Hystrix circuit breaker is implemented for this api
Example:
```
http://localhost:9001/studentanduniversity/search/china
```

#### Swagger documentation is implemented for SearchController
- visit http://localhost:9001/swagger-ui.html to get swagger documentation in JSON format
- visit http://localhost:9001//swagger-ui/index.html to get swagger documentation with UI


#### Ribbon is implemented for Search Service

## Details Service
#### Port: 8200/9400
#### service name: hibernate-student
#### Api Gateway is implemented for this Hibernate-Student service. We can use port 8200 to access it.

### API Endpoints

#### get weather

- Endpoint: /details
- Method: GET
- Description: get weather

- #### get weather by city id
- Endpoint: /details/{id}
- Method: GET
- Description: et weather by city id


- #### get port for weather details service
- Endpoint: /details/port
- Method: GET
- Description: get port for weather details service

## Author
Weidong Wang
Jun 25 2023
