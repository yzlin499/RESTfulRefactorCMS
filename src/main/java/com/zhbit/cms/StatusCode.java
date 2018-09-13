package com.zhbit.cms;

public final class StatusCode {
    public static final String STATUS="status";
    public static final String ERROR="error";
    public static final String DATA = "data";
    public static final String MESSAGE="message";
    public static final String SUCCESS="success";


    /**         成功                */
    public static final int COMPLETE=200;
    /**         无法通过验证        */
    public static final int FORBIDDEN=403;
    /**    */
    public static final int DB_BOON=452;
    /**         数据库报错           */
    public static final int DB_ERROR=453;
    /**         JSON格式错误        */
    public static final int NO_JSON=454;
    /**         参数缺失            */
    public static final int PARAM_LACK=455;
    /**         未知错误            */
    public static final int UNKNOWN_FAIL=465;

    public static final int NO_FIND=404;

    /**
     * 参数错误
     */
    public static final int PARAM_ERROR = 501;
}
