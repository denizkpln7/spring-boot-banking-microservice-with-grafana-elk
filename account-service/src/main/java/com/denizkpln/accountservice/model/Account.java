package com.denizkpln.accountservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ownerame")
    private String ownername;

    @Column(name="ownersurname")
    private String ownersurname;

    @Column(name="currentbalance")
    private double currentbalance;

    @Column(name="bankname")
    private String bankname;

    @Column(name="createDate")
    private LocalDate createDate;

    private List<Long> transactions;

}
