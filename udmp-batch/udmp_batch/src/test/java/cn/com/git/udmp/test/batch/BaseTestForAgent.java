package cn.com.git.udmp.test.batch;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionTemplate;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.core.logging.ILog;

/**
 * 
 * @description 
 * @author Spring Cao
 * @date 2016年8月30日 上午11:41:12
 */
@ContextConfiguration(locations = { "classpath*:META-INF/applicationContextTestForAgent.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTestForAgent implements ILog{
    public final static String UNIT_TEST_USER = "hexintest";
    protected TransactionTemplate transactionTemplate;
    protected JdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    protected ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private ApplicationContext context;

    /**
     * @description 获取Spring Bean
     * @version
     * @title
     * @param name bean名称
     * @return spring配置中对应的bean实例
     */
    public Object getBean(String name) {
        if (context == null) {
            context = SpringContextHolder.getApplicationContext();
        }
        return context.getBean(name);
    }

//    @Resource(name = "dataSource")
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

//    @Resource(name = "threadPoolTaskExecutor")
//    public void setTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor1) {
//        this.threadPoolTaskExecutor = threadPoolTaskExecutor1;
//    }

//    @Autowired
//    public void setTransactionManager(PlatformTransactionManager transactionManager) { // 注入TransactionManager
//        // 以注入的TransactionManager作为参数，获取一个TransactionTemplate实例，该实例封装了Hibernate的行为
//        this.transactionTemplate = new TransactionTemplate(transactionManager); 
//
//    }

    /**
     * 重载这个方法，设置不同的测试用户
     * 
     * @description
     * @version
     * @title
     * @return 测试用户名
     */
    protected String getTestUserName() {
        return UNIT_TEST_USER;
    }

    /**
     * @description 初始化方法
     * @version
     * @title
     */
    @Before
    public void setupAbstractTest() {
        bindAppUser();
//        baseDao = (IBaseDao) getBean("baseDao");
    }

    /**
     * @description Bind current user to current thread
     * @version
     * @title
     */
    private void bindAppUser() {
//        String userName = getTestUserName();
//        LoginUCC ucc = (LoginUCC) getBean("loginUCC");
//        // 创建一个登录用户的对象
//        AppUser user = ucc.findAppUser(userName);
//
//        AppUserContext.setCurrentUser(user);
    }
}
