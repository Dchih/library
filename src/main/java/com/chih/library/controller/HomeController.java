package com.chih.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "图书管理系统API<br><br>" +
                "可用端点:<br>" +
                "- GET /api/books - 获取所有图书<br>" +
                "- GET /api/books/{id} - 通过ID获取图书<br>" +
                "- POST /api/books - 创建新图书<br>" +
                "- PUT /api/books/{id} - 更新图书<br>" +
                "- DELETE /api/books/{id} - 删除图书<br>" +
                "- GET /api/books/available - 获取可借阅图书<br>" +
                "- GET /api/books/borrowed - 获取已借出图书";
    }
}