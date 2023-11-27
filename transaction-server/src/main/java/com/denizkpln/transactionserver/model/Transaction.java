package com.denizkpln.transactionserver.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="accountId")
    private Long accountId;

    @Column(name="createDate")
    private LocalDate createDate;

    @Column(name="bankname")
    private String bankname;

    @Column(name="balance")
    private double balance;

    @Column(name="status")
    private String status;

}
