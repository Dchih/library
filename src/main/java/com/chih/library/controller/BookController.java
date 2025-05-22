package com.chih.library.controller;

import com.chih.library.entity.Book;
import com.chih.library.service.BookService;
import com.chih.library.util.ResultResponse;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResultResponse<List<Book>> getAllBooks() {
        return ResultResponse.success(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResultResponse<Book> getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id)
                .map(ResultResponse::success)
                .orElse(ResultResponse.error(404, "图书不存在"));
    }

    @PostMapping
    public ResultResponse<Book> createBook(@RequestBody Book book) {
        return ResultResponse.success("图书创建成功", bookService.saveBook(book));
    }

    @Operation(summary = "批量创建图书", description = "一次创建多本图书")
    @PostMapping("/batch")
    public ResultResponse<List<Book>> createBooks(@RequestBody List<Book> books) {
        System.out.println(
                books);
        return ResultResponse.success("批量创建图书成功", bookService.saveBooks(books));
    }

    @PutMapping("/{id}")
    public ResultResponse<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        book.setId(id);
        return ResultResponse.success("图书更新成功", bookService.saveBook(book));
    }

    @DeleteMapping("/{id}")
    public ResultResponse<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResultResponse.success("图书删除成功", null);
    }

    @GetMapping("/available")
    public ResultResponse<List<Book>> getAvailableBooks() {
        return ResultResponse.success(bookService.findAvailableBooks(1));
    }

    @GetMapping("/borrowed")
    public ResultResponse<List<Book>> getBorrowedBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        List<Book> borrowedBooks = allBooks.stream()
                .filter(book -> (book.getQuantity() - book.getAvailableQuantity()) > 0)
                .toList();
        return ResultResponse.success(borrowedBooks);
    }
}