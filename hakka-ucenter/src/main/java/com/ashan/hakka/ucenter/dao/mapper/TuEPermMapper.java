package com.ashan.hakka.ucenter.dao.mapper;

import java.util.List;

import com.ashan.hakka.ucenter.dbo.TuEPermDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/05
 */
@Mapper
public interface TuEPermMapper {

    /**
     * 根据appid获取其对应的所有权限.
     * @param appId
     * @return
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/7/5
     */
    public List<TuEPermDO> listTuEPermsByAppId(Long appId);
}
