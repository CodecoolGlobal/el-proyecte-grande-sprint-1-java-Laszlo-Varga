package com.ThreeTree.controller;

import com.ThreeTree.model.Order;
import com.ThreeTree.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest {

    @Test
    public void testBuilder() {
        BigDecimal price = new BigDecimal("100.00");
        Set<Order> orders = new HashSet<>();

        Product product = Product.builder()
                .productId(1L)
                .name("Test Product")
                .sku("TEST123")
                .description("Test Description")
                .price(price)
                .stock(10)
                .image("test.jpg")
                .orders(orders)
                .build();

        assertEquals(1L, product.getProductId());
        assertEquals("Test Product", product.getName());
        assertEquals("TEST123", product.getSku());
        assertEquals("Test Description", product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(10, product.getStock());
        assertEquals("test.jpg", product.getImage());
        assertNotNull(product.getOrders());
    }

    @Test
    public void testSettersAndGetters() {
        BigDecimal price = new BigDecimal("100.00");
        Set<Order> orders = new HashSet<>();

        Product product = new Product();
        product.setProductId(1L);
        product.setName("Test Product");
        product.setSku("TEST123");
        product.setDescription("Test Description");
        product.setPrice(price);
        product.setStock(10);
        product.setImage("test.jpg");
        product.setOrders(orders);

        assertEquals(1L, product.getProductId());
        assertEquals("Test Product", product.getName());
        assertEquals("TEST123", product.getSku());
        assertEquals("Test Description", product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(10, product.getStock());
        assertEquals("test.jpg", product.getImage());
        assertNotNull(product.getOrders());
    }


}
