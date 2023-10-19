package com.ThreeTree.controller;

import com.ThreeTree.model.Order;
import com.ThreeTree.model.Person;
import com.ThreeTree.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest {

    @Test
    public void testBuilder() {
        Date date = new Date();
        BigDecimal total = new BigDecimal("100.00");
        Map<Product, Integer> productsQuantities = new HashMap<>();
        Person person = new Person();

        Order order = Order.builder()
                .orderId(1L)
                .orderDate(date)
                .orderTotal(total)
                .productsQuantities(productsQuantities)
                .person(person)
                .build();

        assertEquals(1L, order.getOrderId());
        assertEquals(date, order.getOrderDate());
        assertEquals(total, order.getOrderTotal());
        assertNotNull(order.getProductsQuantities());
        assertNotNull(order.getPerson());
    }

    @Test
    public void testSettersAndGetters() {
        Date date = new Date();
        BigDecimal total = new BigDecimal("100.00");
        Map<Product, Integer> productsQuantities = new HashMap<>();
        Person person = new Person();

        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderDate(date);
        order.setOrderTotal(total);
        order.setProductsQuantities(productsQuantities);
        order.setPerson(person);

        assertEquals(1L, order.getOrderId());
        assertEquals(date, order.getOrderDate());
        assertEquals(total, order.getOrderTotal());
        assertNotNull(order.getProductsQuantities());
        assertNotNull(order.getPerson());
    }


}
