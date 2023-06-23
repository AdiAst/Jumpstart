package com.aceadoratech.jumpstart.exchanges;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailProductRequest {
    private Integer retailId;
    private Integer productId;
    private int stock;
}
