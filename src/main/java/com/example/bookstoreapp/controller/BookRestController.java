package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.BookData;
import com.example.bookstoreapp.service.IBookService;
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
@RequestMapping("/book")
public class BookRestController {

    //Autowired IbookService to inject its dependency here
    @Autowired
    private IBookService iBookService;

    @Autowired
    private TokenUtil tokenUtil;

    // Ability to call api to add book data record
    @PostMapping("/addbook")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookData bookData = iBookService.addBook(bookDTO);
        String token = tokenUtil.createToken(bookData.getBookId());
        ResponseDTO responseDTO = new ResponseDTO("Book is been added ", bookData, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to get all book data record
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getBooksList() {
        List<BookData> bookDataList = iBookService.getBookList();
        ResponseDTO responseDTO = new ResponseDTO("Getting Record of All Books", bookDataList, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to get book data record by id
    @GetMapping("/getbyid/{token}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable("token") String token) {
        int tokenId = tokenUtil.decodeToken(token);
        BookData bookData = iBookService.getBookById(tokenId);
        ResponseDTO responseDTO = new ResponseDTO("Getting Book by Id", bookData, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to delete book data record
    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable("token") String token) {
        int tokenId = tokenUtil.decodeToken(token);
        iBookService.deleteBookById(tokenId);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Book By Id", "Deleted Id : " + tokenId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to get book data record by author
    @GetMapping("/getbyauthor/{bookAuthor}")
    public ResponseEntity<ResponseDTO> getBookByAuthor(@PathVariable("bookAuthor") String bookAuthor) {
        List<BookData> bookDataList = iBookService.getBookByAuthor(bookAuthor);
        ResponseDTO responseDTO = new ResponseDTO("Getting books by author", bookDataList, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to get book data record in ascending order
    @GetMapping("/orderascending")
    public ResponseEntity<ResponseDTO> sortBookAscendingOrder() {
        List<BookData> bookDataList = iBookService.sortBookAscendingOrder();
        ResponseDTO responseDTO = new ResponseDTO("Getting books in ascending order", bookDataList, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to get book data record in descending order
    @GetMapping("/orderdescending")
    public ResponseEntity<ResponseDTO> sortBookDescendingOrder() {
        List<BookData> bookDataList = iBookService.sortBookDescendingOrder();
        ResponseDTO responseDTO = new ResponseDTO("Getting books in descending order", bookDataList, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    // Ability to call api to update book data record by token
    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateBookById(@PathVariable("token") String token,@RequestBody BookDTO bookDTO) {
        int tokenId = tokenUtil.decodeToken(token);
        BookData bookData = iBookService.updateBookById(tokenId, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated book for Id " + tokenId, bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

}

