package com.chih.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 图书数据传输对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    private String isbn;

    private String title;

    private String author;

    private String publisher;

    private BigDecimal price;

    private LocalDate publicationDate;

    private String category;

    private Integer quantity;

    private Integer availableQuantity;

    private String description;

    private String coverImage;
}