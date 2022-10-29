package com.example.bookstoreapp.model;

import com.example.bookstoreapp.dto.BookDTO;
import lombok.Data;
import javax.persistence.*;
//This annotation specifies that the class is an entity and is mapped to a database table
@Entity
//making the table with the following name
@Table(name = "book_table")
public @Data class BookData {
    //This annotation specifies the primary key of an entity
    @Id
    //This annotation specifies , the automatically generate the primary key value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_description")
    private String bookDescription;

    @Column(name = "book_image")
    private String bookImage;

    @Column(name = "book_price")
    private int bookPrice;

    public BookData() {
    }

    public BookData(BookDTO bookDTO) {
        this.updateBookData(bookDTO);
    }

    public void updateBookData(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.bookAuthor = bookDTO.bookAuthor;
        this.bookDescription = bookDTO.bookDescription;
        this.bookImage = bookDTO.bookImage;
        this.bookPrice = bookDTO.bookPrice;
    }
}

