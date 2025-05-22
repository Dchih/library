package com.chih.library.repository;

import com.chih.library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    // 根据性别查询
    List<Reader> findByGender(String gender);

    // 根据姓名查询
    List<Reader> findByNameContaining(String name);

    // 根据邮箱查询
    List<Reader> findByEmailContaining(String email);

    // 根据电话查询
    List<Reader> findByPhoneContaining(String phone);

}
