

package cn.com.git.udmp.common.utils.bizdate;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.utils.bizdate.support.BizTimeHandlerFactory;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.modules.sys.dao.BizTimeDao;
import cn.com.git.udmp.modules.sys.entity.BizTime;

/**
 * 
 * @description 日期上下文
 * @author liang
 *
 */
public class DateContext implements ILog{
    
    private static BizTimeDao dao =null;
    private static Map<String,BizTime> map = new HashMap<String, BizTime>();
    
    public static void init(){
        dao =  SpringContextHolder.getApplicationContext().getBean(BizTimeDao.class);
    }
    /**
     * @description 获取对应ID和对应类型关联的业务时间
     * @param id  请求ID
     * @param idType 请求ID类型
     * @return 时间
     * @throws ParseException 
     */
    public static Date getBizDateById(String id,String idType) throws ParseException{
        if(dao==null){
            init();
        }
//        Assert.assertEquals(dao, null);
        /**
         * 1.判断内存中是否已经存在了对应请求参数的时间处理缓存
         * 2.设置参数（请求ID，请求ID类型，是否批处理时间标志）
         * 3.获取对应的时间、时间类型以及时间处理机制（偏移量、设置时间、不生效等）
         * 4.根据对应的处理机制处理时间返回
         * TODO 也可以加上时区等变量作为参数
         */
        BizTime result = null;
        if(map.get(id+idType)!=null){
            //从缓存中获取对应的业务时间配置
            result = map.get(id+idType);
        }else{
            //重新从数据库获取业务时间配置
            BizTime entity = new BizTime();
            entity.setCustomId(id);
            entity.setIdType(idType);
            result = dao.selectByPrimaryKey(entity);
            map.put(id+idType, result);
        }
        if(result==null){
            return new Date();
        }else{
            return handle(result);
        }
        
        
        
//        DateUtils.parseDate(bizTime.get, parsePatterns)
    }
    /**
     * @param result
     * @return
     */
    private static Date handle(BizTime result) {
        return BizTimeHandlerFactory.getBizTimeHandler(result.getCustomType()).handler(result);
    }
}
