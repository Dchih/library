package com.chih.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 图书请求数据传输对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    @Size(max = 20, message = "ISBN不能超过20个字符")
    private String isbn;

    @NotBlank(message = "书名不能为空")
    @Size(max = 200, message = "书名不能超过200个字符")
    private String title;

    @NotBlank(message = "作者不能为空")
    @Size(max = 100, message = "作者不能超过100个字符")
    private String author;

    @Size(max = 100, message = "出版社不能超过100个字符")
    private String publisher;

    @Positive(message = "价格必须为正数")
    private BigDecimal price;

    private LocalDate publicationDate;

    @Size(max = 50, message = "分类不能超过50个字符")
    private String category;

    @NotNull(message = "数量不能为空")
    @Positive(message = "数量必须为正数")
    private Integer quantity;

    @Size(max = 1000, message = "描述不能超过1000个字符")
    private String description;

    private String coverImage;
}