package com.example.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.helperUtils.MessageWithStatusCode;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private final AccountRepository accountRepository;
    private final MessageRepository messageRepository;

    public MessageService(AccountRepository accountRepository, MessageRepository messageRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    public MessageWithStatusCode createMessage(Message message) {
        Account account = accountRepository.findById(message.getPostedBy()).orElse(null);
        if (account != null) {
            if (!(message.getMessageText().isBlank() && message.getMessageText().isEmpty()) && message.getMessageText().length() < 255) {
                return new MessageWithStatusCode(messageRepository.save(message), HttpStatus.OK);
            } else {
                return new MessageWithStatusCode(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new MessageWithStatusCode(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public Integer deleteMessage(Integer messageId) {
        Message message = messageRepository.findById(messageId).orElse(null);
        if (message != null) {
            messageRepository.deleteById(messageId);
            return 1;
        } else {
            return null;
        }
    }

    public Integer updateMessageById(Integer messageId, Message newMessage) {
        Message message = messageRepository.findById(messageId).orElse(null);
        if (message != null) {
            if (!(newMessage.getMessageText().isBlank() && newMessage.getMessageText().isEmpty()) && newMessage.getMessageText().length() <= 255) {
                message.setMessageText(newMessage.getMessageText());
                messageRepository.save(message);
                return 1;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<Message> findMessagesByAccountHolder(Integer postedBy) {
        return messageRepository.findMessageByAccount(postedBy);
    }
}
