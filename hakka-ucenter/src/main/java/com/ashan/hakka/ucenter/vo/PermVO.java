package com.ashan.hakka.ucenter.vo;

import com.ashan.hakka.common.pojo.vo.VO;
import com.ashan.hakka.common.util.PojoUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;
import sun.plugin2.util.PojoUtil;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/05
 */
@ApiModel(value="权限信息")
public class PermVO implements VO {

    private static final long serialVersionUID = -54212348385095064L;

    /**
     *id
     **/
    @ApiModelProperty(value="id")
    private Integer id;


    /**
     *权限编码
     **/
    @ApiModelProperty(value="权限编码")
    private String premCode;


    /**
     *权限描述
     **/
    @ApiModelProperty(value="权限描述")
    private String premDesc;


    /**
     *权限对应的应用程序
     **/
    @ApiModelProperty(value="权限对应的应用程序")
    private Integer appId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPremCode() {
        return premCode;
    }

    public void setPremCode(String premCode) {
        this.premCode = premCode;
    }

    public String getPremDesc() {
        return premDesc;
    }

    public void setPremDesc(String premDesc) {
        this.premDesc = premDesc;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
