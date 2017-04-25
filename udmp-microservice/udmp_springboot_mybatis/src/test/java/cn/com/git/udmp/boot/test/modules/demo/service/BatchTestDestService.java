package cn.com.git.udmp.boot.test.modules.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.boot.test.modules.base.IBizService;
import cn.com.git.udmp.boot.test.modules.demo.dao.BatchTestDestMapper;
import cn.com.git.udmp.boot.test.modules.demo.entity.BatchTestDest;


@Service
public class BatchTestDestService implements IBizService{
    @Autowired
    BatchTestDestMapper mapper;
    
    public BatchTestDest selectByPrimaryKey(Long numA){
        return mapper.selectByPrimaryKey(numA);
    }
}
