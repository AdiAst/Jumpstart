package com.aceadoratech.jumpstart.exchanges;

import com.aceadoratech.jumpstart.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private TransactionalRequest transactionalRequest;
    private double total;
}
