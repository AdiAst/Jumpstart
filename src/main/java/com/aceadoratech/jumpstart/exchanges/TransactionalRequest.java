package com.aceadoratech.jumpstart.exchanges;

import com.aceadoratech.jumpstart.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionalRequest {
    String customerName;
    String address;
    Product product;
}
