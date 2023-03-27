package io.ara.remittance.model;

import io.ara.remittance.active.listener.TransactionListener;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EntityListeners(TransactionListener.class)
@Table(name = "transaction")
@Data
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_id")
    private String transactionId;

//    @Column
//    private State transactionStatus;

    @Column(name = "transfer_fee")
    private BigDecimal transferFee;

    @Column(name = "total_money_to_receive")
    private BigDecimal totalMoneyToReceive;

    @Column(name = "approve_by")
    private String approveBy;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @Column(name = "sender_email")
    private String senderEmail;

//    @OneToOne(fetch = FetchType.EAGER, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinColumn(name = "receiver_id", nullable = false, referencedColumnName = "id")
//    private Receiver receiver;


//    @OneToOne(fetch = FetchType.EAGER, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinColumn(name = "paypal_transaction_id", nullable = false, referencedColumnName = "id")
//    private PaypalTransaction paypalTransaction;
}
