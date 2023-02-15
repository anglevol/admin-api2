package com.tenji.adminapi2.util;

import com.tenji.adminapi2.api.ApiResponseCode;

public class ApiMessageUtil {


    private ApiMessageUtil() {
    }

    /**
     * 获取API消息
     *
     * @param apiResponseCode 响应code
     * @return 响应message
     */
    public static String getApiMessage(ApiResponseCode apiResponseCode) {
        return String.format("%s", apiResponseCode.getMessage());
    }
}
