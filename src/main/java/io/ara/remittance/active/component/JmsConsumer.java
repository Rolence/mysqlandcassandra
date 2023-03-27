package io.ara.remittance.active.component;

import io.ara.remittance.active.events.EventConstants;
import io.ara.remittance.data.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {


    @Override
    @JmsListener(destination = EventConstants.CREATE_TRANSACTION)
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            TransactionDTO employee = (TransactionDTO) objectMessage.getObject();
            //do additional processing
            log.info("Received Message: "+ employee.toString());
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }

    }
}
