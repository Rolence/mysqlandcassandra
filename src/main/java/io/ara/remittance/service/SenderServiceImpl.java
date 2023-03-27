package io.ara.remittance.service;

import io.ara.remittance.active.events.EventConstants;
import io.ara.remittance.data.SenderRequest;
import io.ara.remittance.data.SenderResponse;
import io.ara.remittance.data.TransactionDTO;
import io.ara.remittance.model.SenderRepository;
import io.ara.remittance.model.TransactionEntity;
import io.ara.remittance.model.TransactionRepository;
import io.ara.remittance.security.UserDetailsImpl;
import io.ara.remittance.security.domain.User;
import io.ara.remittance.security.domain.UserSecurityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SenderServiceImpl implements SenderServiceInterface{
    private final SenderRepository senderRepository;
    private final UserSecurityRepository userSecurityRepository;
    private final TransactionRepository transactionRepository;
    private final JmsTemplate jmsTemplate;

    public SenderServiceImpl(SenderRepository senderRepository, UserSecurityRepository userSecurityRepository, TransactionRepository transactionRepository, JmsTemplate jmsTemplate) {
        this.senderRepository = senderRepository;
        this.userSecurityRepository = userSecurityRepository;
        this.transactionRepository = transactionRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public String createSender(SenderRequest request) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username =  userDetails.getUsername();
        Optional<User> user = userSecurityRepository.findByUsername(username);
        if (user.isPresent()){
//            SenderEntity entity = new SenderEntity();
        }
        return null;
    }

    @Override
    public SenderResponse getSenderByName() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username =  userDetails.getUsername();
        Optional<User> user = userSecurityRepository.findByUsername(username);
        if (user.isPresent()){

        }
        return null;
    }

    @Override
    public List<SenderResponse> getAllSenders() {
        return null;
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        LocalDateTime dateTime = LocalDateTime.now();
        TransactionEntity entity = TransactionEntity.builder()
                .approveBy(transactionDTO.getApproveBy())
                .senderEmail(transactionDTO.getSenderEmail())
                .exchangeRate(transactionDTO.getExchangeRate())
                .transferFee(transactionDTO.getTransferFee())
                .totalMoneyToReceive(transactionDTO.getTotalMoneyToReceive())
                .transactionId(transactionDTO.getTransactionId())
                .approvalDate(dateTime)
                .build();
        transactionRepository.save(entity);
        jmsTemplate.convertAndSend(EventConstants.CREATE_TRANSACTION,entity);
//        BeanUtils.copyProperties();
        return null;
    }
}
