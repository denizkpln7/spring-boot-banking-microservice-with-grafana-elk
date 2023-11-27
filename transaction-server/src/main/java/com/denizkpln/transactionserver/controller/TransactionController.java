package com.denizkpln.transactionserver.controller;

import com.denizkpln.transactionserver.dto.TransactionDto;
import com.denizkpln.transactionserver.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> save(@RequestBody TransactionDto transactionDto){
        log.info("transaction save");
        return ResponseEntity.ok(transactionService.save(transactionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable Long id){
        log.info("transaction findById");
        return ResponseEntity.ok(transactionService.findById(id));
    }

}
