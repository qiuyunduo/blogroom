package cn.qyd.blogroom.common.utils;

import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 分页工具类
 */
public class PagingUtil {

    /**
     * 对数据进行分页
     *
     * @param page     当前页
     * @param limit    当页条数
     * @param dataList 数据列表
     * @return
     */
    public static PagingInfo paging(int page, int limit, List dataList) {
        if(page == 0)page = 1;
        final List data = Optional.ofNullable(dataList).orElse(Lists.newArrayList());
        final int size = dataList.size();
        final int totalSize = size < limit ? 1 : (int) Math.ceil((float) size / limit);
        final int start = ((page > totalSize ? totalSize : page) - 1)  * limit;
        int end = page * limit;
        end = end > size ? size : end;
        List subList = null;
        if (size > 0) {
            subList = data.subList(start, end);
        } else {
            subList = Lists.newArrayList();
        }
        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setFirstPage(page == 1 ? true : false);
        pagingInfo.setTotal(size);
        pagingInfo.setTotalPage(size % limit > 0 ? (size / limit) + 1 : size / limit);
        pagingInfo.setLastPage(page == pagingInfo.getTotalPage());
        pagingInfo.setPage(page);
        pagingInfo.setPrePage(pagingInfo.getFirstPage() ? 1 : page-1);
        pagingInfo.setNextPage(pagingInfo.getLastPage() ? pagingInfo.getTotalPage() : page+1);
        pagingInfo.setPageSize(subList.size());
        pagingInfo.setItem(subList);
        return pagingInfo;
    }

    /**
     * 生成PagingInfo,替换page中数据（例如将UserInfo -> UserInfoVo）
     *
     * @param page jpa 分页插件
     * @param item 集合
     * @return
     */
    public static PagingInfo page(Page page, List item) {
        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setItem(item);
        getPagePushPagingInfo(page, pagingInfo);
        return pagingInfo;
    }

    /**
     * 生成PagingInfo
     *
     * @param page jpa 分页插件
     * @return
     */
    public static PagingInfo page(Page page) {
        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setItem(page.getContent());
        getPagePushPagingInfo(page, pagingInfo);
        return pagingInfo;
    }

    /**
     * set pageingInfo 基本值
     *
     * @param page
     * @param pagingInfo
     */
    private static void getPagePushPagingInfo(Page page, PagingInfo pagingInfo) {
        pagingInfo.setPageSize(page.getNumberOfElements());
        pagingInfo.setPage(page.getNumber() + 1);
        pagingInfo.setPrePage(page.isFirst() ? 1 : page.getNumber());
        pagingInfo.setNextPage(page.isLast() ? page.getTotalPages() : page.getNumber()+2);
        pagingInfo.setTotal((int) page.getTotalElements());
        pagingInfo.setFirstPage(page.isFirst());
        pagingInfo.setLastPage(page.isLast());
        pagingInfo.setTotalPage(page.getTotalPages());
    }
}
