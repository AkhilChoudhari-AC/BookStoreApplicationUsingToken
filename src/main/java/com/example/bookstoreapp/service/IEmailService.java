package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.EmailData;
import org.springframework.http.ResponseEntity;
//Created IEmailService interface to achieve abstraction
public interface IEmailService {

    ResponseEntity<ResponseDTO> sendEmail(EmailData emailData);

    String getLink(String token);

    String getOrderLink(String token);
}

