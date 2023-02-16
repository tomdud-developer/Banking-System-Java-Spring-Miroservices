package com.tomdud.transactionsservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cash_transactions")
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_id_sequence",
            sequenceName = "transaction_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_id_sequence"
    )
    @Column
    private Long id;

    @Column(name = "account_id_from")
    private Long accountIdFrom;

    @Column(name = "account_id_to")
    private Long accountIdTo;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "date")
    private Date date;

}
