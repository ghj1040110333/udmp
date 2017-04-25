package cn.com.git.udmp.batch.controller.base;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.web.BaseController;
import cn.com.git.udmp.component.model.CurrentPage;

public class BaseBatchController extends BaseController {
    /**
     * @title
     * @description 转换原批处理的page对象映射为现udmp中分页组件的page对象
     * 
     * @param pageNo
     * @param pageSize
     * @param result
     * @return
     */
    protected <T> Page<T> currentPageToPage(int pageNo, int pageSize, CurrentPage<T> result) {
        Page<T> page = new Page<T>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setList(result.getPageItems());
        page.setCount(result.getTotal());
        return page;
    }
}
