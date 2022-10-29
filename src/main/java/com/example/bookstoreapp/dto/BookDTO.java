package com.example.bookstoreapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
//DTO: Data transfer object
//This is passing our data from model class to service class
@Data
@NoArgsConstructor
public class BookDTO {
    public String bookName;
    public String bookAuthor;
    public String bookDescription;
    public String bookImage;
    public int bookPrice;
}



