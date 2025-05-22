package com.chih.library.service.impl;

import com.chih.library.entity.Book;
import com.chih.library.exception.ResourceNotFoundException;
import com.chih.library.repository.BookRepository;
import com.chih.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public List<Book> saveBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthorContaining(author);
    }

    @Override
    public List<Book> findBooksByPublisher(String publisher) {
        return bookRepository.findByPublisherContaining(publisher);
    }

    @Override
    public List<Book> findBooksByCategory(String category) {
        return bookRepository.findByCategoryContaining(category);
    }

    @Override
    public List<Book> findBooksByDescription(String description) {
        return bookRepository.findByDescriptionContaining(description);
    }

    @Override
    public List<Book> findAvailableBooks(int minQuantity) {
        return bookRepository.findByAvailableQuantityGreaterThan(minQuantity - 1);
    }

    @Override
    @Transactional
    public Book updateBookAvailability(Long bookId, int changeQuantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("图书未找到，ID：" + bookId));

        int newAvailableQuantity = book.getAvailableQuantity() + changeQuantity;
        if (newAvailableQuantity < 0) {
            throw new IllegalArgumentException("可用数量不能小于0");
        }

        book.setAvailableQuantity(newAvailableQuantity);
        return bookRepository.save(book);
    }
}