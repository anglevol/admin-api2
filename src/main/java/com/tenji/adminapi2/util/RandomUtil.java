package com.tenji.adminapi2.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomUtil {



    private RandomUtil() {

    }

    private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final String RANDOM_NUM = "0123456789";

    private static final String RANDOM_POSITIVE_NUM = "123456789";

    private static final java.util.Random RANDOM = new java.util.Random();

    public static String getRandomStr(int digit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
        }
        return sb.toString();
    }

    public static String getRandomNum(int digit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            sb.append(RANDOM_NUM.charAt(RANDOM.nextInt(RANDOM_NUM.length())));
        }
        return sb.toString();
    }

    /**
     * 生成不以0开头的随机数字串
     *
     * @param digit 长度
     * @return string
     */
    public static String getRandomNumStartWithPositive(int digit) {
        StringBuilder sb = new StringBuilder();
        sb.append(RANDOM_POSITIVE_NUM.charAt(RANDOM.nextInt(RANDOM_POSITIVE_NUM.length())));
        for (int i = 1; i < digit; i++) {
            sb.append(RANDOM_NUM.charAt(RANDOM.nextInt(RANDOM_NUM.length())));
        }
        return sb.toString();
    }

    /**
     * 生成订单号 yyyyMMddHHmmss + 4位随机数
     * @return
     */
    public static String getOrderNo(){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(sdf.format(new Date()));
        sb.append(getRandomNumStartWithPositive(4));
        return sb.toString();
    }

    /**
     * 生成订单号 MMddHHmmss + 4位随机数
     * @return
     */
    public static String getRandomStr(){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        sb.append(sdf.format(new Date()));
        sb.append(getRandomNumStartWithPositive(4));
        return sb.toString();
    }

    /**
     * 生成订单号 yyyyMMdd
     * @return
     */
    public static String getYXOrderNo(){
        StringBuilder sb = new StringBuilder();
        sb.append("YX");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }

    /**
     * 生成订单号 yyyyMMdd
     * @return
     */
    public static String getECOrderNo(){
        StringBuilder sb = new StringBuilder();
        sb.append("EC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }

    /**
     * 生成订单号 yyyyMMddHHmmss + n位随机数
     * @return
     */
    public static String getOrderNo(int count){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(sdf.format(new Date()));
        sb.append(getRandomNumStartWithPositive(count));
        return sb.toString();
    }

    /**
     * 生成退款订单号 TyyyyMMddHHmmss + 3位随机数
     * @return
     */
    public static String getRefundNo(){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append("T");
        sb.append(sdf.format(new Date()));
        sb.append(getRandomNumStartWithPositive(4));
        return sb.toString();
    }
}
