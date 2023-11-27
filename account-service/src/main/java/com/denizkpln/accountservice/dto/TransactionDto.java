package com.denizkpln.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TransactionDto {

    private Long id;

    private Long accountId;

    private LocalDate createDate;

    private String bankname;

    private double balance;

    private String status;


}
