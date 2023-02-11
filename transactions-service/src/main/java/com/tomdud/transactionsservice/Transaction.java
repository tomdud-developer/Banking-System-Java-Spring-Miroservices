package com.tomdud.transactionsservice;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Column(name = "from")
    private Long from;

    @Column(name = "to")
    private Long to;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "date")
    private Date date;

}
