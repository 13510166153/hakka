package com.ashan.hakka.ucenter.dbo;



/**
 *自动生成的DO代码,对应表[#tableName#]的DO对象.
 *@author CreateCodeUtils
 *@date 2017/07/05
 */
public class TuEPermDO implements com.ashan.hakka.common.pojo.dbo.DBO
{

    private static final long serialVersionUID = 36195909L;


    /**
     *id
     **/
    private Integer id;


    /**
     *权限编码
     **/
    private String premCode;


    /**
     *权限描述
     **/
    private String premDesc;


    /**
     *权限对应的应用程序
     **/
    private Integer appId;


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


    public String getPremCode()
    {
        return this.premCode;
    }


    public void setPremCode(String premCode)
    {
        this.premCode=premCode;
    }


    public String getPremDesc()
    {
        return this.premDesc;
    }


    public void setPremDesc(String premDesc)
    {
        this.premDesc=premDesc;
    }


    public Integer getAppId()
    {
        return this.appId;
    }


    public void setAppId(Integer appId)
    {
        this.appId=appId;
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