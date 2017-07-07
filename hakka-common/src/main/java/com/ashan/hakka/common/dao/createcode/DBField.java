package com.ashan.hakka.common.dao.createcode;

import com.ashan.hakka.common.util.PojoUtils;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/28
 */
public class DBField {

    private String fieldName;

    private String fieldDesc;

    private Integer fieldType;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
