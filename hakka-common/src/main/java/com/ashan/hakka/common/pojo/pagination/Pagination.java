package com.ashan.hakka.common.pojo.pagination;

import java.util.Collection;

import com.ashan.hakka.common.pojo.POJO;
import com.ashan.hakka.common.util.PojoUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询结果对象.
 * <PRE>
 * 当{@linkplain #setPageSize(Integer)}或{@linkplain #setTotalItem(Integer)}方法被调用时,会触发{@linkplain
 * #calPageNum()}方法设置{@linkplain #pageNum}的值
 * </PRE>
 *
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/06/23
 * @see #setPageSize(Integer)
 * @see #setTotalItem(Integer)
 * @see #calPageNum()
 * @see PaginationRequestDTO
 */
@ApiModel(value = "分页查询结果对象")
public class Pagination<T> implements POJO {

    private static final long serialVersionUID = -5424222568685095096L;

    @ApiModelProperty(value = "当前页数")
    private Integer currentPage;

    @ApiModelProperty(value = "总记录数")
    private Integer totalItem;

    @ApiModelProperty(value = "总页数")
    private Integer pageNum;

    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;

    private Collection<T> results;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
        this.calPageNum();
        ;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.calPageNum();
    }

    public Collection<T> getResults() {
        return results;
    }

    public void setResults(Collection<T> results) {
        this.results = results;
    }

    /**
     * 计划总页数,{@linkplain #pageSize}和{@linkplain #totalItem}设置时都会调用.
     *
     * @author 李银山 yinshan.lys@alibaba-inc.com
     * @date ${YEAR}/${MONTH}/${DAY}
     * @see #setTotalItem(Integer)
     * @see #setPageSize(Integer)
     */
    public void calPageNum() {
        //两个属性都必须设置,才能计算pageNum
        if (this.totalItem != null && this.pageSize != null) {
            int _totalItem = this.totalItem.intValue();
            int _pageSize = this.pageSize.intValue();
            int num = _totalItem / _pageSize;
            if (_totalItem % _pageSize != 0) {
                num++;
            }
            this.pageNum = new Integer(num);
        }
    }

    @Override
    public String toString() {
        return PojoUtils.toString(this);
    }
}
