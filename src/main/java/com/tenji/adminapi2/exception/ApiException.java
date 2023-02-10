package com.tenji.adminapi2.exception;

import com.tenji.adminapi2.api.ApiResponseCode;

public class ApiException extends RuntimeException{

    private static final long serialVersionUID = -5908715393204853393L;

    private ApiResponseCode responseCode = null;

    private String extendMessage = "";

    @Override
    public String getMessage() {
        return super.getMessage() + " " + extendMessage;
    }

    public ApiException(String extendMessage) {
        this.extendMessage = extendMessage;
    }

    public ApiException(String message, String extendMessage) {
        super(message);
        this.extendMessage = extendMessage;
    }

    public ApiException(ApiResponseCode apiResponseCode) {
        super(apiResponseCode.getMessage());
        this.responseCode = apiResponseCode;
    }

    public ApiException(ApiResponseCode apiResponseCode, String extendMessage) {
        super(apiResponseCode.getMessage());
        this.responseCode = apiResponseCode;
        this.extendMessage = extendMessage;
    }

    public ApiResponseCode getResponseCode() {
        return responseCode;
    }

    public String getExtendMessage() {
        return extendMessage;
    }
}
