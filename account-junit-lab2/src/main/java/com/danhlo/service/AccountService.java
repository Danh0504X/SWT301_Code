/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danhlo.service;

public class AccountService {

    public boolean registerAccount(String username, String password, String email) {

        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        if (password == null || password.length() <= 6) {
            return false;
        }

        if (!isValidEmail(email)) {
            return false;
        }

        // Giả lập đăng ký thành công
        return true;
    }

    public boolean isValidEmail(String email) {
        if (email == null) return false;

        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
