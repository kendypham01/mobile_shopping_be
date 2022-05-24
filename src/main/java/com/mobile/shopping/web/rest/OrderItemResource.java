package com.mobile.shopping.web.rest;

import com.mobile.shopping.domain.OrderItem;
import com.mobile.shopping.repository.OrderItemRepository;
import com.mobile.shopping.service.OrderItemService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.mobile.shopping.service.dto.OrderItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
@Transactional
public class OrderItemResource {

    private final Logger log = LoggerFactory.getLogger(OrderItemResource.class);

    private static final String ENTITY_NAME = "orderItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderItemRepository orderItemRepository;

    private final OrderItemService orderItemService;

    public OrderItemResource(OrderItemRepository orderItemRepository, OrderItemService orderItemService) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
    }

    @PostMapping("/order-items")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) throws URISyntaxException {
        OrderItem result = orderItemRepository.save(orderItem);
        return ResponseEntity.ok().body(result);
    }



    @GetMapping("/order-items")
    public List<OrderItem> getAllOrderItems() {
        log.debug("REST request to get all OrderItems");
        return orderItemRepository.findAll();
    }

    @GetMapping("/order-items/{id}")
    public ResponseEntity<List<OrderItemDTO>> getOrderItem(@PathVariable Long id) {
        List<OrderItemDTO> lstOrderItem = orderItemService.getListOrderItemByOrderId(id);
        return ResponseEntity.ok().body(lstOrderItem);
    }

    @DeleteMapping("/order-items/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        log.debug("REST request to delete OrderItem : {}", id);
        orderItemRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
