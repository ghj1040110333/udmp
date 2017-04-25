package cn.com.git.udmp.calc.camel.job.impl;

import org.springframework.stereotype.Component;

import cn.com.git.udmp.boot.camel.processor.AbsProcessor;
import cn.com.git.udmp.common.model.DataObject;

@Component
public class Job1 extends AbsProcessor {
    public static int count = 0;

    @Override
    public <T extends DataObject> T process(T sourceObj) {
        logger.debug("执行任务...");
        logger.debug("处理数据:{}", sourceObj.getData());
        System.out.println(this);
        String word = sourceObj.getString("word");
        word += count++;
        sourceObj.setString("word", word);
        return sourceObj;
    }

    @Override
    public <T extends DataObject> void afterProcess(T sourceObj) {
        logger.debug("after exec...");
        logger.debug("处理后的数据是{}", sourceObj.getData());
    }

}
