package com.chih.library.enums;

/**
 * 读者身份枚举类
 */
public enum ReaderIdentity {
    STUDENT("学生"),
    TEACHER("教师"),
    ADMIN("管理员"),
    VISITOR("游客");

    private final String displayName;

    ReaderIdentity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}