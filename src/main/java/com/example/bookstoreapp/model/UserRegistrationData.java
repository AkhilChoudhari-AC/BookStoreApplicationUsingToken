package com.example.bookstoreapp.model;

import com.example.bookstoreapp.dto.UserRegistrationDTO;
import lombok.Data;
import javax.persistence.*;

//This annotation specifies that the class is an entity and is mapped to a database table
@Entity
//making the table with the following name
@Table(name = "user_registration_table")
public @Data class UserRegistrationData {
    //This annotation specifies the primary key of an entity
    @Id
    //This annotation specifies , the automatically generate the primary key value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "verified")
    private boolean verified;

    public UserRegistrationData() {
    }

    public UserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        this.updateUserRegistrationData(userRegistrationDTO);
    }

    public void updateUserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        this.firstName = userRegistrationDTO.firstName;
        this.lastName = userRegistrationDTO.lastName;
        this.email = userRegistrationDTO.email;
        this.password = userRegistrationDTO.password;
        this.address = userRegistrationDTO.address;
    }
}
