package com.tenji.adminapi2.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> {

    /**
     * code为0（成功）
     */
    public ApiResponse() {
        this(ApiResponseCode.status_0.getCode(), ApiResponseCode.status_0.getMessage());
    }

    /**
     * 指定code
     */
    public ApiResponse(ApiResponseCode status) {
        this(status.getCode(), status.getMessage());
    }

    /**
     * code为0（成功）+ data
     */
    public ApiResponse(T data) {
        this(ApiResponseCode.status_0.getCode(), ApiResponseCode.status_0.getMessage(), data);
    }

    /**
     * 指定 code + data
     *
     * @param status
     * @param data
     */
    public ApiResponse(ApiResponseCode status, T data) {
        this(status.getCode(), status.getMessage(), data);
    }

    public ApiResponse(Integer code, String message) {
        this(code, message,  null);
    }

    public ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    /**
     * 返回成功消息
     *
     * @return
     */
    public static ApiResponse success() {
        return new ApiResponse(null);
    }

    /**
     * 返回成功消息 + data
     *
     * @param data 数据对象
     * @return
     */
    public static <O> ApiResponse<O> success(O data) {
        return new ApiResponse<>(data);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ApiResponse error(String msg) {
        return ApiResponse.error(msg, null);
    }
    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ApiResponse error(String msg, Object data) {
        return new ApiResponse(500, msg, data);
    }

    /**
     * 系统状态
     */
    @JsonProperty("code")

    private Integer code;

    /**
     * 系统消息信息
     */
    @JsonProperty("message")
    private String message;

    /**
     * 返回结果内容
     */
    @JsonProperty("data")

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResponse data(T data) {
        this.data = data;
        return this;
    }
    public ApiResponse code(Integer code) {
        this.code = code;
        return this;
    }

    public ApiResponse message(String message) {
        this.message = message;
        return this;
    }

}


