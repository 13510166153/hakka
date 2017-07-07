package com.ashan.hakka.common.pojo.vo;


import com.ashan.hakka.common.util.PojoUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/23
 */
@ApiModel(value="响应对象",description = "根对象,Http请求响应中直接将该对象序列化成json后写入Response Body")
public class ResponseVO<T> implements VO {

    private static final long serialVersionUID = -797178731233966316L;

    /**
     * 请求ID,标识其是哪个请求的响应
     */
    @ApiModelProperty(value="请求ID")
    private String requestId;


    @ApiModelProperty(value="响应时间")
    private Long responseTime;

    /**
     * 错误编码
     * <PRE>
     *     0:成功
     *     其他:失败
     * </PRE>
     */
    @ApiModelProperty(value="错误编码,0:成功,其他:失败")
    private Integer errCode;

    /**
     * 错误描述,错误编码{@linkplain #errCode}不为0时必须填写该字段
     */
    @ApiModelProperty(value="错误描述,错误编码不为0时必须填写该字段")
    private String errInfo;

    /**
     * 业务数据
     */
    @ApiModelProperty(value="具体接口的业务数据")
    private T data;


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
