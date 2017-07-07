package com.ashan.hakka.ucenter.bo;

import java.util.List;

import com.ashan.hakka.common.pojo.bo.BO;
import com.ashan.hakka.common.util.PojoUtils;
import com.ashan.hakka.ucenter.dbo.TuEAppDO;
import com.ashan.hakka.ucenter.dbo.TuEPermDO;

/**
 *
 * 应用程序和对应的所有权限.
 *
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/05
 */
public class TuEAppWithPermBO implements BO {

    private static final long serialVersionUID = -5424222568385095064L;

    /**
     * 应用程序信息
     */
    private TuEAppDO app;

    /**
     * 对应的权限信息
     */
    private List<TuEPermDO> perms;

    public TuEAppDO getApp() {
        return app;
    }

    public void setApp(TuEAppDO app) {
        this.app = app;
    }

    public List<TuEPermDO> getPerms() {
        return perms;
    }

    public void setPerms(List<TuEPermDO> perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
