package fr.capco.bank.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "operations")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private BigDecimal amount;
    private BigDecimal balance;

    public OperationEntity() {}

    public OperationEntity(LocalDate date, BigDecimal amount, BigDecimal balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDate getDate() { return date; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getBalance() { return balance; }
}