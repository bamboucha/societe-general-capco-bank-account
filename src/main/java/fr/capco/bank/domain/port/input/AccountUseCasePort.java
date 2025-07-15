package fr.capco.bank.domain.port.input;

import java.math.BigDecimal;

public interface AccountUseCasePort {

    void deposit(BigDecimal amount);

    void withdraw(BigDecimal amount);

    void printStatement();
}
