package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.LoginDTO;
import com.example.bookstoreapp.dto.UserRegistrationDTO;
import com.example.bookstoreapp.exceptions.UserRegistrationCustomException;
import com.example.bookstoreapp.model.CartData;
import com.example.bookstoreapp.model.UserRegistrationData;
import com.example.bookstoreapp.repository.UserRegistrationRepository;
import com.example.bookstoreapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
// Service layer is mediates communication between a controller and repository layer
@Service
//Creating UserRegistrationService class to serve api calls done by controller layer
public class UserRegistrationService implements IUserRegistrationService{
    //Autowired interface to inject its dependency here
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private TokenUtil tokenUtil;

    //creating a method name as getUserRegistrationData
    //Ability to get all user details by findAll() method
    @Override
    public List<UserRegistrationData> getUserRegistrationData() {
        return userRegistrationRepository.findAll();
    }

    //creating a method name as getUserRegistrationDataByUserId
    //Ability to get user details by findById() method
    @Override
    public UserRegistrationData getUserRegistrationDataByUserId(int userId) {
        return userRegistrationRepository.findById(userId).orElseThrow(() -> new UserRegistrationCustomException("User not found"));
    }

    //creating a method name as getUserByEmailId
    //Ability to get user details by findUserRegistrationDataByEmail() method
    @Override
    public UserRegistrationData getUserByEmailId(String email) {
        UserRegistrationData userRegistrationData = userRegistrationRepository.findUserRegistrationDataByEmail(email);
        if (userRegistrationData != null)
            return userRegistrationData;
        else
            throw new UserRegistrationCustomException("User with email id " + email + " not found");
    }

    //creating a method name as createUserRegistrationData
    //Ability to add  user details
    @Override
    public UserRegistrationData createUserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = new UserRegistrationData(userRegistrationDTO);
        return userRegistrationRepository.save(userRegistrationData);
    }
    //creating a method name as updateUserRegistrationData
    //Ability to update user details
    @Override
    public UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = this.getUserRegistrationDataByUserId(userId);
        userRegistrationData.updateUserRegistrationData(userRegistrationDTO);
        return userRegistrationRepository.save(userRegistrationData);
    }
    //creating a method name as userLogin
    //Ability to get user details by findByEmailAndPassword() method
    @Override
    public UserRegistrationData userLogin(LoginDTO loginDTO) {
        UserRegistrationData userLoginData = userRegistrationRepository.findByEmailAndPassword(loginDTO.email,
                loginDTO.password);
        if (userLoginData != null)
            return userLoginData;
        else
            throw new UserRegistrationCustomException("User not found");
    }
    //creating a method name as verifyUser

    @Override
    public UserRegistrationData verifyUser(String token) {
        UserRegistrationData userRegistrationData = this.getUserRegistrationDataByUserId(tokenUtil.decodeToken(token));
        userRegistrationData.setVerified(true);
        return userRegistrationRepository.save(userRegistrationData);
    }
}
