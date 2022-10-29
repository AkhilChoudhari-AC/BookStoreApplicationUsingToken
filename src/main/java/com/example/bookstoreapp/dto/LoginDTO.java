package com.example.bookstoreapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
//DTO: Data transfer object
//This is passing our data from model class to service class
@Data
@NoArgsConstructor
public class LoginDTO {
    public String email;
    public String password;
}
