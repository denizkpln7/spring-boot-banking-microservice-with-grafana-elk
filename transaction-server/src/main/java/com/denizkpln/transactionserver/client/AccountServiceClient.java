package com.denizkpln.transactionserver.client;


import com.denizkpln.transactionserver.dto.TransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="account-service",path = "/account")
public interface AccountServiceClient {

    @PostMapping("/transactionsave")
    ResponseEntity<TransactionDto> transactionSave(@RequestBody TransactionDto transactionDto);
}
