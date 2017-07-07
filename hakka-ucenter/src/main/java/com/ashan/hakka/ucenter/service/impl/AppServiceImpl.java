package com.ashan.hakka.ucenter.service.impl;

import java.util.List;

import com.ashan.hakka.ucenter.bo.TuEAppWithPermBO;
import com.ashan.hakka.ucenter.dao.mapper.TuEAppMapper;
import com.ashan.hakka.ucenter.dao.mapper.TuEPermMapper;
import com.ashan.hakka.ucenter.dbo.TuEAppDO;
import com.ashan.hakka.ucenter.dbo.TuEPermDO;
import com.ashan.hakka.ucenter.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/05
 */
@Service
public class AppServiceImpl implements AppService {

    private final Logger logger= LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private TuEAppMapper tuEAppMapper;

    @Autowired
    private TuEPermMapper tuEPermMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TuEAppDO> listAllApp() {
        return this.tuEAppMapper.listAllTuEApp();
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public TuEAppWithPermBO getAppWithPermByAppId(Long id) {
        //获取app信息
        TuEAppDO appDO=this.tuEAppMapper.getTuEAppDOById(id);


        //获取对应的权限信息

        List<TuEPermDO> list=this.tuEPermMapper.listTuEPermsByAppId(id);

        //组装TuEAppWithPermBO对象
        TuEAppWithPermBO appWithPermBO=new TuEAppWithPermBO();
        appWithPermBO.setApp(appDO);
        appWithPermBO.setPerms(list);
        return appWithPermBO;
    }
}
