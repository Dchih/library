package com.chih.library.service.impl;

import com.chih.library.entity.Reader;
import com.chih.library.repository.ReaderRepository;
import com.chih.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @Override
    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }

    @Override
    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }

    @Override
    public List<Reader> findReadersByGender(String gender) {
        return readerRepository.findByGender(gender);
    }

    @Override
    public List<Reader> findReadersByName(String name) {
        return readerRepository.findByNameContaining(name);
    }

    @Override
    public List<Reader> findReadersByEmail(String email) {
        return readerRepository.findByEmailContaining(email);
    }

    @Override
    public List<Reader> findReadersByPhone(String phone) {
        return readerRepository.findByPhoneContaining(phone);
    }
}