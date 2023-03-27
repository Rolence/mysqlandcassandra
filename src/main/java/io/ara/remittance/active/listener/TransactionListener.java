package io.ara.remittance.active.listener;

import io.ara.remittance.model.TransactionEntity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

public class TransactionListener {
    @PostPersist
    public void trasactionPostPersist(TransactionEntity omnipaidTransactionEntity) {
        System.out.println("Listening User Post Persist : " + omnipaidTransactionEntity.getTransactionId());
    }

    @PostUpdate
    public  void transactionPostUpdate(TransactionEntity transactionEntity){
        System.out.println("Listening  Post Update : " + transactionEntity.getTransactionId());
    }
}
