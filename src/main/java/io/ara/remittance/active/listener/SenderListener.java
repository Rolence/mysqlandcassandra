package io.ara.remittance.active.listener;

import com.google.gson.Gson;
import io.ara.remittance.active.events.EventConstants;
import io.ara.remittance.model.TransactionEntity;
import io.ara.remittance.model.SenderEntity;
import io.ara.remittance.service.NotificationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessagingException;

public class SenderListener {
//    private final VerificationTokenService verificationTokenService;
    private final NotificationService notificationService;
    private final Logger logger;

    @Autowired
    public SenderListener(
//            final VerificationTokenService verificationTokenService,
                          final NotificationService notificationService,
                          @Qualifier("ServiceConstant.LOGGER_NAME")Logger logger) {
        super();
//        this.verificationTokenService = verificationTokenService;
        this.notificationService = notificationService;
        this.logger = logger;
    }

    @JmsListener(destination = EventConstants.POST_SENDER, containerFactory = "connectionFactory")
    public void postSender(SenderEntity senderEntity) throws MessagingException {
        System.out.println("Listening  Post SenderEntity : " + senderEntity.getEmail());
//        this.verificationTokenService.sendVerificationEmail(senderEntity.getEmail());
        this.logger.info("Verification email listener called " + new Gson().toJson(senderEntity));
    }

    @JmsListener(destination = EventConstants.POST_OMNIPAID_TRANSACTION, containerFactory = "connectionFactory")
    public void createTransaction(TransactionEntity omnipaidTransactionEntity) throws MessagingException {
        String subject = "Omnipaid TransactionEntity";
        String text = "Hello dear customer the  Omnipaid TransactionEntity have been created we will get back to you when it will be approve.Thanks";
        System.out.println("Listening  createTransaction : " );
        this.notificationService.notificationEmail(omnipaidTransactionEntity.getSenderEmail(), subject, text);
    }

    @JmsListener(destination = EventConstants.APPROVE_OMNIPAID_TRANSACTION, containerFactory = "connectionFactory")
    public void approveTransaction(TransactionEntity omnipaidTransactionEntity) throws MessagingException {
        String subject = "Omnipaid TransactionEntity";
        String text = "Hello der customer the  Omnipaid TransactionEntity have been approve";
        System.out.println("Listening  approveTransaction: ");
        this.notificationService.notificationEmail(omnipaidTransactionEntity.getSenderEmail(), subject, text);
    }

    @JmsListener(destination = EventConstants.CREATE_TRANSACTION, containerFactory = "connectionFactory")
    public void createTransactionTest(TransactionEntity transaction) throws MessagingException {
        String subject = "Omnipaid TransactionEntity";
        String text = "Hello der customer the  Omnipaid TransactionEntity have been approve";
        System.out.println("Test Listening Listening  approveTransaction Create Transaction: "+new Gson().toJson(transaction));
//        this.notificationService.notificationEmail(omnipaidTransactionEntity.getSenderEmail(), subject, text);
    }
}
