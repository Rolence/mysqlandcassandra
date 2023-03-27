package io.ara.remittance.controller;

import com.google.gson.Gson;
import io.ara.remittance.active.component.JmsProducer;
import io.ara.remittance.data.TransactionDTO;
import io.ara.remittance.model.TransactionEntity;
import io.ara.remittance.service.SenderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create")
public class SenderController {
    private final SenderServiceInterface senderServiceInterface;
    @Autowired
    JmsProducer jmsProducer;

    public SenderController(SenderServiceInterface senderServiceInterface) {
        this.senderServiceInterface = senderServiceInterface;
    }

    @PostMapping(value = "/trans",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO){
        jmsProducer.sendMessage(transactionDTO);
        System.out.println("Create Transaction Controller called\n"+new Gson().toJson(transactionDTO));
        return ResponseEntity.ok(senderServiceInterface.createTransaction(transactionDTO));
    }
}
