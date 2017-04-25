

package cn.com.git.udmp.common.utils;

import cn.com.git.udmp.modules.tools.service.KeyGenService;

/**
 * @description 业务主键生成工具类
 * @author liang
 * @instruction 1.在数据库的T_KEY_RULE表中定义生成规则</br>
 *              表结构：GEN_ID：主键ID,BIZ_ID:业务编号，BIZ_NAME：业务含义，DATA_PATTERN：时间格式，
 *              RANDOM_RANGE：随机数范围，SEQ_SEGMENT:序列批次长度，OTHER1：预留属性1，OTHER2：预留属性2，
 *              GEN_RULE:生成规则，VALID_RULE:校验规则，VALID_PARAM：校验规则参数</br>
 *              2.根据ID获取主键，主键规则有spring的el表达式构成</br>
 */
public class KeyGenUtils {

    private static KeyGenService service;

    /**
     * @param genId
     *            主键生成规则ID
     * @return 主键
     */
    public static String getKey(String genId) {
        return getKey(genId,null,null);
    }
    
    
    /**
     * @param genId 生成规则ID
     * @param other1 拓展元素1
     * @param other2 拓展元素2
     * @return
     */
    public static String getKey(String genId,String other1,String other2){
        if (service == null) {
            init();
        }
        return service.getKey(genId,other1,other2);
    }

    /**
     * 
     */
    private static void init() {
        service = SpringContextHolder.getBean(KeyGenService.class);
    }
}
