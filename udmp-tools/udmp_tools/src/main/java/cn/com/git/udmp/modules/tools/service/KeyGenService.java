

package cn.com.git.udmp.modules.tools.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.utils.CacheUtils;
import cn.com.git.udmp.common.utils.DateUtils;
import cn.com.git.udmp.modules.tools.dao.KeyGenRuleDao;
import cn.com.git.udmp.modules.tools.entity.KeyGenConst;
import cn.com.git.udmp.modules.tools.entity.KeyGenRule;

/**
 * 业务主键生成服务
 * 
 * @author liang
 *
 */
@Service
@Transactional(readOnly = true)
public class KeyGenService {
    @Autowired
    private KeyGenRuleDao ruleDao;
    @Autowired
    private KeyGenSeqService seqServcie;
    
    private final String CACHE_GEN_RULE_KEY = "CACHE_GEN_RULE_KEY";

    private Random random = new Random();

    /**
     * @description 根据业务主键的生成规则生成业务主键
     * @param genId
     *            业务主键生成规则ID
     * @return 业务主键
     */
    public String getKey(String genId) {
        return getKey(genId, null, null);
    }

    public String getKey(String genId, String other1, String other2) {
        // 根据需要的业务规则ID获取规则对象
    	//updated by liang on 2016-04-19 查询规则走缓存，不然规则查询太多会撑爆数据库 
    	KeyGenRule ruleObj;
    	Map<String,Object> map = new HashMap<String, Object>();
    	if(null!=CacheUtils.get(CACHE_GEN_RULE_KEY)){
    		map = (Map<String, Object>) CacheUtils.get(CACHE_GEN_RULE_KEY);
    	}
    	if(null==map.get(genId)){
    		ruleObj = ruleDao.selectByPrimaryKey(genId);
    		map.put(genId, ruleObj);
    		CacheUtils.put(CACHE_GEN_RULE_KEY, map);
    	}else{
    		ruleObj = (KeyGenRule) map.get(genId);
    	}
    	if(ruleObj==null){
    		throw new RuntimeException("查询不到规则");
    	}
        if (!cn.com.git.udmp.common.utils.StringUtils.isEmpty(other1)) {
            ruleObj.setOther1(other1);
        }
        if (!cn.com.git.udmp.common.utils.StringUtils.isEmpty(other2)) {
            ruleObj.setOther1(other2);
        }
        return genKeyByRule(ruleObj);
        // TODO 业务主键规则表中还预留了校验规则，可以用来拓展生成的业务主键的校验逻辑
    }

    /**
     * @param ruleObj
     *            已配置规则对象，具体的规则是spEL表达式，例如:"#bizId+#date+#seq"
     * @return 根据规则生成的业务主键
     */
    public String genKeyByRule(KeyGenRule ruleObj) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(ruleObj.getGenRule());
        StandardEvaluationContext context = new StandardEvaluationContext();
        context = setValue(context, ruleObj);
        String message = (String) exp.getValue(context);
        return message;

    }

    /**
     * @description 设置参数到el表达式规则中
     * @param context
     *            上下文参数
     * @param ruleObj
     *            包含参数的规则对象
     */
    private StandardEvaluationContext setValue(StandardEvaluationContext context, KeyGenRule ruleObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KeyGenConst.BIZ_ID, ruleObj.getBizId());
        String rule = ruleObj.getGenRule();
        // 日期获取
        if (rule.contains(KeyGenConst.DATE)) {
            if (StringUtils.isNotEmpty(ruleObj.getDatePattern())) {
                map.put(KeyGenConst.DATE, DateUtils.formatDate(new Date(), ruleObj.getDatePattern()));
            } else {
                map.put(KeyGenConst.DATE, DateUtils.formatDate(new Date(), "yyyyMMdd"));
            }
        }
        // 随机数获取
        if (rule.contains(KeyGenConst.RANDOM) && StringUtils.isNotEmpty(ruleObj.getRandomRange())) {

            map.put(KeyGenConst.RANDOM, fixZero(ruleObj.getRandomRange().length(),
                    new Long(random.nextInt(Integer.valueOf(ruleObj.getRandomRange())))));
        }
        // 递增序列获取
        if (rule.contains(KeyGenConst.SEQ)) {
            int segment = 0;
            int incrementNum = 1;
            if (ruleObj.getSeqSegment() != null && ruleObj.getSeqSegment() != 0) {
                segment = ruleObj.getSeqSegment();
            }
            if (ruleObj.getIncrementNum() != null && ruleObj.getIncrementNum() != 0) {
                incrementNum = ruleObj.getIncrementNum();
            }

            // 通过序列服务类获取对应规则的序列号
            Long seq = seqServcie.getSeq(ruleObj.getGenId(), ruleObj.getSeqRange(), segment, incrementNum);
            // 自动补0
            String seqFormat = fixZero(ruleObj.getSeqRange().length(), seq);
            // String seqFormat =
            // String.format("%0"+ruleObj.getSeqRange().length()+"d", seq);
            map.put(KeyGenConst.SEQ, seqFormat);
        }
        // 其他属性1获取
        if (rule.contains(KeyGenConst.OTHER1) && StringUtils.isNotEmpty(ruleObj.getOther1())) {
            map.put(KeyGenConst.OTHER1, ruleObj.getOther1());
        }
        // 其他属性2获取
        if (rule.contains(KeyGenConst.OTHER2) && StringUtils.isNotEmpty(ruleObj.getOther2())) {
            map.put(KeyGenConst.OTHER2, ruleObj.getOther2());
        }
        context.setVariables(map);
        return context;
    }

    /**
     * @description 自动补0方法
     * @param length
     * @param seq
     * @return
     */
    private String fixZero(int length, Long seq) {
        return String.format("%0" + length + "d", seq);
    }

    public static void main(String[] args) {
        // KeyGenRule rule = new KeyGenRule();
        // rule.setGenId("1");
        // rule.setBizId("BJZ");
        // rule.setDatePattern("yyyyMMdd");
        // rule.setGenRule("#bizId+#date+#seq");
        // KeyGenService service = new KeyGenService();
        // System.out.println(service.genKeyByRule(rule));
        // System.out.println(new Random().nextInt(1000));
        String seq = "12";
        String seqFormat = String.format("%0" + seq.toString().length() + "d", 100);
        System.out.println(seqFormat);
    }
}
