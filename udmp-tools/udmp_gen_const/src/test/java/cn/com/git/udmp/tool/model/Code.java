

package cn.com.git.udmp.tool.model;

/**
 * @description 
 * @author liang
 *
 */
public class Code {
    /**
     * @description 
     */
    public Code() {
        super();
    }

    private String value;
    /**
     * @description 
     * @param _value
     * @param _id
     * @param _sort
     */
    public Code( String _id,String _value, String _sort) {
        this.id = _id;
        this.value = _value;
        this.sort = _sort;
    }
    
    private String id;
    private String sort;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
