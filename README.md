# Multi-MicroServices Project

## Hibernate-Student

### API Endpoints

#### Base URL: /students

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
Endpoint: /students/{studentId}
Method: DELETE
Description: Deletes the student with the provided ID.
Return: A confirmation message.
