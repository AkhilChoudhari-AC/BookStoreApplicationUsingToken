package com.example.bookstoreapp.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

//DTO: Data transfer object
//This is passing our data from model class to service class

@Data
@NoArgsConstructor
public class UserRegistrationDTO {

    public String firstName;

    public String lastName;

    public String email;

    public String password;

    public String address;
}

