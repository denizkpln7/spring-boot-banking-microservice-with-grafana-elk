package com.denizkpln.accountservice.dto;

import com.denizkpln.accountservice.model.Account;
import jakarta.persistence.Column;
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
public class AccountDto {

    private Long id;

    private String ownername;

    private String ownersurname;

    private double currentbalance;

    private String bankname;

    private LocalDate createDate;

    private List<Long> transactions;




    public Account converter(AccountDto accountDto){
        Account account=new Account();
        account.setOwnername(accountDto.getOwnername());
        account.setOwnersurname(accountDto.getOwnersurname());
        account.setCurrentbalance(account.getCurrentbalance());
        account.setBankname(accountDto.getBankname());
        return account;
    }
}
