package com.ThreeTree.controller;

import com.ThreeTree.dao.ProductRepository;
import com.ThreeTree.model.Product;
import com.ThreeTree.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProducts() {
        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setDescription("flower1");

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setDescription("flower2");

        List<Product> allProducts = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(allProducts);

        List<Product> result = productService.getProducts();

        assertEquals(2, result.size());
        assertEquals("flower1", result.get(0).getDescription());
        assertEquals("flower2", result.get(1).getDescription());
    }
}
