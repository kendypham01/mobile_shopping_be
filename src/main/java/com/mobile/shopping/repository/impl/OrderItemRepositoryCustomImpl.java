package com.mobile.shopping.repository.impl;

import com.mobile.shopping.repository.OrderItemRepositoryCustom;
import com.mobile.shopping.service.dto.OrderItemDTO;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class OrderItemRepositoryCustomImpl implements OrderItemRepositoryCustom {

    private EntityManager entityManager;

    public OrderItemRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<OrderItemDTO> getListOrderItemByOrderId(Long orderId) {
        StringBuilder sql = new StringBuilder(
            "SELECT oi.id,\n" +
            "o.id as orderId,\n" +
            "p.code as productCode,\n" +
            "p.name as productName,\n" +
            "oi.quantity as quantity,\n" +
            "p.price as productPrice,\n" +
            "oi.price as price\n" +
            "FROM order_item oi LEFT JOIN orders o ON oi.order_id = o.id\n" +
            "LEFT JOIN product p ON oi.product_code = p.code\n" +
            "WHERE oi.order_id = " + orderId
        );
        NativeQuery<OrderItemDTO> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
        query
            .addScalar("id", new LongType())
            .addScalar("orderId", new LongType())
            .addScalar("productCode", new StringType())
            .addScalar("productName", new StringType())
            .addScalar("productPrice", new FloatType())
            .addScalar("quantity", new IntegerType())
            .addScalar("price", new FloatType())
            .setResultTransformer(Transformers.aliasToBean(OrderItemDTO.class));
        return query.list();
    }
}
