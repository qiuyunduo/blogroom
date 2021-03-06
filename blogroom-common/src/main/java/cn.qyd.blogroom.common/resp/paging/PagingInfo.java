package cn.qyd.blogroom.common.resp.paging;


import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页信息
 */
public class PagingInfo {

    @ApiModelProperty(notes = "当前页数")
    private Integer page;

    @ApiModelProperty(notes = "返回的当前页数据条数")
    private Integer pageSize;

    @ApiModelProperty(notes = "总共数据条数")
    private Integer total;

    @ApiModelProperty(notes = "总页数")
    private Integer totalPage;

    @ApiModelProperty(notes = "前一页")
    private Integer prePage;

    @ApiModelProperty(notes = "下一页")
    private Integer nextPage;

    @ApiModelProperty(notes = "是否为第一页")
    private boolean isFirstPage;

    @ApiModelProperty(notes = "是否为最后一页")
    private Boolean isLastPage;

    @ApiModelProperty(notes = "数据列表")
    private List item;


    //数据列表为空时返回该对象
    public static PagingInfo DEFAULT = getPaginInfo();

    private static PagingInfo getPaginInfo(){
        PagingInfo info = new PagingInfo();
        info.setFirstPage(true);
        info.setLastPage(true);
        info.setItem(Lists.newArrayList());
        info.setTotal(0);
        info.setTotalPage(0);
        info.setPage(1);
        info.setPrePage(0);
        info.setNextPage(0);
        info.setPageSize(10);
        return info;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Boolean getFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(Boolean firstPage) {
        isFirstPage = firstPage;
    }

    public Boolean getLastPage() {
        return isLastPage;
    }

    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }

    public List getItem() {
        return item;
    }

    public void setItem(List item) {
        this.item = item;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
