package com.denizkpln.transactionserver.service;

import com.denizkpln.transactionserver.client.AccountServiceClient;
import com.denizkpln.transactionserver.dto.TransactionDto;
import com.denizkpln.transactionserver.model.Transaction;
import com.denizkpln.transactionserver.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private  TransactionService transactionService;
    private  TransactionRepository transactionRepository;
    private  AccountServiceClient accountServiceClient;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        accountServiceClient = Mockito.mock(AccountServiceClient.class);
        transactionService = new TransactionService(transactionRepository, accountServiceClient);
    }


    @DisplayName("Transaction save With AccountClient")
    @Test
    void testwhenTransactionSaveWithAccount(){
        TransactionDto transactionDto=getMockTransactionDto();
        Transaction transaction=getMockTransaction();
        Transaction transaction1=getMockTransactionStatus();

        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);
        Mockito.when(accountServiceClient.transactionSave(Mockito.any(TransactionDto.class))).thenReturn(ResponseEntity.ok(transactionDto));
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction1);

        TransactionDto result=transactionService.save(transactionDto);

        assertEquals(transactionDto,result);

        Mockito.verify(transactionRepository,Mockito.times(2)).save(Mockito.any(Transaction.class));
        Mockito.verify(accountServiceClient,Mockito.times(1)).transactionSave(Mockito.any(TransactionDto.class));
    }

    private TransactionDto getMockTransactionDto() {
        TransactionDto transactionDto=new TransactionDto();
        transactionDto.setId(1L);
        transactionDto.setAccountId(1L);
        transactionDto.setBankname("deneme");
        transactionDto.setBalance(10.00);
        transactionDto.setCreateDate(LocalDate.now());
        return transactionDto;
    }

    private Transaction getMockTransaction(){
        Transaction transaction=new Transaction();
        transaction.setId(1L);
        transaction.setAccountId(1L);
        transaction.setBankname("deneme");
        transaction.setBalance(10.00);
        transaction.setCreateDate(LocalDate.now());
        return transaction;
    }

    private Transaction getMockTransactionStatus(){
        Transaction transaction=new Transaction();
        transaction.setId(1L);
        transaction.setAccountId(1L);
        transaction.setBankname("deneme");
        transaction.setBalance(10.00);
        transaction.setCreateDate(LocalDate.now());
        transaction.setStatus("EVET");
        return transaction;
    }

}