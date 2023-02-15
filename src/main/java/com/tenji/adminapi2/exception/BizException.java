package com.tenji.adminapi2.exception;

import com.tenji.adminapi2.api.ApiResponseCode;

public class BizException extends ApiException{
    public BizException(){ super(ApiResponseCode.status_103);}

    public BizException(String extendMessage){
        super(ApiResponseCode.status_103,extendMessage);
    }
}
