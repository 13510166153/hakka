package com.ashan.hakka.ucenter.web.controller;

import com.ashan.hakka.common.exception.CommonException;
import com.ashan.hakka.common.exception.ErrorCode;
import com.ashan.hakka.common.pojo.dto.RequestDTO;
import com.ashan.hakka.common.pojo.vo.ResponseVO;
import com.ashan.hakka.common.util.PojoUtils;
import com.ashan.hakka.common.web.controller.BaseController;
import com.ashan.hakka.ucenter.bo.TuEAppWithPermBO;
import com.ashan.hakka.ucenter.service.AppService;
import com.ashan.hakka.ucenter.vo.AppVO;
import com.ashan.hakka.ucenter.vo.PermVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/06
 */
@RestController
@RequestMapping("/app")
@Api(value="应用程序相关信息接口")
public class AppController extends BaseController{

    @Autowired
    private AppService appService;

    /**
     *
     */
    public static class GetAppByIdDTO extends RequestDTO{

        public GetAppByIdDTO(){
            System.out.println("GetAppByIdDTO..");
        }

        /**
         * appid
         */
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return PojoUtils.toString(this);
        }
    }



    @ApiOperation(value="根据应用程序id获取应用程序信息,包含应用程序的权限")
    @RequestMapping(value="/getAppById",method = RequestMethod.POST)
    public ResponseVO<AppVO> getAppById(@RequestBody GetAppByIdDTO getAppByIdDTO)
    {

        //校验入参是否合法
        if(getAppByIdDTO==null || getAppByIdDTO.getId()==null){
            throw new CommonException(ErrorCode.PARAM_ERR_CODE,"getAppByIdDTO 或 getAppByIdDTO.id 为空");
        }
        TuEAppWithPermBO appWithPermBO=this.appService.getAppWithPermByAppId(getAppByIdDTO.getId());

        //没有找到的情况下,报错
        if(appWithPermBO.getApp()==null)
        {
            throw new CommonException(ErrorCode.DATA_NOT_FOUND_ERR_CODE,"根据appid没有找到应用程序数据,appid="+getAppByIdDTO.getId());
        }


        //组装返回VO对象
        AppVO appVO=PojoUtils.instantiateBean(appWithPermBO.getApp(),AppVO.class);

        //处理权限,如果权限列表为空,则返回空的数组
        if(CollectionUtils.isEmpty(appWithPermBO.getPerms()))
        {
            appVO.setPerms(Lists.newArrayListWithCapacity(0));
        }
        else
        {
            appVO.setPerms(Lists.newArrayListWithCapacity(appWithPermBO.getPerms().size()));
            appWithPermBO.getPerms().forEach(tuEPermDO -> appVO.getPerms().add(PojoUtils.instantiateBean(tuEPermDO, PermVO.class)));
        }
        return PojoUtils.responseVO(getAppByIdDTO,appVO);
    }


}
