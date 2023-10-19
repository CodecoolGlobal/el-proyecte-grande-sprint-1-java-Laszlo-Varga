package com.ThreeTree.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonTest {

    @Test
    public void testBuilder() {
        Set<Order> orders = new HashSet<>();
        Person person = Person.builder()
                .PersonId(1L)
                .FirstName("John")
                .LastName("Doe")
                .passwordHash("hashedPassword")
                .address("123 Main St")
                .email("john@example.com")
                .phoneNumber(123456789)
                .orders(orders)
                .build();

        assertEquals(1L, person.getPersonId());
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("hashedPassword", person.getPasswordHash());
        assertEquals("123 Main St", person.getAddress());
        assertEquals("john@example.com", person.getEmail());
        assertEquals(123456789, person.getPhoneNumber());
        assertNotNull(person.getOrders());
    }

    @Test
    public void testSettersAndGetters() {
        Person person = new Person();
        Set<Order> orders = new HashSet<>();

        person.setPersonId(1L);
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setPasswordHash("hashedPassword");
        person.setAddress("123 Main St");
        person.setEmail("john@example.com");
        person.setPhoneNumber(123456789);
        person.setOrders(orders);

        assertEquals(1L, person.getPersonId());
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("hashedPassword", person.getPasswordHash());
        assertEquals("123 Main St", person.getAddress());
        assertEquals("john@example.com", person.getEmail());
        assertEquals(123456789, person.getPhoneNumber());
        assertNotNull(person.getOrders());
    }


}
