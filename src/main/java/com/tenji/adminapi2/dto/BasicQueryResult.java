package com.tenji.adminapi2.dto;

import lombok.Data;

import java.util.List;

/**
 * 画面にリストがある値を渡す為のベーシッククラス
 */
@Data
public class BasicQueryResult {

    /**
     * 現在ページ番号
     */
    private int pageNum;
    /**
     * 1ページの表示件数
     */
    private int pageSize;
    /**
     * 総件数
     */
    private long totalSize;
    /**
     * 画面に戻るデータ
     */
    private List<?> result;

}
