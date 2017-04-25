

package cn.com.git.udmp.common.persistence;

import java.util.List;

import cn.com.git.udmp.common.model.DataObject;

/**
 * bootstrap-table表格插件类
 * 
 * @author yeqingping
 * @version 2016-1-8
 *
 */
public class Table extends DataObject{

    private long total = 0l;

    private List<?> rows;

    public Table(Page<?> page) {
        this.total = page.getCount();
        this.rows = page.getList();
    }

    /**
     * @return total
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total
     *            要设置的 total
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * @return rows
     */
    public List<?> getRows() {
        return rows;
    }

    /**
     * @param rows
     *            要设置的 rows
     */
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
