package com.denizkpln.accountservice.service;

import com.denizkpln.accountservice.client.TransactionServiceClient;
import com.denizkpln.accountservice.dto.AccountDto;
import com.denizkpln.accountservice.dto.AccountTransacDto;
import com.denizkpln.accountservice.dto.TransactionDto;
import com.denizkpln.accountservice.exception.CustomException;
import com.denizkpln.accountservice.model.Account;
import com.denizkpln.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private TransactionServiceClient transactionServiceClient;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        transactionServiceClient = Mockito.mock(TransactionServiceClient.class);
        accountService = new AccountService(accountRepository, transactionServiceClient);
    }

    @DisplayName("Account Save")
    @Test
    void testwhebaccountSaveAndReturnAccountDto(){
        Account account=getMockAccount();
        AccountDto accountDto=getMockAccountDto();

        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);

        AccountDto accountsave=accountService.save(accountDto);

        assertEquals(accountDto,accountsave);

        Mockito.verify(accountRepository,Mockito.times(1)).save(Mockito.any(Account.class));
    }

    @DisplayName("Account FindById together transtactions")
    @Test
    void testwhenfindbyIdAccountAndTransactions(){
        Account account=getMockTransactionAccount();
        TransactionDto transactionDto1=new TransactionDto(1L,1L,LocalDate.now(),"deneme",10.00,"EVET");
        TransactionDto transactionDto2=new TransactionDto(2L,1L,LocalDate.now(),"deneme",10.00,"EVET");
        List<TransactionDto> list=Arrays.asList(transactionDto1,transactionDto2);
        AccountTransacDto accountDto=new AccountTransacDto(1L,"deneme","deneme",100.00,null,LocalDate.now(),list);
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        Mockito.when(transactionServiceClient.findById(1L)).thenReturn(ResponseEntity.ok(transactionDto1));
        Mockito.when(transactionServiceClient.findById(2L)).thenReturn(ResponseEntity.ok(transactionDto2));

        AccountTransacDto result=accountService.findbyId(1L);

        assertEquals(accountDto,result);

        Mockito.verify(accountRepository,Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(transactionServiceClient,Mockito.times(1)).findById(1L);
        Mockito.verify(transactionServiceClient,Mockito.times(1)).findById(2L);

    }

    @DisplayName("Account FindById together transtactions fail")
    @Test
    void testwhenfindbyIdAccountAndTransactionsFail(){

        Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(CustomException.class,
                ()->accountService.findbyId(Mockito.anyLong()));

        Mockito.verify(accountRepository,Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verifyNoInteractions(transactionServiceClient);

    }


    private Account getMockTransactionAccount() {
        List<Long> ids = Arrays.asList(1L,2L);
        Account account=new Account();
        account.setId(1L);
        account.setOwnername("deneme");
        account.setOwnersurname("deneme");
        account.setCurrentbalance(100.00);
        account.setBankname("deneme");
        account.setCreateDate(LocalDate.now());
        account.setTransactions(ids);
        return account;
    }

//    private AccountDto getMockTransactionAccountDto() {
//        List<Long> ids = Arrays.asList(1L,2L);
//        AccountDto account=new AccountDto();
//        account.setId(1L);
//        account.setOwnername("deneme");
//        account.setOwnersurname("deneme");
//        account.setCurrentbalance(100.00);
//        account.setBankname("deneme");
//        account.setCreateDate(LocalDate.now());
//        account.setTransactions(ids);
//        return account;
//    }


    private AccountDto getMockAccountDto() {
        AccountDto accountDto=new AccountDto();
        accountDto.setId(1L);
        accountDto.setOwnername("deneme");
        accountDto.setOwnersurname("deneme");
        accountDto.setCurrentbalance(100.00);
        accountDto.setBankname("deneme");
        accountDto.setCreateDate(LocalDate.now());
        return accountDto;
    }


    private Account getMockAccount() {
        Account account=new Account();
        account.setId(1L);
        account.setOwnername("deneme");
        account.setOwnersurname("deneme");
        account.setCurrentbalance(100.00);
        account.setBankname("deneme");
        account.setCreateDate(LocalDate.now());
        return account;
    }

}