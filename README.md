# Bank Account Kata

This project contains an implementation of the Bank Account Kata using Java in a hexagonal architecture using unit tests.

## Kata Description

The Bank Account Kata involves creating a personal banking system that allows you to make deposits, withdrawals, check the
account balance, and view your transaction history.

## Dependencies

- Java 23
- Spring boot 3.3.0
- H2 DB
- Junit

### Features

The main features of the Bank Account Kata include:

- Deposit and Withdrawal
- Account statement (date, amount, balance)
- Statement printing

### Test Scenarios

The test scenarios for the Bank Account Kata are as follows (easy to read in test cases):

- Deposit a positive amount into an empty account.
- Deposit a negative amount (should throw an exception).
- Withdraw a positive amount from an account with sufficient balance.
- Withdraw a negative amount (should throw an exception).
- Withdraw an amount greater than the available balance (should throw an exception).
- Check balance in concurrent operations
- Print the transaction history.
- Attempt to deposit/withdraw a negative amount (should throw an exception).

## Prerequisites

To run the Bank Account Kata, you will need the following:

- Java JDK (version 8 or higher)
- Maven (to execute the tests)

## Usage Instructions

1. Clone the project from GitHub:

```console
git clone https://github.com/bamboucha/societe-general-capco-bank-account.git
```

2. Run the tests:

```console
mvn clean test
```