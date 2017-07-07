package com.ashan.hakka.ucenter.vo;

import java.util.List;

import com.ashan.hakka.common.pojo.vo.VO;
import com.ashan.hakka.common.util.PojoUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/05
 */
@ApiModel(value="应用程序信息")
public class AppVO implements VO {


    private static final long serialVersionUID = -54268385095064L;

    /**
     *id
     **/
    @ApiModelProperty(value="app_id,应用程序ID")
    private Integer id;


    /**
     *应用程序编码
     **/
    @ApiModelProperty(value="应用程序编码")
    private String appCode;


    /**
     *应用程序名称
     **/
    @ApiModelProperty(value="应用程序名称")
    private String appName;


    /**
     *应用程序图标路径
     **/
    @ApiModelProperty(value="应用程序图标路径")
    private String appImg;


    /**
     *应用程序描述
     **/
    @ApiModelProperty(value="应用程序描述")
    private String appDesc;


    /**
     *应用的URL
     **/
    @ApiModelProperty(value="应用的URL")
    private String appUrl;


    /**
     *访问的KEY
     **/
    @ApiModelProperty(value="访问的KEY")
    private String accessKey;

    /**
     * 应用程序对应的权限
     */
    @ApiModelProperty(value="应用程序对应的权限,如果没有任何权限,则返回为空的数组")
    private List<PermVO> perms;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppImg() {
        return appImg;
    }

    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public List<PermVO> getPerms() {
        return perms;
    }

    public void setPerms(List<PermVO> perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
