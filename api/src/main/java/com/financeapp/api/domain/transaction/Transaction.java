package com.financeapp.api.domain.transaction;

import com.financeapp.api.domain.common.TransactionTypeEnum;
import com.financeapp.api.domain.user.UserAccount;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "transactions")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;
    private LocalDate date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;

}
