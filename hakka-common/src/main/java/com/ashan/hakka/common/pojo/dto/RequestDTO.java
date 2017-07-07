package com.ashan.hakka.common.pojo.dto;

import com.ashan.hakka.common.util.PojoUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 *
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/23
 */
@ApiModel(value="请求")
public class RequestDTO implements DTO {

    private static final long serialVersionUID = -604451761994777643L;

    /**
     * 请求ID,每个请求对应不同的ID
     */
    @ApiModelProperty(value="请求ID")
    private String requestId;

    /**
     * 请求域,用于服务端标识该请求属于那个子系统
     */
    @ApiModelProperty(value="由服务端分配")
    private String requestDomain;

    /**
     * 客户端的请求时间
     */
    @ApiModelProperty(value="客户端的请求时间")
    private Long requestTime;

    /**
     * 请求凭证,主要鉴权使用
     */
    @ApiModelProperty(value="请求凭证")
    private String certificate;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestDomain() {
        return requestDomain;
    }

    public void setRequestDomain(String requestDomain) {
        this.requestDomain = requestDomain;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
