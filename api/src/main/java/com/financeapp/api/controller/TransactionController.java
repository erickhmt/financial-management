package com.financeapp.api.controller;

import com.financeapp.api.controller.common.BaseController;
import com.financeapp.api.domain.transaction.TransactionRequestDTO;
import com.financeapp.api.domain.transaction.TransactionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transactions")
public class TransactionController extends BaseController {

    @Autowired
    private TransactionRequestDTO.TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Page<TransactionResponseDTO>> getAllTransactions(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
        var page = transactionService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
        TransactionResponseDTO transaction = transactionService.findById(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        var user = getAuthenticatedUserOrError();

        var transaction = transactionService.save(transactionRequestDTO, user);
        var uri = uriComponentsBuilder.path("/transactions/{id}").buildAndExpand(transaction.id()).toUri();
        return ResponseEntity.created(uri).body(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequestDTO transactionRequestDTO) {
        var user = getAuthenticatedUserOrError();

        TransactionResponseDTO existingTransaction = transactionService.findById(id);
        if (existingTransaction == null) {
            return ResponseEntity.notFound().build();
        }

        TransactionResponseDTO updatedTransaction = transactionService.save(transactionRequestDTO, user);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
