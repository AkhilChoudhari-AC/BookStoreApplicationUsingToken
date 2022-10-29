package com.example.bookstoreapp.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
//DTO: Data transfer object
//This is passing our data from model class to service class
@Data
@Component
public class CartDTO
{
    public int userId;
    public int bookId;
    public int quantity;
}


