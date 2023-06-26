# Multi-MicroServices Project

## Config Service
#### Port: 8100
All other services fetch config from this service: 
- details service: details-dev.properties
- discovery service: discovery-service-dev.properties
- gateway service: gateway-dev.properties
- hibernate-student service: hibernate-student-dev.properties
- search service: search-dev.properties
- university service: university-dev.properties

## Hibernate-Student Service

### API Endpoints

#### Base URL: /students
#### Port: 8081
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
Example:
```
http://localhost:9001/studentanduniversity/search/china
```




