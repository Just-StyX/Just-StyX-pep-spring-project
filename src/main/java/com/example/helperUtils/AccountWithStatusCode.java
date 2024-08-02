package com.example.helperUtils;

import org.springframework.http.HttpStatus;

import com.example.entity.Account;

public class AccountWithStatusCode {
    private Account account;
    private HttpStatus statusCode;

    public AccountWithStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public AccountWithStatusCode(Account account, HttpStatus statusCode) {
        this.account = account;
        this.statusCode = statusCode;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

}
