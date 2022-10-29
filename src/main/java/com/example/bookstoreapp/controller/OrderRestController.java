package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.EmailData;
import com.example.bookstoreapp.model.OrderData;
import com.example.bookstoreapp.service.IEmailService;
import com.example.bookstoreapp.service.IOrderService;
import com.example.bookstoreapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// In controller class we write API's here
//@controller indicates that a particular class serves the role of a controller

// This annotation is used at the class level and allows the class to handel the request made by the client
@RestController
//This annotation is used to map web requests(HTTP) into specific handler class/methods
@RequestMapping("/order")
public class OrderRestController {
    //Autowired IOrderService to inject its dependency here
    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IEmailService iEmailService;

    @Autowired
    private TokenUtil tokenUtil;

// Ability to call api to add order data

    @PostMapping("/addorder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderData orderData = iOrderService.placeOrder(orderDTO);
        String token = tokenUtil.createToken(orderData.getOrderId());
        EmailData emailData = new EmailData(orderData.getCartId().getUserId().getEmail(),
                "Order confirmed",
                "Hi " + orderData.getCartId().getUserId().getFirstName() +
                        " " + orderData.getCartId().getUserId().getLastName() +
                        ", Click on the given below link to get details \n" + iEmailService.getOrderLink(token));
        iEmailService.sendEmail(emailData);
        ResponseDTO responseDTO = new ResponseDTO("Order is Been placed ", orderData, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    // Ability to call api to get all order data
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<OrderData> orderDataList = iOrderService.getAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("Getting all the orders", orderDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to cancel order by token
    @PutMapping("/cancelorder/{token}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable("token") String token) {
        int tokenId = tokenUtil.decodeToken(token);
        OrderData orderData = iOrderService.cancelOrder(tokenId);
        EmailData emailData = new EmailData(orderData.getCartId().getUserId().getEmail(),
                "Cancelled order",
                "Hi " + orderData.getCartId().getUserId().getFirstName() +
                        " " + orderData.getCartId().getUserId().getLastName() +
                        ", Your order has been cancelled successfully");
        iEmailService.sendEmail(emailData);
        ResponseDTO responseDTO = new ResponseDTO("Order is Been Cancelled", "Order id " + tokenId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    // Ability to call api to get order data by id
    @GetMapping("/getbyid/{token}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable("token") String token) {
        int tokenId = tokenUtil.decodeToken(token);
        OrderData orderData = iOrderService.getOrderById(tokenId);
        ResponseDTO responseDTO = new ResponseDTO("Getting order by Id", orderData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to verify order by token
    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyOrder(@PathVariable("token") String token) {
        OrderData orderData = iOrderService.verifyOrder(token);
        ResponseDTO responseDTO = new ResponseDTO("Your order is ", orderData, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

}

