package com.ashan.hakka.ucenter.dbo;

import com.ashan.hakka.common.util.PojoUtils;


/**
 *自动生成的DO代码,对应表[{tableName}]的DO对象.
 *@author CreateCodeUtils
 *@date 2017/07/03
 */
public class TuEAppDO implements com.ashan.hakka.common.pojo.dbo.DBO
{

    private static final long serialVersionUID = 1205636091L;


    /**
      *id
     **/
    private Integer id;


    /**
      *应用程序编码
     **/
    private String appCode;


    /**
      *应用程序名称
     **/
    private String appName;


    /**
      *应用程序图标路径
     **/
    private String appImg;


    /**
      *应用程序描述
     **/
    private String appDesc;


    /**
      *应用的URL
     **/
    private String appUrl;


    /**
      *访问的KEY
     **/
    private String accessKey;


    /**
      *是否删除:0-否,1-是
     **/
    private Integer isDelete;


    /**
      *创建时间
     **/
    private java.util.Date gmtCreate;


    /**
      *更新时间
     **/
    private java.util.Date gmtModified;


    public Integer getId()
    {
        return this.id;
    }


    public void setId(Integer id)
    {
        this.id=id;
    }


    public String getAppCode()
    {
        return this.appCode;
    }


    public void setAppCode(String appCode)
    {
        this.appCode=appCode;
    }


    public String getAppName()
    {
        return this.appName;
    }


    public void setAppName(String appName)
    {
        this.appName=appName;
    }


    public String getAppImg()
    {
        return this.appImg;
    }


    public void setAppImg(String appImg)
    {
        this.appImg=appImg;
    }


    public String getAppDesc()
    {
        return this.appDesc;
    }


    public void setAppDesc(String appDesc)
    {
        this.appDesc=appDesc;
    }


    public String getAppUrl()
    {
        return this.appUrl;
    }


    public void setAppUrl(String appUrl)
    {
        this.appUrl=appUrl;
    }


    public String getAccessKey()
    {
        return this.accessKey;
    }


    public void setAccessKey(String accessKey)
    {
        this.accessKey=accessKey;
    }


    public Integer getIsDelete()
    {
        return this.isDelete;
    }


    public void setIsDelete(Integer isDelete)
    {
        this.isDelete=isDelete;
    }


    public java.util.Date getGmtCreate()
    {
        return this.gmtCreate;
    }


    public void setGmtCreate(java.util.Date gmtCreate)
    {
        this.gmtCreate=gmtCreate;
    }


    public java.util.Date getGmtModified()
    {
        return this.gmtModified;
    }


    public void setGmtModified(java.util.Date gmtModified)
    {
        this.gmtModified=gmtModified;
    }


    @Override
    public String toString()
    {
        return com.ashan.hakka.common.util.PojoUtils.toString(this);
    }
}