package com.ashan.hakka.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ashan.hakka.common.exception.ErrorCode;
import com.ashan.hakka.common.pojo.pagination.Pagination;
import com.ashan.hakka.common.pojo.vo.ResponseVO;
import com.ashan.hakka.common.exception.CommonException;
import com.ashan.hakka.common.pojo.dto.RequestDTO;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.BeanUtils;

/**
 *
 * 处理对象相关的工具类
 *
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/23
 */
public class PojoUtils {

    private static final String GETTER="get";


    private static final String SETTER="set";




    /**
     *
     * @param bean
     * @return
     */
    public static String toString(Object bean){
        return ReflectionToStringBuilder.toString(bean);
    }

    /**
     * Pagination对象的转换.
     * @param source
     * @param mapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> Pagination<R> mapPagination(Pagination<T> source, Function<? super T, ? extends R> mapper){
        Pagination<R> p=new Pagination<>();
        p.setCurrentPage(source.getCurrentPage());
        p.setPageSize(source.getPageSize());
        p.setTotalItem(source.getTotalItem());
        if(source.getResults()!=null){
            p.setResults(source.getResults().stream().map(mapper).collect(Collectors.toList()));
        }
        else{
            source.setResults(Lists.newArrayListWithCapacity(0));
        }
        return p;
    }

    /**
     * Pagination转换.
     * @param source
     * @param clazz
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> Pagination<R> mapPagination(Pagination<T> source,Class<R> clazz){
        return mapPagination(source,obj->instantiateBean(obj,clazz));
    }


    /**
     * 根据class创建一个对象,并从source对象中复制属性.
     * @param source
     * @param clazz
     * @param <R>
     * @return
     */
    public static <R> R instantiateBean(Object source,Class<R> clazz){
        R r= BeanUtils.instantiate(clazz);
        BeanUtils.copyProperties(source,r);
        return r;
    }

    /**
     * 反射拿到实例.
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> tClass){
        return BeanUtils.instantiate(tClass);
    }

    /**
     * 生成一个响应成功的ResponseVO.
     * @param dto
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> responseVO(RequestDTO dto, T t){
        ResponseVO<T> vo=new ResponseVO<>();
        vo.setData(t);
        vo.setErrCode(ErrorCode.SUCCESS_CODE);
        vo.setErrInfo(ErrorCode.SUCCESS_INFO);
        vo.setRequestId(dto.getRequestId());
        vo.setResponseTime(System.currentTimeMillis());
        return vo;
    }

    /**
     * 生成一个响应失败的VO
     * @param dto
     * @param e
     * @return
     */
    public static ResponseVO<String> errorResponseVO(RequestDTO dto, Exception e){
        ResponseVO<String> vo=new ResponseVO<>();
        vo.setRequestId(dto.getRequestId());
        vo.setResponseTime(System.currentTimeMillis());
        if(e instanceof CommonException)
        {
            CommonException ce=(CommonException)e;
            vo.setErrCode(ce.getErrCode());
            vo.setErrInfo(ce.getErrInfo());
        }
        else
        {
            vo.setErrCode(ErrorCode.UNKNOWN_ERR_CODE);
            vo.setErrInfo(e.getMessage());
        }
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        PrintStream ps=new PrintStream(os);
        e.printStackTrace(ps);
        vo.setData(os.toString());
        return vo;
    }

    /**
     * 将数据库的名称信息转换成DO对象的名称信息.
     * <PRE>
     *      转换规则:下划线转驼峰,如:tu_e_app=>TuEApp,user_name=>userName
     *      数据库的名称信息包含:
     *          1.表名
     *          2.字段名
     *      DO对象名称信息包含:
     *          1.类名
     *          2.属性名
     * </PRE>
     * @param dbName 数据库的名称信息
     * @param isFirstUpper 首字母是否大写
     * @return
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/29
     */
    public static String dbName2DOName(String dbName,boolean isFirstUpper){
        if(StringUtils.isBlank(dbName))
        {
            return dbName;
        }

        StringBuilder sb=new StringBuilder();


        char lastChar=dbName.charAt(0);
        //首字母
        sb.append(isFirstUpper?Character.toUpperCase(lastChar):Character.toLowerCase(lastChar));

        for(int i=1;i<dbName.length();i++)
        {
            char c=dbName.charAt(i);
            //如果不划线
            if(c!='_')
            {
                //如果上一字母是下划线,大写,否则下写
                sb.append(lastChar=='_'?Character.toUpperCase(c):Character.toLowerCase(c));
            }
            lastChar=c;
        }
        return sb.toString();
    }

    /**
     * 表字段名转do字段名.
     * @param dbFieldName
     * @return
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/29
     */
    public static String dbFieldName2DOFieldName(String dbFieldName)
    {
        return dbName2DOName(dbFieldName,false);
    }

    /**
     * 表名转DO的类名.
     * @param dbTableName
     * @return
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/29
     */
    public static String dbTableName2DOName(String dbTableName)
    {
        return dbName2DOName(dbTableName,true);
    }

    /**
     * 数据库字段名转DO属性的getter方法名
     * @param dbFieldName
     * @return
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/29
     */
    public static String dbFieldName2GetterName(String dbFieldName)
    {
        return GETTER+dbName2DOName(dbFieldName,true);
    }

    /**
     * 数据库字段名转DO属性的setter方法名
     * @param dbFieldName
     * @return
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/6/29
     */
    public static String dbFieldName2SetterName(String dbFieldName)
    {
        return SETTER+dbName2DOName(dbFieldName,true);
    }

    /**
     * 将DO对象中的属性名称转换成数据库的字段名.
     * <PRE>
     *     转换逻辑:
     *     dboFieldName中大写字母转小写的同时,在前面增加下划线.如:
     *     userName=>user_name
     *     updateOptrId=>update_optr_id
     * </PRE>
     * @param dboFieldName
     * @return
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/7/6
     */
    public static String doFieldName2DBFieldName(String dboFieldName){
        if(dboFieldName==null){
            return null;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<dboFieldName.length();i++)
        {
            char c=dboFieldName.charAt(i);
            if(Character.isUpperCase(c))
            {
                //如果是大写字母
                //增加下划线
                sb.append("_");
                //转成小写字母
                sb.append(Character.toLowerCase(c));
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 复制Bean的属性值.
     * <PRE>
     *     注意:只有字段名与类型一样的属性才能被复制
     * </PRE>
     * @param source
     * @param target
     * @see  BeanUtils#copyProperties(Object, Object)
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/7/6
     */
    public static void copyProperties(Object source,Object target){
        BeanUtils.copyProperties(source, target);
    }



}
