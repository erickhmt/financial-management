package com.financeapp.api.domain.transaction;

import com.financeapp.api.domain.common.TransactionTypeEnum;
import com.financeapp.api.domain.user.UserAccount;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequestDTO(
    String description,
    BigDecimal amount,
    LocalDate transactionDate,
    String type
) {
    @Service
    public static class TransactionService {

        @Autowired
        private TransactionRepository transactionRepository;

        public Page<TransactionResponseDTO> findAll(Pageable pageable) {
            return transactionRepository.findAll(pageable)
                    .map(TransactionResponseDTO::new);
        }

        public TransactionResponseDTO findById(Long id) {
            var transaction =  transactionRepository.getReferenceById(id);
            return new TransactionResponseDTO(transaction);
        }

        public TransactionResponseDTO save(TransactionRequestDTO transactionRequestDTO, UserAccount userAccount) {
            Transaction transaction = new Transaction();
            transaction.setDescription(transactionRequestDTO.description());
            transaction.setAmount(transactionRequestDTO.amount());
            transaction.setDate(transactionRequestDTO.transactionDate());

            TransactionTypeEnum type = TransactionTypeEnum.valueOf(transactionRequestDTO.type());
            transaction.setType(type);
            transaction.setUserAccount(userAccount);

            Transaction savedTransaction = transactionRepository.save(transaction);
            return new TransactionResponseDTO(savedTransaction);
        }

        public void deleteById(Long id) {
            transactionRepository.deleteById(id);
        }

    }
}
