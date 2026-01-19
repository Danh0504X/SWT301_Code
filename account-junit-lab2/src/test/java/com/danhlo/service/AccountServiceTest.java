package com.danhlo.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.CsvSource;

public class AccountServiceTest {

    private final AccountService accountService = new AccountService();

    @ParameterizedTest
    @CsvFileSource(resources = "/data/test-data.csv", numLinesToSkip = 1)
    void shouldRegisterAccountCorrectly(
            String username,
            String password,
            String email,
            boolean expected) {

        boolean actual = accountService.registerAccount(username, password, email);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "a@b.com,true",
        "abc,false",
        "test@,false"
    })
    void shouldValidateEmailCorrectly(String email, boolean expected) {
        assertEquals(expected, accountService.isValidEmail(email));
    }
}
