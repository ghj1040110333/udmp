package cn.com.git.udmp.component.batch.model;

/** 
 * @description 分区对象
 * @author liuliang liuliang_wb@newchina.live 
 * @date 2015年7月7日 下午4:33:13  
*/
public class Section {
    private long startNum;
    private long endNum;
    public long getStartNum() {
        return startNum;
    }
    public void setStartNum(long startNum) {
        this.startNum = startNum;
    }
    public long getEndNum() {
        return endNum;
    }
    public void setEndNum(long endNum) {
        this.endNum = endNum;
    }
}
