package com.example.bookstoreapp.dto;


import lombok.Data;
//DTO: Data transfer object
//This is passing our data from model class to service class
@Data
public class OrderDTO
{
    public int cartId;
    public String address;
}



