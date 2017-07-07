package com.ashan.hakka.common.dao.createcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ashan.hakka.common.exception.CommonException;
import com.ashan.hakka.common.util.PojoUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.FileCopyUtils;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/28
 */
public class CreateCodeUtils {

    /**
     *
     * 数据库连接
     *
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/28
     */
    private static String url="jdbc:mysql://localhost:3306/hakka";

    /**
     * 用户名
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/28
     */
    private static String userName="root";

    /**
     * 密码
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/28
     */
    private static String password="123456";

    /**
     * 驱动
     */
    private static String driverClassName="com.mysql.jdbc.Driver";

    /**
     * 包名
     */
    private static String packageName="com.ashan.hakka.ucenter";


    private static String srcDir="C:\\Users\\a\\Desktop\\work\\20170629\\";



    /**
     * TABLE的一个常量
     */
    private static final String[] TABELE_ARRAY=new String[]{"TABLE"};

    /**
     * 根据TABLE获取DBTable对象,如果表不存在,返回null.
     * @param tableName
     * @return
     * @throws Exception
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/28
     */
    public static DBTable getDBTable(String  tableName) throws Exception{
        Class.forName(driverClassName);
        Connection conn= DriverManager.getConnection(url,userName,password);

        DatabaseMetaData dbMetaData= conn.getMetaData();
        ResultSet rs=dbMetaData.getColumns(null,"%",tableName,"%");
        try {
            DBTable table = null;
            while (rs.next()) {
                if(table==null){
                    table=new DBTable();
                    table.setTableName(rs.getString("TABLE_NAME"));
                    table.setFields(new ArrayList<DBField>(10));
                }

                String columnName=rs.getString("COLUMN_NAME");

                Integer columnType=rs.getInt("DATA_TYPE");

                String remark=rs.getString("REMARKS");

                DBField field=new DBField();
                field.setFieldDesc(remark);
                field.setFieldName(columnName);
                field.setFieldType(columnType);
                table.getFields().add(field);
            }
            return table;
        }
        finally {
            rs.close();
            conn.close();
        }
    }



















    private static String getType(int type){
        switch (type){
            case Types.BIGINT:
                return "Integer";
            case Types.BIT:
                return "Integer";
            case Types.CHAR:
                return "Character";
            case Types.DATE:
                return "java.util.Date";
            case Types.DOUBLE:
                return "Double";
            case Types.FLOAT:
                return "Float";
            case Types.INTEGER:
                return "Integer";
            case Types.NUMERIC:
                return "Number";
            case Types.NVARCHAR:
                return "String";
            case Types.TIME:
                return "java.util.Date";
            case Types.TIMESTAMP:
                return "java.util.Date";
            case Types.TINYINT:
                return "Integer";
            case Types.VARCHAR:
                return "String";

            default:
                throw new CommonException(-1,"不支持的sql类型:Type.ARRAY");
        }
    }









    private static String createClassFile(DBTable table,
        String packageName,
        String classFileHeaderName,
        String serialVersionUIDName,
        String fieldName,
        String getterAndSetterName,
        String toStringName
    ) throws Exception
    {
        StringBuilder sb=new StringBuilder();
        String classFileHeaderString=getString(classFileHeaderName);
        Map<String,String> param=new HashMap<>(16);
        param.put("packageName",packageName);
        param.put("dboName",PojoUtils.dbTableName2DOName(table.getTableName()));
        param.put("currentDate",DateFormatUtils.format(new Date(),"yyyy/MM/dd"));
        sb.append(replace(classFileHeaderString,param));
        sb.append("{");

        param.put("serialVersionUID", RandomUtils.nextInt()+"L");
        sb.append(replace(getString(serialVersionUIDName),param));

        String fieldString=getString(fieldName);
        for(DBField field:table.getFields())
        {
            Map<String,String> fieldMap=new HashMap<>(6);
            fieldMap.put("fieldDesc",field.getFieldDesc());
            fieldMap.put("fieldType",getType(field.getFieldType()));
            fieldMap.put("fieldName",PojoUtils.dbFieldName2DOFieldName(field.getFieldName()));
            sb.append(replace(fieldString,fieldMap));
        }

        String methodString=getString(getterAndSetterName);
        for(DBField field:table.getFields())
        {
            Map<String,String> fieldMap=new HashMap<>(6);
            fieldMap.put("fieldDesc",field.getFieldDesc());
            fieldMap.put("fieldType",getType(field.getFieldType()));
            fieldMap.put("fieldName",PojoUtils.dbFieldName2DOFieldName(field.getFieldName()));
            fieldMap.put("getName",PojoUtils.dbFieldName2GetterName(field.getFieldName()));
            fieldMap.put("setName",PojoUtils.dbFieldName2SetterName(field.getFieldName()));
            sb.append(replace(methodString,fieldMap));
        }

       sb.append(getString(toStringName));

        sb.append("}");
        System.out.print(sb);
        return sb.toString();
    }


    private static String replace(String context, Map<String,String> param)
    {
        for(Map.Entry<String,String> entry:param.entrySet())
        {
            context=context.replaceAll("#"+entry.getKey()+"#",entry.getValue());
        }
        return context;
    }


    private static String getString(String fileName) throws Exception{
        StringBuilder sb=new StringBuilder();
        BufferedReader br=new BufferedReader(new InputStreamReader(CreateCodeUtils.class.getResourceAsStream(fileName)));
        String line=null;
        while((line=br.readLine())!=null)
        {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }


    private static void createDBOFile() throws Exception{
        DBTable table=getDBTable("TU_E_PERM");
        String dboName=PojoUtils.dbTableName2DOName(table.getTableName())+"DO";
        String dboClass=createClassFile(table,
            packageName+".dbo","dboClassFileHeader.template","serialVersionUID.template","dboField.template","getterAndSetter.template","toString.template");

        String dboDirName=srcDir+"/src/"+(packageName.replaceAll("\\.","/"))+"/dbo";

        File dboDir=new File(dboDirName);

        if(!dboDir.exists())
        {
            dboDir.mkdirs();
        }

        PrintWriter pw=new PrintWriter(dboDirName+"/"+dboName+".java");

        pw.print(dboClass);
        pw.close();
    }

    private static void createDTOFile() throws Exception{
        DBTable table=getDBTable("TU_E_PERM");
        String dtoName=PojoUtils.dbTableName2DOName(table.getTableName())+"QueryDTO";
        String dtoClass=createClassFile(table,
            packageName+".dto","dtoClassFileHeader.template","serialVersionUID.template","dtoField.template","getterAndSetter.template","toString.template");

        String dboDirName=srcDir+"/src/"+(packageName.replaceAll("\\.","/"))+"/dto";

        File dboDir=new File(dboDirName);

        if(!dboDir.exists())
        {
            dboDir.mkdirs();
        }

        PrintWriter pw=new PrintWriter(dboDirName+"/"+dtoName+".java");

        pw.print(dtoClass);
        pw.close();
    }

    private static void createPDTOFile() throws Exception{
        DBTable table=getDBTable("TU_E_PERM");
        String dtoName=PojoUtils.dbTableName2DOName(table.getTableName())+"PaginationQueryDTO";
        String dtoClass=createClassFile(table,
            packageName+".dto","pdtoClassFileHeader.template","serialVersionUID.template","dtoField.template","getterAndSetter.template","toString.template");

        String dboDirName=srcDir+"/src/"+(packageName.replaceAll("\\.","/"))+"/dto";

        File dboDir=new File(dboDirName);

        if(!dboDir.exists())
        {
            dboDir.mkdirs();
        }

        PrintWriter pw=new PrintWriter(dboDirName+"/"+dtoName+".java");

        pw.print(dtoClass);
        pw.close();
    }


    public static void createMapperFile() throws Exception
    {
        Map<String,String> param=new HashMap<>(16);
        DBTable table=getDBTable("TU_E_PERM");
        String tmpString=getString("mapper.template");
        String pgName=packageName+".dao.mapper";
        param.put("packageName",pgName);
        param.put("dboName",PojoUtils.dbTableName2DOName(table.getTableName()));
        param.put("currentDate",DateFormatUtils.format(new Date(),"yyyy/MM/dd"));
        String fileString=replace(tmpString,param);
        System.out.println(fileString);

        String mapperName=PojoUtils.dbTableName2DOName(table.getTableName())+"Mapper";
        String dboDirName=srcDir+"/src/"+(pgName.replaceAll("\\.","/"));
        File dboDir=new File(dboDirName);
        if(!dboDir.exists())
        {
            dboDir.mkdirs();
        }
        PrintWriter pw=new PrintWriter(dboDirName+"/"+mapperName+".java");
        pw.print(fileString);
        pw.close();

    }


    public static void main(String[] args) throws  Exception{
        //createDTOFile();
        createDBOFile();
        //createPDTOFile();
        //createMapperFile();

    }


}
