package io.ara.remittance.data;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private String transactionId;

//    @Column
//    private State transactionStatus;

    private BigDecimal transferFee;

    private BigDecimal totalMoneyToReceive;

    private String approveBy;

//    private LocalDateTime approvalDate;

    private BigDecimal exchangeRate;

    private String senderEmail;
}
