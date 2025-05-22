package com.chih.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chih.library.entity.Borrow;
import com.chih.library.exception.ResourceNotFoundException;
import com.chih.library.service.BorrowService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
    private final BorrowService borrowService;

    BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("/{id}")
    public Borrow getBorrowById(@PathVariable Long id) {
        return borrowService.getBorrowById(id)
                .orElseThrow(() -> new ResourceNotFoundException("借阅记录不存在，ID：" + id));
    }

    @PostMapping
    public Borrow createBorrow(@RequestBody Borrow borrow) {
        return borrowService.saveBorrow(borrow);
    }

    @PutMapping("/{id}")
    public Borrow updateBorrow(@PathVariable Long id, @RequestBody Borrow borrow) {
        return borrowService.getBorrowById(id)
                .map(existingBorrow -> {
                    borrow.setId(existingBorrow.getId());
                    return borrowService.saveBorrow(borrow);
                })
                .orElseThrow(() -> new ResourceNotFoundException("借阅记录不存在，ID：" + id));
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);
    }
}
