package com.haipiao.persist.utils;

import com.haipiao.persist.constants.CommentLimitConstant;

/**
 * @author wangjipeng
 */
public class PageUtil {

    public static int limit(int limit){
        return limit == 0 ? CommentLimitConstant.LIMIT : limit;
    }

    public static int cursor(String cursor){
        return cursor == null ? 0 : Integer.parseInt(cursor);
    }
}
