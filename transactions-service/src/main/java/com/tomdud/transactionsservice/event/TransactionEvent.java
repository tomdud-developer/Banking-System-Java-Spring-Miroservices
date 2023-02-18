package com.tomdud.transactionsservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEvent {
    public String accountNumberFrom;
    public String accountNumberTo;
    public Long quantity;
}
