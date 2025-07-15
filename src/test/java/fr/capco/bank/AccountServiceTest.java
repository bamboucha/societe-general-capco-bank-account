package fr.capco.bank;

import fr.capco.bank.application.service.AccountService;
import fr.capco.bank.domain.model.DepositOperation;
import fr.capco.bank.domain.model.Operation;
import fr.capco.bank.domain.model.WithdrawalOperation;
import fr.capco.bank.domain.port.input.AccountUseCasePort;
import fr.capco.bank.domain.port.output.OperationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private AccountUseCasePort service;
    private OperationPersistencePort persistence;

    @BeforeEach
    void setUp() {
        persistence = mock(OperationPersistencePort.class);
        service = new AccountService(persistence);
    }

    @Test
    void printStatement_shouldOutputFormattedLines() {
        // Préparation des données simulées
        Operation op1 = new DepositOperation(LocalDate.of(2025, 6, 17), new BigDecimal("100.00"), new BigDecimal("100.00"));
        Operation op2 = new WithdrawalOperation(LocalDate.of(2025, 6, 18), new BigDecimal("50.00"), new BigDecimal("50.00"));
        when(persistence.findAll()).thenReturn(List.of(op1, op2));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        service.printStatement();

        String output = out.toString().trim();
        assertTrue(output.contains("DATE | AMOUNT | BALANCE"));
        assertTrue(output.contains("2025-06-17 | 100.00 | 100.00"));
        assertTrue(output.contains("2025-06-18 | -50.00 | 50.00"));

        System.setOut(System.out);
    }

    @Test
    void deposit_shouldCallSaveWithCorrectOperation() {
        service.deposit(BigDecimal.valueOf(100));
        verify(persistence).save(any(Operation.class));
    }

    @Test
    void withdraw_shouldCallSaveWithCorrectOperation() {
        service.deposit(BigDecimal.valueOf(200));
        service.withdraw(BigDecimal.valueOf(50));

        verify(persistence, times(2)).save(any(Operation.class));
    }

    @Test
    void deposit_withNegativeAmount_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> service.deposit(BigDecimal.valueOf(-100)));
        verify(persistence, never()).save(any());
    }

    @Test
    void withdraw_withTooMuchAmount_shouldThrow() {
        service.deposit(BigDecimal.valueOf(100));
        assertThrows(IllegalArgumentException.class, () -> service.withdraw(BigDecimal.valueOf(150)));
    }

    @Test
    void withdraw_withNegativeAmount_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> service.withdraw(BigDecimal.valueOf(-30)));
    }

    @Test
    void printStatement_shouldCallFindAllAndPrint() {
        List<Operation> mockOps = List.of(
                new DepositOperation(java.time.LocalDate.now(), BigDecimal.valueOf(100), BigDecimal.valueOf(100)),
                new WithdrawalOperation(java.time.LocalDate.now(), BigDecimal.valueOf(-50), BigDecimal.valueOf(50))
        );

        when(persistence.findAll()).thenReturn(mockOps);

        service.printStatement();

        verify(persistence).findAll();
    }
}