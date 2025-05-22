package com.chih.library.repository;

import com.chih.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // 根据标题查询
    List<Book> findByTitleContaining(String title);

    // 根据作者查询
    List<Book> findByAuthorContaining(String author);

    // 根据出版社查询
    List<Book> findByPublisherContaining(String publisher);

    // 根据分类查询
    List<Book> findByCategoryContaining(String category);

    // 根据描述查询
    List<Book> findByDescriptionContaining(String description);

    // 根据可用数量查询
    List<Book> findByAvailableQuantityGreaterThan(int quantity);

}
