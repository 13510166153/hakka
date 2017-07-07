package com.ashan.hakka.common.enums;

import com.ashan.hakka.common.util.PojoUtils;

/**
 *
 * 对应数据库中的is_delete字段.
 *
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/27
 */
public enum IsDelete {

    /**
     * 已经删除
     */
    DELETED(1,"已经删除"),

    /**
     * 未删除
     */
    NOT_DELETED(0,"未删除");


    private Integer code;


    private String desc;



    public static IsDelete valueOfCode(int code){
        if(code==0){
            return IsDelete.NOT_DELETED;
        }
        else if(code==1){
            return IsDelete.DELETED;
        }
        return null;
    }



    private IsDelete(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
