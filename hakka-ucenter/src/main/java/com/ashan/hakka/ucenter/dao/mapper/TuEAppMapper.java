package com.ashan.hakka.ucenter.dao.mapper;

import java.util.List;

import com.ashan.hakka.ucenter.dbo.TuEAppDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author CreateCodeUtils
 * @date #currentDate#
 */
@Mapper
public interface TuEAppMapper {

    public TuEAppDO getTuEAppDOById(Long id);


    public List<TuEAppDO> listAllTuEApp();
}
