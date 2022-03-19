# Coding Challenge
## What's Provided
A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped 
with data. The application contains information about all employees at a company. On application start-up, an in-memory 
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

### How to Run
The application may be executed by running `gradlew bootRun`.

### How to Use
The following endpoints are available to use:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```
The Employee has a JSON schema of:
```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```
For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement
Clone or download the repository, do not fork it.

### Task 1
Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of 
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example, 
given the following employee structure:
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4. 

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return 
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will 
not be persisted.

### Task 2
Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create 
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the 
Compensation from the persistence layer.

## Delivery
Please upload your results to a publicly accessible Git repo. Free ones are provided by Github and Bitbucket.

-------------------------------------------------------------------------------

# Solution

There is brief description of the solution.

## Task 1

For this task:

1. Created the new class [ReportingStructure](src/main/java/com/mindex/challenge/data/ReportingStructure.java) with the methods needed to retrieve an `Employee` and their reports
2. Created the [ReportingStructureService](src/main/java/com/mindex/challenge/service/ReportingStructureService.java) interface
3. Created the [ReportingStructureController](src/main/java/com/mindex/challenge/controller/ReportingStructureController.java) class with the READ endpoint for our reporting structure
4. Created the [ReportingStructureServiceImpl](src/main/java/com/mindex/challenge/service/impl/ReportingStructureServiceImpl.java) class that will read an employee ID and create the `numberOfReports` instance, and calculates the total number of direct reports via a recursive function
5. Created the test [ReportingStructureServiceImpl](src/test/java/com/mindex/challenge/service/impl/ReportingStructureServiceImplTest.java) to check the above implementation

The following endpoints are available to use for Reporting structure:
```
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/reporting/{id}
    * RESPONSE: Employee
```

### Test

Running in the terminal
```shell
$ curl -X GET localhost:8080/reporting/16a596ae-edd3-4847-99fe-c4518e82c86f
```
gives the following output in `json` format
```json
{
  "employee": {
    "employeeId": "16a596ae-edd3-4847-99fe-c4518e82c86f",
    "firstName": "John","lastName":"Lennon",
    "position": "Development Manager",
    "department": "Engineering",
    "directReports": [
      {
        "employeeId":"b7839309-3348-463b-a7e3-5de1c168beb3",
        "firstName":null,
        "lastName":null,
        "position":null,
        "department":null,
        "directReports":null
      },
      {
        "employeeId":"03aa1462-ffa9-4978-901b-7c001562cf6f",
        "firstName":null,
        "lastName":null,
        "position":null,
        "department":null,
        "directReports":null
      }
    ]
  },
  "numberOfReports":4
}
```
While running
```shell
$ curl -X GET localhost:8080/reporting/03aa1462-ffa9-4978-901b-7c001562cf6f
```
gives
```json
{
  "employee":
  {
    "employeeId":"03aa1462-ffa9-4978-901b-7c001562cf6f",
    "firstName":"Ringo",
    "lastName":"Starr",
    "position":"Developer V",
    "department":"Engineering",
    "directReports" : [
      {
        "employeeId":"62c1084e-6e34-4630-93fd-9153afb65309",
        "firstName":null,
        "lastName":null,
        "position":null,
        "department":null,
        "directReports":null
      },
      {
        "employeeId":"c0c2293d-16bd-4603-8e08-638a9d18b22c",
        "firstName":null,
        "lastName":null,
        "position":null,
        "department":null,
        "directReports":null
      }
    ]
  },
  "numberOfReports":2
}
```

## Task 2

For this task:

1. Created the new class [Compensation](src/main/java/com/mindex/challenge/data/Compensation.java) with the methods needed to retrieve an `Employee` and their reports, and the new attributes: salary, effective date and date
2. Created the [CompensationRepository](src/main/java/com/mindex/challenge/dao/CompensationRepository.java) and [CompensationService](src/main/java/com/mindex/challenge/service/CompensationService.java) interfaces
3. Created the [CompensationController](src/main/java/com/mindex/challenge/controller/CompensationController.java) class with the CREATE and READ endpoints for our compensation structure
4. Created the [CompensationServiceImpl](src/main/java/com/mindex/challenge/service/impl/CompensationServiceImpl.java) class that will create the compensation, along with its attributes, or read the employee ID and return their compensation.
5. Created the test [CompensationServiceImpl](src/test/java/com/mindex/challenge/service/impl/CompensationServiceImplTest.java) to check the above implementation

The following endpoints are available to use:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/compensation
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/compensation/{id}
    * RESPONSE: Employee
```

### Test

By running in the terminal
```shell
$ curl -X GET localhost:8080/compensation/16a596ae-edd3-4847-99fe-c4518e82c86f
```
we get the following error message in `json` form
```json
{
  "timestamp":"2022-03-19T01:53:03.897+0000",
  "status":500,
  "error":"Internal Server Error",
  "message":"No compensation for employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f",
  "path":"/compensation/16a596ae-edd3-4847-99fe-c4518e82c86f"
}
```

If we run
```shell
$ curl -X POST localhost:8080/compensation -H 'Content-type:application/json' -d '{"employeeId":"16a596ae-edd3-4847-99fe-c4518e82c86f","salary":86.42,"effectiveDate":"2022-03-19T01:53:03.897+0000"}'
$ curl -X GET localhost:8080/compensation/16a596ae-edd3-4847-99fe-c4518e82c86f
```
from the last command we get the following output in `json` form
```json
{
  "employeeId":"16a596ae-edd3-4847-99fe-c4518e82c86f",
  "salary":86.42,
  "effectiveDate":"2022-03-19T01:53:03.897+0000"
}
```