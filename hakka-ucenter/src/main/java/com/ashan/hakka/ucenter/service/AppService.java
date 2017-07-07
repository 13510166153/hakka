package com.ashan.hakka.ucenter.service;

import java.util.List;

import com.ashan.hakka.ucenter.bo.TuEAppWithPermBO;
import com.ashan.hakka.ucenter.dbo.TuEAppDO;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/05
 */
public interface AppService {

    /**
     * 获取所有的应用信息.
     * @return
     */
    public List<TuEAppDO> listAllApp();

    /**
     * 根据appid获取应用信息及其所有的权限信息.
     * @param id
     * @return
     */
    public TuEAppWithPermBO getAppWithPermByAppId(Long id);
}
