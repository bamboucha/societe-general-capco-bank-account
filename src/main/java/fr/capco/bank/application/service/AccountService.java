package fr.capco.bank.application.service;

import fr.capco.bank.domain.model.Account;
import fr.capco.bank.domain.model.Operation;
import fr.capco.bank.domain.port.input.AccountUseCasePort;
import fr.capco.bank.domain.port.output.OperationPersistencePort;

import java.math.BigDecimal;

public class AccountService implements AccountUseCasePort {

    private final Account account  = new Account();
    private final OperationPersistencePort operationPort;

    public AccountService(OperationPersistencePort operationPort) {
        this.operationPort = operationPort;
    }

    public void deposit(BigDecimal amount) {
        account.deposit(amount);
        Operation lastOperation = account.getLastOperation();
        operationPort.save(lastOperation);
    }

    public void withdraw(BigDecimal amount) {
        account.withdraw(amount);
        Operation lastOperation = account.getLastOperation();
        operationPort.save(lastOperation);
    }


    public void printStatement() {
        System.out.println("DATE | AMOUNT | BALANCE");
        for (Operation op : operationPort.findAll()) {
            System.out.println(op);
        }
    }
}