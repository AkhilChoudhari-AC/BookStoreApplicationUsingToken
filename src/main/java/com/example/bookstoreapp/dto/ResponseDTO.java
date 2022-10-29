package com.example.bookstoreapp.dto;

import lombok.Data;
//DTO: Data transfer object
//This is passing our data from model class to service class
@Data
public class ResponseDTO {
    private String message;
    private Object data;
    private String token;

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(String message, Object data, String token) {
        this.message = message;
        this.data = data;
        this.token = token;
    }
}


