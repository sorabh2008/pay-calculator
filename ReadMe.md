# Introduction

Pay Calculator application is a basic Java based command line application developed following TDD approach. This application takes a CSV file as an input. CSV file should have at least five attributes - First Name, Last Name, Annual Salary, Super Rate and Pay period. The application then performs the payment calculation and outputs the salary per on per month basis. Application will prompt error messages if the input is not in correct format. 

# Dependencies
```
junit-4.11.jar
hamcrest-all-1.3.jar
```

# Input
All input has to be in a comma separated format. 

### Input Format
Schema:
```
first name, last name, annual salary, super rate, pay period
```
e.g.
```
David,Rudd,60050,9%,01 March – 31 March
```

# Run the application
- Full path of input file is required if it's not in the same directory.
- Application should have write access on the directory.
```
java -jar CalculatorApp.jar input.csv
```

# Output
All output is 
# Exceptions
Informatory messages have been configured for erroneous states of application.
- PAYCAL_E_001: Incorrect input format.
- PAYCAL_E_002: Incorrect number format in CSV.
- PAYCAL_E_003: Input file missing.
- PAYCAL_E_004: Unknown pay frequency.
- PAYCAL_APP_500: Something went wrong, please try again.


 
