package com.example.market.service.impl;

import com.example.market.model.entity.User;
import com.example.market.repository.UserRepository;
import com.example.market.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private User testUser;

    private UserService userService;

    private UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {

//      ARRANGE
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("Vasil");
        testUser.setAccount(10);

        this.mockUserRepository = Mockito.mock(UserRepository.class);

    }

    @Test
    public void testAddOneUser() {
//      ARRANGE
        when(this.mockUserRepository.getUserById(1l)).thenReturn(this.testUser);

        this.userService = new UserServiceImpl(this.mockUserRepository);
//      ACT
        this.userService.initUsers();
//      ASSERT
        assertEquals(this.testUser,this.mockUserRepository.getUserById(1L));

    }

}