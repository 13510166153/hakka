package com.ashan.hakka.common.exception;

/**
 * 支持错误编码的异常.
 *
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/23
 * @see ErrorCode
 */
public class CommonException extends RuntimeException {

    /**
     * 错误编码.
     *
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date ${YEAR}/${MONTH}/${DAY}
     * @see ErrorCode
     */
    private Integer errCode;

    /**
     * 错误描述
     *
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date ${YEAR}/${MONTH}/${DAY}
     */
    private String errInfo;

    public CommonException() {}

    public CommonException(Integer errCode, String errInfo) {
        super(errInfo);
        this.errCode = errCode;
        this.errInfo = errInfo;
    }

    public CommonException(Integer errCode, String errInfo, Throwable cause) {
        super(errInfo, cause);
        this.errCode = errCode;
        this.errInfo = errInfo;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }
}
