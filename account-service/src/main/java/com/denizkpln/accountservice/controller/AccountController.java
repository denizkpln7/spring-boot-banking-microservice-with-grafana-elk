package com.denizkpln.accountservice.controller;

import com.denizkpln.accountservice.dto.AccountDto;
import com.denizkpln.accountservice.dto.AccountTransacDto;
import com.denizkpln.accountservice.dto.TransactionDto;
import com.denizkpln.accountservice.model.Account;
import com.denizkpln.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    private final AccountService accountService;



    @Value("${application.name}")
    private String value;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> save(@RequestBody AccountDto accountDto){
        log.info("account save");
        return ResponseEntity.ok(accountService.save(accountDto));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountTransacDto> findbyId(@PathVariable Long accountId){
        log.info("account findbyıd");
        return ResponseEntity.ok(accountService.findbyId(accountId));
    }

    @GetMapping("/config")
    public ResponseEntity<?> config(){
        log.info("config");
        return ResponseEntity.ok(value);
    }

    @PostMapping("/transactionsave")
    public ResponseEntity<TransactionDto> transactionSave(@RequestBody TransactionDto transactionDto){
        log.info("transactionSave");
        return ResponseEntity.ok(accountService.transactionSave(transactionDto));
    }
}
