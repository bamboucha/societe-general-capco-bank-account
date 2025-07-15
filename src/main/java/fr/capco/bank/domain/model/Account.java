package fr.capco.bank.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fr.capco.bank.domain.model.constant.Message.*;

public class Account {

    private final List<Operation> operations = new ArrayList<>();
    private BigDecimal balance = BigDecimal.ZERO;


    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_POSITIVE);
        }
        this.balance = this.getBalance().add(amount);
        operations.add(new DepositOperation(LocalDate.now(), amount, balance));
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_POSITIVE);
        }
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException(INSUFFICIENT_FUNDS);
        }
        this.balance = this.getBalance().subtract(amount);
       operations.add(new WithdrawalOperation(LocalDate.now(), amount.negate(), balance));
    }

    public Operation getLastOperation() {
        if (operations.isEmpty()) {
            throw new IllegalStateException(NO_OPERATIONS_AVAILABLE);
        }
        return operations.get(operations.size() - 1);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}