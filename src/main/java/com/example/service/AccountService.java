package com.example.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.helperUtils.AccountWithStatusCode;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService( AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;
    }

    public AccountWithStatusCode register(Account account) {
        Optional<Account> foundAccount = accountRepository.findByUsername(account.getUsername());
        if (foundAccount.isPresent()) return new AccountWithStatusCode(HttpStatus.CONFLICT);

        if (!(account.getUsername().isBlank() || account.getUsername().isEmpty()) && account.getPassword().length() >= 4) {
            Account savedAccount = accountRepository.save(account);
            return new AccountWithStatusCode(savedAccount, HttpStatus.OK);  
        } else {
            return new AccountWithStatusCode(HttpStatus.BAD_REQUEST);
        }
    }

    public AccountWithStatusCode login(Account account) {
        Optional<Account> foundAccount = accountRepository.findByUsername(account.getUsername());
        if (foundAccount.isPresent()) {
            Account userAccount = foundAccount.get();
            if (userAccount.getPassword().equals(account.getPassword())) {
                return new AccountWithStatusCode(userAccount, HttpStatus.OK);
            } else {
                return new AccountWithStatusCode(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new AccountWithStatusCode(HttpStatus.UNAUTHORIZED);
        }
    }
}
