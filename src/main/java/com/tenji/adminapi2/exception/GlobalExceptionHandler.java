package com.tenji.adminapi2.exception;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.util.ApiMessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * API处理异常
     *
     * @param apiException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiErrorException(ApiException apiException) {
        logger.error("ApiException: ", apiException);
        // 获取状态码和消息
        ApiResponseCode apiResponseCode = apiException.getResponseCode();
        String message = ApiMessageUtil.getApiMessage(apiResponseCode);
        // 构建返回消息
        if (StringUtils.isNotBlank(apiException.getExtendMessage())) {
            if (StringUtils.isNotBlank(message)) {
                message = apiException.getExtendMessage();
            } else {
                message = apiException.getExtendMessage();
            }
        }
        // 返回响应对象
        ApiResponse apiResponse = new ApiResponse(apiResponseCode.getCode(), message);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    /**
     * 将RestController的未处理NullPointerException转换为code_999错误。
     *
     * @param nullPointerException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> nullException(NullPointerException nullPointerException) {
        logger.error("NullPointerException: ", nullPointerException);
        // 获取状态码和消息
        ApiResponseCode apiResponseCode = ApiResponseCode.status_999;
        String message = ApiMessageUtil.getApiMessage(apiResponseCode);
        // 返回响应对象
        ApiResponse apiResponse = new ApiResponse(apiResponseCode.getCode(), message);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    /**
     * 重写@Valid 400错误 返回值
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("BadRequest: ", ex);
        // 获取状态码和消息
        ApiResponseCode apiResponseCode = ApiResponseCode.status_101;
        String message = ApiMessageUtil.getApiMessage(apiResponseCode);
        // 返回响应对象
        ApiResponse apiResponse = new ApiResponse(apiResponseCode.getCode(), message);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // 获取状态码和消息
        ApiResponseCode apiResponseCode = ApiResponseCode.status_101;
        String message = ApiMessageUtil.getApiMessage(apiResponseCode);
        // 返回响应对象
        ApiResponse apiResponse = new ApiResponse(apiResponseCode.getCode(), message);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * 将RestController的未处理Exception转换为code_999错误。
     *
     * @param exception
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception exception) {
        logger.error("Exception", exception);
        // 获取状态码和消息
        ApiResponseCode apiResponseCode = ApiResponseCode.status_999;
        String message = ApiMessageUtil.getApiMessage(apiResponseCode);
        // 返回响应对象
        ApiResponse apiResponse = new ApiResponse(apiResponseCode.getCode(), message);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}
