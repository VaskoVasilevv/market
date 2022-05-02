package com.example.market.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testCreateUserCorrect(){

        User user = new User();
        user.setUsername("Vasil");
        user.setAccount(10);
        user.setId(1L);

        assertEquals("Vasil",user.getUsername());
        assertEquals(10,user.getAccount());
        assertEquals(1,user.getId());
    }

}