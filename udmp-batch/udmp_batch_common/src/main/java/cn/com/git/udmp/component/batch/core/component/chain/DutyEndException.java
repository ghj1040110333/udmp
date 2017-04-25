package cn.com.git.udmp.component.batch.core.component.chain;

/**
 * @description 责任链终止异常
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月21日 上午11:23:57
 */
public class DutyEndException extends RuntimeException {

    public DutyEndException(String string) {
        super(string);
    }
    
    public DutyEndException(){
        super();
    }

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */

    private static final long serialVersionUID = -6005764249732358350L;

}
