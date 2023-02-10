package com.tenji.adminapi2.dto;

public class UserInfoHolder {
    private final static ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    public static void add(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    public static void remove() {
        userInfoThreadLocal.remove();
    }

    public static UserInfo get() {
        return userInfoThreadLocal.get();
    }
}
