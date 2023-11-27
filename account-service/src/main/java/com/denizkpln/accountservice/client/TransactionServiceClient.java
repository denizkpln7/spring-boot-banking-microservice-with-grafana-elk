package com.denizkpln.accountservice.client;


import com.denizkpln.accountservice.dto.TransactionDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "transaction-service", path = "/transaction")
public interface TransactionServiceClient {

    Logger logger = LoggerFactory.getLogger(TransactionServiceClient.class);

    @GetMapping("/{id}")
    @CircuitBreaker(name = "getTransactionByIsbnCircuitBreaker", fallbackMethod = "getBookFallback")
    ResponseEntity<TransactionDto> findById(@PathVariable Long id);

    default ResponseEntity<TransactionDto> getBookFallback(Long id) {
        logger.info("Transaction not found by id " + id + ", returning default TransactionDto object");
        return ResponseEntity.ok(new TransactionDto());
    }

}
