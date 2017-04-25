

package cn.com.git.udmp.test.utils.bizdate;

import java.text.ParseException;

import org.junit.Test;

import cn.com.git.udmp.common.utils.bizdate.DateContext;
import cn.com.git.udmp.test.base.AbstractTest;

/**
 * @author liang
 *
 */
public class BizDateUtilTest extends AbstractTest{
    @Test
    public void getBizDate(){
        try {
            System.out.println(DateContext.getBizDateById("123", "1"));
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
