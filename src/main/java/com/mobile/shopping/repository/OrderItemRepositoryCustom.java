package com.mobile.shopping.repository;

import com.mobile.shopping.service.dto.OrderItemDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepositoryCustom {

    List<OrderItemDTO> getListOrderItemByOrderId(Long orderId);
}
