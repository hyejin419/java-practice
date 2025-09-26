package com.koreait.hellospring.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void testUserGetterSetter() {
        User user = new User();
        user.setName("김사과");
        user.setAge(20);
        assertEquals("김사과", user.getName());
        assertEquals(20, user.getAge());
    }
}
