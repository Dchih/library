package com.chih.library.service;

import com.chih.library.entity.Borrow;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BorrowService {
    // 获取所有借阅记录
    List<Borrow> getAllBorrows();

    // 通过ID获取借阅记录
    Optional<Borrow> getBorrowById(Long id);

    // 保存借阅记录（新增或更新）
    Borrow saveBorrow(Borrow borrow);

    // 删除借阅记录
    void deleteBorrow(Long id);

    // 借书操作
    Borrow borrowBook(Long readerId, Long bookId);

    // 还书操作
    Borrow returnBook(Long borrowId);

    // 查找读者的借阅记录
    List<Borrow> findBorrowsByReaderId(Long readerId);

    // 查找图书的借阅记录
    List<Borrow> findBorrowsByBookId(Long bookId);

    // 根据借阅日期查询
    List<Borrow> findBorrowsByBorrowDate(LocalDateTime borrowDate);

    // 根据归还日期查询
    List<Borrow> findBorrowsByReturnDate(LocalDateTime returnDate);

    // 根据借阅状态查询
    List<Borrow> findBorrowsByStatus(String status);

}