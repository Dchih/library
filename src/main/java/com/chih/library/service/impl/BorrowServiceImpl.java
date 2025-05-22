package com.chih.library.service.impl;

import com.chih.library.entity.Book;
import com.chih.library.entity.Borrow;
import com.chih.library.entity.Reader;
import com.chih.library.exception.ResourceNotFoundException;
import com.chih.library.repository.BookRepository;
import com.chih.library.repository.BorrowRepository;
import com.chih.library.repository.ReaderRepository;
import com.chih.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BorrowServiceImpl(BorrowRepository borrowRepository,
            ReaderRepository readerRepository,
            BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @Override
    public Optional<Borrow> getBorrowById(Long id) {
        return borrowRepository.findById(id);
    }

    @Override
    public Borrow saveBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @Override
    public void deleteBorrow(Long id) {
        borrowRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Borrow borrowBook(Long readerId, Long bookId) {
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new ResourceNotFoundException("读者未找到，ID：" + readerId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("图书未找到，ID：" + bookId));

        if (book.getAvailableQuantity() <= 0) {
            throw new IllegalStateException("图书无库存可借：" + book.getTitle());
        }

        // 减少可用数量
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);

        // 创建借阅记录
        Borrow borrow = new Borrow();
        borrow.setReader(reader);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDateTime.now());
        borrow.setStatus("已借出");

        return borrowRepository.save(borrow);
    }

    @Override
    @Transactional
    public Borrow returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new ResourceNotFoundException("借阅记录未找到，ID：" + borrowId));

        if ("已归还".equals(borrow.getStatus())) {
            throw new IllegalStateException("图书已归还，不能重复操作");
        }

        // 增加可用数量
        Book book = borrow.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        bookRepository.save(book);

        // 更新借阅记录
        borrow.setReturnDate(LocalDateTime.now());
        borrow.setStatus("已归还");

        return borrowRepository.save(borrow);
    }

    @Override
    public List<Borrow> findBorrowsByReaderId(Long readerId) {
        return borrowRepository.findByReader_Id(readerId);
    }

    @Override
    public List<Borrow> findBorrowsByBookId(Long bookId) {
        return borrowRepository.findByBook_Id(bookId);
    }

    @Override
    public List<Borrow> findBorrowsByBorrowDate(LocalDateTime borrowDate) {
        return borrowRepository.findByBorrowDate(borrowDate);
    }

    @Override
    public List<Borrow> findBorrowsByReturnDate(LocalDateTime returnDate) {
        return borrowRepository.findByReturnDate(returnDate);
    }

    @Override
    public List<Borrow> findBorrowsByStatus(String status) {
        return borrowRepository.findByStatus(status);
    }
}