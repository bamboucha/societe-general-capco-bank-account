package fr.capco.bank.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public sealed abstract class Operation permits DepositOperation, WithdrawalOperation {

    protected final LocalDate date;
    protected final BigDecimal amount;
    protected final BigDecimal balance;

    protected Operation(LocalDate date, BigDecimal amount, BigDecimal balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDate getDate() { return date; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getBalance() { return balance; }

    @Override
    public String toString() {
        return "%s | %s | %s".formatted(date, amount, balance);
    }
}