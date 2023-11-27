package com.denizkpln.transactionserver.service;

import com.denizkpln.transactionserver.client.AccountServiceClient;
import com.denizkpln.transactionserver.dto.TransactionDto;
import com.denizkpln.transactionserver.exception.CustomException;
import com.denizkpln.transactionserver.model.Transaction;
import com.denizkpln.transactionserver.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountServiceClient accountServiceClient;

    public TransactionService(TransactionRepository transactionRepository, AccountServiceClient accountServiceClient) {
        this.transactionRepository = transactionRepository;
        this.accountServiceClient = accountServiceClient;
    }

    public TransactionDto save(TransactionDto transactionDto) {
        Transaction transaction=new Transaction();
        transaction.setAccountId(transactionDto.getAccountId());
        transaction.setBankname(transactionDto.getBankname());
        transaction.setBalance(transactionDto.getBalance());
        transaction.setCreateDate(LocalDate.now());
        transaction=transactionRepository.save(transaction);

        transactionDto.setId(transaction.getId());
        transactionDto.setCreateDate(transaction.getCreateDate());

        //Account alanına transaction idsini yazmak
        ResponseEntity<TransactionDto> transactionDtos=accountServiceClient.transactionSave(transactionDto);
        transaction.setStatus(transactionDtos.getBody().getStatus());
        transactionRepository.save(transaction);
        transactionDto.setStatus(transaction.getStatus());

        return transactionDto;
    }

    public TransactionDto findById(Long id) {
        Transaction transaction=transactionRepository.findById(id).orElseThrow( () -> new CustomException("Transaction with given id not found","NOT_FOUND",404));

        TransactionDto transactionDto=new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAccountId(transaction.getAccountId());
        transactionDto.setBankname(transaction.getBankname());
        transactionDto.setCreateDate(transaction.getCreateDate());
        transactionDto.setStatus(transaction.getStatus());
        return transactionDto;
    }
}
