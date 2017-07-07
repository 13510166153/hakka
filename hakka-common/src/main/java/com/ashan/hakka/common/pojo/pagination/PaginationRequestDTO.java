package com.ashan.hakka.common.pojo.pagination;


import com.ashan.hakka.common.pojo.dto.RequestDTO;
import com.ashan.hakka.common.util.PojoUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *分页查询请求对象.
 * <PRE>
 *  调用{@linkplain #setCurrentPage(Integer)}和{@linkplain #setPageSize(Integer)}方法时,会调用{@linkplain #calOffset()}设置offset的值
 * </PRE>
 *
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/23
 * @see #setCurrentPage(Integer)
 * @see #setPageSize(Integer)
 * @see #calOffset()
 * @see Pagination
 */
@ApiModel(value="分页查询请求")
public class PaginationRequestDTO extends RequestDTO {

    /**
     * 默认的查询页
     */
    public static final Integer DEFAULT_CURRENT_PAGE=new Integer(1);

    /**
     * 默认的分页记录数
     */
    public static final Integer DEFAULT_PAGE_SIZE=new Integer(20);

    private static final long serialVersionUID = 4538420877750972611L;


    @ApiModelProperty(value="当前页")
    private Integer currentPage;


    @ApiModelProperty(value="每页记录数")
    private Integer pageSize;

    @ApiModelProperty(value="排序字段")
    private String orderField;

    @ApiModelProperty(value="排序规则:ASC-升序,DESC-降序")
    private String orderType;


    /**
     * 查询记录时的偏移数
     */
    private Integer offset;



    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * 触发{@linkplain #calOffset()}方法
     * @param currentPage
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date ${YEAR}/${MONTH}/${DAY}
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        this.calOffset();
    }

    public Integer getPageSize() {
        return pageSize;
    }


    /**
     * 触发{@linkplain #calOffset()}方法
     * @param pageSize
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/7/3
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.calOffset();
    }

    public Integer getOffset() {
        return offset;
    }

    /**
     * 计算{@linkplain #offset}属性
     *
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date 2017/7/3
     */
    private  void calOffset()
    {
        int _currentPage=this.currentPage==null?DEFAULT_CURRENT_PAGE:this.currentPage.intValue();
        int _pageSize=this.pageSize==null?DEFAULT_PAGE_SIZE:this.pageSize.intValue();
        this.offset=new Integer(_currentPage-1)*_pageSize;
    }

    public String getOrderField() {
        return PojoUtils.doFieldName2DBFieldName(this.orderField);
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString(){
        return PojoUtils.toString(this);
    }

}
