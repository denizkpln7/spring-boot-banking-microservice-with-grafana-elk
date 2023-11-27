package com.denizkpln.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountTransacDto {
    private Long id;

    private String ownername;

    private String ownersurname;

    private double currentbalance;

    private String bankname;

    private LocalDate createDate;

    private List<TransactionDto> transactions;
}
