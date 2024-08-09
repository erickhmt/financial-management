package com.financeapp.api.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponseDTO(
    Long id,
    String description,
    String type,
    BigDecimal amount,
    LocalDate date
) {
    public TransactionResponseDTO(Transaction transaction) {
        this(transaction.getId(), transaction.getDescription(), transaction.getType().toString(), transaction.getAmount(), transaction.getDate());
    }
}
