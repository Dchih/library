package com.chih.library.util;

import com.chih.library.dto.BookDTO;
import com.chih.library.dto.BookRequestDTO;
import com.chih.library.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 图书对象映射工具类
 */
public class BookMapper {

    /**
     * 将实体对象转换为DTO
     */
    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }

        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setPrice(book.getPrice());
        dto.setPublicationDate(book.getPublicationDate());
        dto.setCategory(book.getCategory());
        dto.setQuantity(book.getQuantity());
        dto.setAvailableQuantity(book.getAvailableQuantity());
        dto.setDescription(book.getDescription());
        dto.setCoverImage(book.getCoverImage());

        return dto;
    }

    /**
     * 将DTO列表转换为实体对象列表
     */
    public static List<BookDTO> toDTOList(List<Book> books) {
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * 将请求DTO转换为实体对象(用于创建)
     */
    public static Book toEntity(BookRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        Book book = new Book();
        book.setIsbn(requestDTO.getIsbn());
        book.setTitle(requestDTO.getTitle());
        book.setAuthor(requestDTO.getAuthor());
        book.setPublisher(requestDTO.getPublisher());
        book.setPrice(requestDTO.getPrice());
        book.setPublicationDate(requestDTO.getPublicationDate());
        book.setCategory(requestDTO.getCategory());
        book.setQuantity(requestDTO.getQuantity());
        book.setAvailableQuantity(requestDTO.getQuantity()); // 初始可用数量等于总数量
        book.setDescription(requestDTO.getDescription());
        book.setCoverImage(requestDTO.getCoverImage());

        return book;
    }

    /**
     * 将请求DTO应用到已有实体对象(用于更新)
     */
    public static void updateEntityFromDTO(BookRequestDTO requestDTO, Book book) {
        if (requestDTO == null || book == null) {
            return;
        }

        book.setIsbn(requestDTO.getIsbn());
        book.setTitle(requestDTO.getTitle());
        book.setAuthor(requestDTO.getAuthor());
        book.setPublisher(requestDTO.getPublisher());
        book.setPrice(requestDTO.getPrice());
        book.setPublicationDate(requestDTO.getPublicationDate());
        book.setCategory(requestDTO.getCategory());

        // 如果数量发生变化，更新可用数量
        if (book.getQuantity() != null && requestDTO.getQuantity() != null) {
            int diff = requestDTO.getQuantity() - book.getQuantity();
            if (book.getAvailableQuantity() != null) {
                book.setAvailableQuantity(book.getAvailableQuantity() + diff);
            } else {
                book.setAvailableQuantity(requestDTO.getQuantity());
            }
        } else {
            book.setAvailableQuantity(requestDTO.getQuantity());
        }

        book.setQuantity(requestDTO.getQuantity());
        book.setDescription(requestDTO.getDescription());
        book.setCoverImage(requestDTO.getCoverImage());
    }
}