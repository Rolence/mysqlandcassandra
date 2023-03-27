package io.ara.remittance.active.component;

import io.ara.remittance.active.events.EventConstants;
import io.ara.remittance.data.TransactionDTO;
import io.ara.remittance.model.TransactionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

//    @Value("${active-mq.topic}")
    private String topic= EventConstants.CREATE_TRANSACTION;

    public void sendMessage(TransactionDTO transaction){
        try{
            log.info("Attempting Send message to Topic: "+ topic);
            jmsTemplate.convertAndSend(topic, transaction);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }
}

