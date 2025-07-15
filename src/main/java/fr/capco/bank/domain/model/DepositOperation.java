package fr.capco.bank.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class DepositOperation extends Operation {
    public DepositOperation(LocalDate date, BigDecimal amount, BigDecimal balance) {
        super(date, amount, balance);
    }
}