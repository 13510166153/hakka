package com.ashan.hakka.common.dao.createcode;

import java.util.List;

import com.ashan.hakka.common.util.PojoUtils;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/28
 */
public class DBTable {

    private String tableName;


    private List<DBField> fields;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<DBField> getFields() {
        return fields;
    }

    public void setFields(List<DBField> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
