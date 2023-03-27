package io.ara.remittance.service;

import io.ara.remittance.data.SenderRequest;
import io.ara.remittance.data.SenderResponse;
import io.ara.remittance.data.TransactionDTO;
import io.ara.remittance.model.TransactionEntity;

import java.util.List;

public interface SenderServiceInterface {
    String createSender(SenderRequest senderRequest);
    SenderResponse getSenderByName();
    List<SenderResponse> getAllSenders();
    TransactionDTO createTransaction(TransactionDTO transactionEntity);

}
