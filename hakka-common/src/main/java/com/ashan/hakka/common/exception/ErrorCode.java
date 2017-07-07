package com.ashan.hakka.common.exception;

import io.swagger.models.auth.In;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/23
 */
public class ErrorCode {

    /**
     * 成功的错误编码
     */
    public static final Integer SUCCESS_CODE = new Integer(0);

    public static final String SUCCESS_INFO = "TradeOK";

    /**
     * 未知异常
     */
    public static final Integer UNKNOWN_ERR_CODE = new Integer(-1);

    /**
     * 参数非法
     */
    public static final Integer PARAM_ERR_CODE=new Integer(1004);

    /**
     * 没有找到数据
     */
    public static final Integer DATA_NOT_FOUND_ERR_CODE=new Integer(1005);

}
