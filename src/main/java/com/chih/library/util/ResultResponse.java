package com.chih.library.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(200, "操作成功", data);
    }

    public static <T> ResultResponse<T> success(String msg, T data) {
        return new ResultResponse<>(200, msg, data);
    }

    public static <T> ResultResponse<T> error(Integer code, String msg) {
        return new ResultResponse<>(code, msg, null);
    }

    public static <T> ResultResponse<T> error(String msg) {
        return new ResultResponse<>(500, msg, null);
    }
}