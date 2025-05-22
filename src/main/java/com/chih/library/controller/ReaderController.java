package com.chih.library.controller;

import com.chih.library.entity.Reader;
import com.chih.library.service.BookService;
import com.chih.library.service.ReaderService;
import com.chih.library.util.ResultResponse;
import jakarta.validation.constraints.Null;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {
    private final ReaderService readerService;

    ReaderController(ReaderService readerService, BookService bookService) {
        this.readerService = readerService;
    }

    @GetMapping
    public ResultResponse<List<Reader>> getAllReaders() {
        return ResultResponse.success(readerService.getAllReaders());
    }

    @GetMapping("/{id}")
    public ResultResponse<Reader> getReaderById(@PathVariable Long id) {
        return readerService.getReaderById(id)
                .filter(reader -> reader.getId() != 1)
                .map(ResultResponse::success)
                .orElseGet(() -> ResultResponse.error("读者不存在"));
    }

    @PostMapping
    public ResultResponse<Reader> createReader(@RequestBody Reader reader) {
        return ResultResponse.success("创建读者成功", readerService.saveReader(reader));
    }

    @PutMapping("/{id}")
    public ResultResponse<Reader> updateReader(@PathVariable Long id, @RequestBody Reader reader) {
        if (reader.getId() == null) {
            reader.setId(id);
        } else if (!reader.getId().equals(id)) {
            return ResultResponse.error("路径ID与读者ID不匹配");
        }

        if (!readerService.getReaderById(id).isPresent()) {
            return ResultResponse.error("读者不存在");
        }

        return ResultResponse.success(readerService.saveReader(reader));
    }

    @DeleteMapping("/{id}")
    public ResultResponse<Void> deleteReader(@PathVariable Long id) {
        if (readerService.getReaderById(id).isPresent()) {
            readerService.deleteReader(id);
            return ResultResponse.success("删除成功", null);
        }
        return ResultResponse.error("读者不存在");
    }

}
