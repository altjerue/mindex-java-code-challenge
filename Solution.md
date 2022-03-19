# Solution

There is brief description of the solution. 

## Task 1

For this task:

1. Created the new class [ReportingStructure](src/main/java/com/mindex/challenge/data/ReportingStructure.java) with the methods needed to retrieve an `Employee` and their reports
2. Created the [ReportingStructureService](src/main/java/com/mindex/challenge/service/ReportingStructureService.java) interface
3. Created the [ReportingStructureController](src/main/java/com/mindex/challenge/controller/ReportingStructureController.java) class with the READ endpoint for our reporting structure
4. Created the [ReportingStructureServiceImpl](src/main/java/com/mindex/challenge/service/impl/ReportingStructureServiceImpl.java) class that will read an employee ID and create the `numberOfReports` instance, and calculates the total number of direct reports via a recursive function
5. Created the test [ReportingStructureServiceImpl](src/test/java/com/mindex/challenge/service/impl/ReportingStructureServiceImplTest.java) to check the above implementation

## Task 2

For this task:

1. Created the new class [Compensation](src/main/java/com/mindex/challenge/data/Compensation.java) with the methods needed to retrieve an `Employee` and their reports, and the new attributes: salary, effective date and date
2. Created the [CompensationRepository](src/main/java/com/mindex/challenge/dao/CompensationRepository.java) and [CompensationService](src/main/java/com/mindex/challenge/service/CompensationService.java) interfaces
3. Created the [CompensationController](src/main/java/com/mindex/challenge/controller/CompensationController.java) class with the CREATE and READ endpoints for our compensation structure
4. Created the [CompensationServiceImpl](src/main/java/com/mindex/challenge/service/impl/CompensationServiceImpl.java) class that will create the compensation, along with its attributes, or read the employee ID and return their compensation.
5. Created the test [CompensationServiceImpl](src/test/java/com/mindex/challenge/service/impl/CompensationServiceImplTest.java) to check the above implementation