package com.chih.library.service;

import com.chih.library.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    // 获取所有图书
    List<Book> getAllBooks();

    // 通过ID获取单本图书
    Optional<Book> getBookById(Long id);

    // 保存图书（新增或更新）
    Book saveBook(Book book);

    // 批量保存图书
    List<Book> saveBooks(List<Book> books);

    // 删除图书
    void deleteBook(Long id);

    // 根据标题查询图书
    List<Book> findBooksByTitle(String title);

    // 根据作者查询图书
    List<Book> findBooksByAuthor(String author);

    // 根据出版社查询图书
    List<Book> findBooksByPublisher(String publisher);

    // 根据分类查询图书
    List<Book> findBooksByCategory(String category);

    // 根据描述查询图书
    List<Book> findBooksByDescription(String description);

    // 查找有可借数量的图书
    List<Book> findAvailableBooks(int minQuantity);

    // 更新图书可借数量
    Book updateBookAvailability(Long bookId, int changeQuantity);
}