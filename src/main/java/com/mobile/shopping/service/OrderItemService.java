package com.mobile.shopping.service;

import com.mobile.shopping.service.dto.OrderItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {

    List<OrderItemDTO> getListOrderItemByOrderId(Long orderId);
}
