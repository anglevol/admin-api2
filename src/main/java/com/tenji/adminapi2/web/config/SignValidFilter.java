package com.tenji.adminapi2.web.config;



import com.alibaba.fastjson.JSONObject;
import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.api.ApiResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class SignValidFilter {
    private String[] prefixIgnores;
    private String ignoreParam;

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        ignoreParam = filterConfig.getInitParameter("exclusions");
//        if (StringUtils.isNotEmpty(ignoreParam)) {
//            prefixIgnores = ignoreParam.split(",");
//        }
//        return;
//    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        if (canIgnore(servletRequest)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
////
////        if (servletRequest instanceof HttpServletRequest) {
////            HttpServletRequest request = (HttpServletRequest) servletRequest;
////            String nonce = request.getHeader("nonce");
////            String timestamp = request.getHeader("timestamp");
////            String signature = request.getHeader("signature");
//////            if (StringUtils.isAnyBlank(nonce, timestamp, signature)) {
//////                returnFailedResponse((HttpServletResponse) servletResponse, "签名校验失败!");
//////                return;
//////            }
//////            if (!SignatureUtil.validateSign(nonce, timestamp, signature, SecurityConstant.SECURITY_KEY)) {
//////                log.error("签名校验失败");
//////                returnFailedResponse((HttpServletResponse) servletResponse, "签名校验失败");
//////                return;
//////            }
////        }
//       filterChain.doFilter(servletRequest, servletResponse);
//    }

//    @Override
//    public void destroy() {
//    }
//
//    private void returnFailedResponse(HttpServletResponse response, String extendMessage) {
//        ApiResponse apiResponse = new ApiResponse(ApiResponseCode.status_403.getCode(),
//                "[403] " + ApiResponseCode.status_403.getMessage() + ": " + extendMessage, null);
//        PrintWriter writer = null;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        try {
//            writer = response.getWriter();
//            writer.print(JSONObject.toJSONString(apiResponse));
//        } catch (IOException e) {
//            log.error("签名校验过滤器返回结果异常：", e);
//        } finally {
//            if (null != writer) {
//                writer.close();
//            }
//        }
//    }
//
//    private boolean canIgnore(ServletRequest request) {
//        boolean ignore = false;
//        // 判断是否在过滤url之外
//        for (String url : prefixIgnores) {
//            if (((HttpServletRequest) request).getServletPath().contains(url)) {
//                ignore = true;
//                break;
//            }
//        }
//        return ignore;
//    }
}
