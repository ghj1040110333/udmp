package cn.com.git.udmp.test.base;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.WebApplicationContext;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.modules.sys.security.UsernamePasswordToken;

/**
 * @description junit测试的基类
 * @author liuliang
 * @date 2014年9月11日 下午2:27:39
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:META-INF/spring*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractMVCTest implements ILog {
    /**
     * @Fields UNIT_TEST_USER : 用户代码
     */
    public static final String UNIT_TEST_USER = "DEMO";
    /**
     * @Fields transactionTemplate : TransactionTemplate
     */
    protected TransactionTemplate transactionTemplate;
    /**
     * @Fields jdbcTemplate : JdbcTemplate
     */
    protected JdbcTemplate jdbcTemplate;
    /**
     * @Fields dataSource : DataSource
     */
    protected DataSource dataSource;
    /**
     * @Fields threadPoolTaskExecutor : ThreadPoolTaskExecutor
     */
    protected ThreadPoolTaskExecutor taskExecutor;
    /**
     * @Fields context : ApplicationContext
     */
    private ApplicationContext context;

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private SecurityManager securityManager;

    /**
     * @description 获取Spring Bean
     * @version 1.0
     * @title 获取Spring Bean
     * @author liuliang liuliang_wb@newchinalife.com
     * @param name
     *            bean名称
     * @return spring配置中对应的bean实例
     */
    public Object getBean(String name) {
        if (context == null) {
            context = SpringContextHolder.getApplicationContext();
        }
        System.out.println("context:" + context == null);
        return context.getBean(name);
    }

    /**
     * @description 设置数据源
     * @version 1.0
     * @title 设置数据源
     * @author tanzhiliang tanzl_wb@newchinalife.com
     * @param dataSource
     *            数据源
     */
    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * @description 设置异步线程池
     * @version 1.0
     * @title 设置异步线程池
     * @author bihb bihb_wb@newchinalife.com
     * @param threadPoolTaskExecutor1
     *            ThreadPoolTaskExecutor
     */
    // @Resource(name = "threadPoolTaskExecutor")
    // public void setTaskExecutor(ThreadPoolTaskExecutor
    // threadPoolTaskExecutor1) {
    // this.taskExecutor = threadPoolTaskExecutor1;
    // }

    /**
     * @description 设置PlatformTransactionManager
     * @version 1.0
     * @title 设置PlatformTransactionManager
     * @author bihb bihb_wb@newchinalife.com
     * @param transactionManager
     *            PlatformTransactionManager
     */
    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) { // 注入TransactionManager
        // 以注入的TransactionManager作为参数，获取一个TransactionTemplate实例，该实例封装了Hibernate的行为
        this.transactionTemplate = new TransactionTemplate(transactionManager);

    }

    /**
     * 重载这个方法，设置不同的测试用户
     * 
     * @description 重载这个方法，设置不同的测试用户
     * @version 1.0
     * @title 重载这个方法，设置不同的测试用户
     * @author liuliang liuliang_wb@newchinalife.com
     * @return 测试用户名
     */
    protected String getTestUserName() {
        return UNIT_TEST_USER;
    }

    /**
     * @description 初始化方法
     * @version 1.0
     * @title 初始化方法
     * @author liuliang liuliang_wb@newchinalife.com
     */
    @Before
    public void setupAbstractTest() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        bindAppUser();
    }

    /**
     * @description Bind current user to current thread
     * @version 1.0
     * @title Bind current user to current thread
     * @author liuliang liuliang_wb@newchinalife.com
     */
    private void bindAppUser() {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        String username;
        String password;
        if (StringUtils.isNoneEmpty(getUserName()) && StringUtils.isNoneEmpty(getPassword())) {
            username = getUserName();
            password = getPassword();
        } else {
            username = "thinkgem";
            password = "admin";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray(), true, "", "", false);
        subject.login(token);// 登录
        logger.info("============={}==============", "登录完成");
    }

    /**
     * @return 用户名
     */
    public abstract String getUserName();

    /**
     * @return 密码
     */
    public abstract String getPassword();

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    public WebApplicationContext getWac() {
        return wac;
    }

    public void setWac(WebApplicationContext wac) {
        this.wac = wac;
    }

}
