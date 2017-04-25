package cn.com.git.udmp.test.batch.framework.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.impl.batch.job.dao.IBatchJobDao;
import cn.com.git.udmp.impl.batch.job.po.BatchJobPO;

/**
 * @description 测试job的dao操作
 * @author liuliang liuliang1@git.com.cn
 * @date 2015年12月25日 上午10:05:23
 */
public class BatchJobDaoTest extends AbstractTest {
    @Autowired
    private IBatchJobDao dao;

    public IBatchJobDao getDao() {
        return dao;
    }

    @Test
    public void testAdd() {
        System.out.println(dao == null);
        BatchJobPO batchJobPO = new BatchJobPO();
        batchJobPO.setJobName("test");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("job_name", "test");
        dao.add(map);
    }

}
