package com.tenji.adminapi2.api;

public enum ApiResponseCode {

    // 操作成功
    status_0(0, "操作成功"),
    // 操作失败
    status_1(1, "操作失败"),

    // 后端相关
    // 参数校验异常
    status_101(101, "参数校验异常"),
    // 参数输入错误
    status_102(102, "参数输入错误"),
    // 业务处理异常
    status_103(103, "业务处理异常"),
    // 第三方接口调用异常
    status_104(104, "第三方接口调用异常"),

    // APP相关
    // 用户未登录
    status_301(301, "用户未登录"),
    // 账号已过期或失效，请重新登录
    status_302(302, "账号已过期或失效，请重新登录"),
    // 账号密码错误
    status_303(303, "账号密码错误"),
    // 账号不存在
    status_304(304, "账号不存在"),
    // SessionKey过期
    status_305(305, "SessionKey过期"),
    // 随机数过期
    status_306(306, "随机数过期"),
    // 客户已注销
    status_307(307, "客户已注销"),
    // 手机号已注册
    status_308(308, "手机号已注册"),
    // 验证签名失败
    status_309(309, "验证签名失败"),

    // 没有权限访问
    status_401(401, "没有权限访问"),
    // 非法请求服务
    status_403(403, "非法请求服务"),
    // 服务器异常
    status_500(500, "服务器异常"),
    // 未找到数据
    status_601(601, "未找到数据"),
    // 参数输入错误
    status_602(602, "参数输入错误"),
    // 无错误信息编码
    status_604(604, ""),
    // 暂停恢复特殊状态
    status_701(701, "已分班用户"),

    status_502(502, "非法访问"),
    // 未知错误
    status_999(999, "未知异常");

    private Integer code;

    private String message;

    private ApiResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
