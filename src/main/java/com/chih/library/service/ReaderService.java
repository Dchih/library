package com.chih.library.service;

import com.chih.library.entity.Reader;
import java.util.List;
import java.util.Optional;

public interface ReaderService {
    // 获取所有读者
    List<Reader> getAllReaders();

    // 通过ID获取读者
    Optional<Reader> getReaderById(Long id);

    // 保存读者（新增或更新）
    Reader saveReader(Reader reader);

    // 删除读者
    void deleteReader(Long id);

    // 根据性别查询读者
    List<Reader> findReadersByGender(String gender);

    // 根据姓名查询读者
    List<Reader> findReadersByName(String name);

    // 根据邮箱查询读者
    List<Reader> findReadersByEmail(String email);

    // 根据电话查询读者
    List<Reader> findReadersByPhone(String phone);
}