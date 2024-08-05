package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.helperUtils.AccountWithStatusCode;
import com.example.helperUtils.MessageWithStatusCode;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        AccountWithStatusCode accountWithStatusCode = accountService.register(account);
        return ResponseEntity.status(accountWithStatusCode.getStatusCode()).body(accountWithStatusCode.getAccount());
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        AccountWithStatusCode accountWithStatusCode = accountService.login(account);
        return ResponseEntity.status(accountWithStatusCode.getStatusCode()).body(accountWithStatusCode.getAccount());
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        MessageWithStatusCode messageWithStatusCode = messageService.createMessage(message);
        return ResponseEntity.status(messageWithStatusCode.getHttpStatus()).body(messageWithStatusCode.getMessage());
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.ok(messageService.getMessageById(messageId));
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.ok(messageService.deleteMessage(messageId));
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable Integer messageId, @RequestBody Message message_text) {
        Integer integer = messageService.updateMessageById(messageId, message_text);
        if (integer != null) {
            return ResponseEntity.ok(integer);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable Integer account_id) {
        return ResponseEntity.ok(messageService.findMessagesByAccountHolder(account_id));
    }
}
