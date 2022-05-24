package com.mobile.shopping.service.impl;

import com.mobile.shopping.repository.OrderItemRepositoryCustom;
import com.mobile.shopping.service.OrderItemService;
import com.mobile.shopping.service.dto.OrderItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepositoryCustom orderItemRepositoryCustom;

    public OrderItemServiceImpl(OrderItemRepositoryCustom orderItemRepositoryCustom) {
        this.orderItemRepositoryCustom = orderItemRepositoryCustom;
    }

    @Override
    public List<OrderItemDTO> getListOrderItemByOrderId(Long orderId) {
        return orderItemRepositoryCustom.getListOrderItemByOrderId(orderId);
    }
}
