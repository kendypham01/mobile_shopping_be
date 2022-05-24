package com.mobile.shopping.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long id;
    private Long orderId;
    private String productCode;
    private String productName;
    private Float productPrice;
    private Integer quantity;
    private Float price;
}
