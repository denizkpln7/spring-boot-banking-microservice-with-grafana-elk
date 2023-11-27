package com.denizkpln.accountservice.service;


import com.denizkpln.accountservice.client.TransactionServiceClient;
import com.denizkpln.accountservice.dto.AccountDto;
import com.denizkpln.accountservice.dto.AccountTransacDto;
import com.denizkpln.accountservice.dto.TransactionDto;
import com.denizkpln.accountservice.exception.CustomException;
import com.denizkpln.accountservice.model.Account;
import com.denizkpln.accountservice.repository.AccountRepository;
import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class AccountService {

    private final AccountRepository accountRepository;

    private final TransactionServiceClient transactionServiceClient;

    public AccountService(AccountRepository accountRepository, TransactionServiceClient transactionServiceClient) {
        this.accountRepository = accountRepository;
        this.transactionServiceClient = transactionServiceClient;
    }


    public AccountDto save(AccountDto accountDto) {
        Account account=new Account();
        account.setOwnername(accountDto.getOwnername());
        account.setOwnersurname(accountDto.getOwnersurname());
        account.setCurrentbalance(accountDto.getCurrentbalance());
        account.setBankname(accountDto.getBankname());
        account.setCreateDate(LocalDate.now());

        Account save=accountRepository.save(account);

        accountDto.setCreateDate(save.getCreateDate());
        accountDto.setId(save.getId());
        return accountDto;
    }

    public AccountTransacDto findbyId(Long accountId) {
        Account account=accountRepository.findById(accountId).orElseThrow(
                ()->new CustomException("Account with given id not found","ACCOUNT_NOT_FOUND",404));

        AccountTransacDto accountDto=new AccountTransacDto();
        accountDto.setId(account.getId());
        accountDto.setOwnername(account.getOwnername());
        accountDto.setOwnersurname(account.getOwnersurname());
        accountDto.setCreateDate(account.getCreateDate());
        accountDto.setCurrentbalance(account.getCurrentbalance());

        if (!CollectionUtils.isEmpty(account.getTransactions())){
            List<TransactionDto> transactionDtos=account.getTransactions().stream()
                    .map(item->transactionServiceClient.findById(item).getBody()).collect(Collectors.toList());

            accountDto.setTransactions(transactionDtos);
        }

        return accountDto;
    }



    public TransactionDto transactionSave(TransactionDto transactionDto) {
        Account account=accountRepository.findById(transactionDto.getAccountId()).orElseThrow(
                ()->new CustomException("Account with given id not found","ACCOUNT_NOT_FOUND",404));

        if (account.getCurrentbalance()-transactionDto.getBalance()>=0){
            transactionDto.setStatus("EVET");
            account.setCurrentbalance(account.getCurrentbalance()-transactionDto.getBalance());
        }else{
            transactionDto.setStatus("HAYIR");
        }



        account.getTransactions().add(transactionDto.getId());
        accountRepository.save(account);

        return transactionDto;
    }

}
