package com.chih.library.repository;

import com.chih.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    // 根据读者ID查询
    List<Borrow> findByReader_Id(Long readerId);

    // 根据书籍ID查询
    List<Borrow> findByBook_Id(Long bookId);

    // 根据借阅日期查询
    List<Borrow> findByBorrowDate(LocalDateTime borrowDate);

    // 根据归还日期查询
    List<Borrow> findByReturnDate(LocalDateTime returnDate);

    // 根据借阅状态查询
    List<Borrow> findByStatus(String status);

}