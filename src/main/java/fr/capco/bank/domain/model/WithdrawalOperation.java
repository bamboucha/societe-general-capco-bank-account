package fr.capco.bank.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class WithdrawalOperation extends Operation {

    public WithdrawalOperation(LocalDate date, BigDecimal amount, BigDecimal balance) {
        super(date, amount.negate(), balance);
    }

}