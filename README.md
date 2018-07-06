# TransactionsApp

## About
TransactionApp is a simple SBT application that reads different types of transactions data from the file where each row in a file represents one financial transaction. There are multiple types of transactions. There could also be invalid rows in a file that do not conform to the expected row format. 

#### Structure of the file is:
- Each row represents one transaction
- Structure of a row (transaction format): ```<transaction id>;<transaction type>;<transaction amount>;<transaction_datetime>```

## Prerequisite

This application needs SBT as a basic need to run this application. That's it.

## How to Run

 - Download (and extract)/ Clone this project to your local
 - Goto the root directory of the project
 - Start SBT
     ```sbt```
 - Once you entered into SBT console, Just hit `run`
 
 Note: 
 - Since this application extends `App` and has only one entry point the application will directly run and prints the requured output on the console
 - For the given test data `transaction_details.txt` in the project, output should be as shown below
 ```
 WITHDRAWAL:
 06/07/2018 : 10000.0
07/07/2018 : 10000.0
REPAYMENT:
 05/07/2018 : -45460.25
21/06/2018 : -7000.0
05/06/2018 : 50000.75
DEPOSIT:
 01/07/2018 : 80000.0
06/07/2018 : 12000.0
TRANSFER:
 05/07/2018 : 9000.0
06/06/2018 : 15000.5

The oldest transaction(s) in the file: 
Transaction(4,WITHDRAWAL,10000.0,2018-07-07T00:00:00.000+05:30)
 
Number of invalid lines in the file: 3
```

 - To validate with the different input, please update `transaction_details.txt` accordingly and run the application again.


Thanks!
