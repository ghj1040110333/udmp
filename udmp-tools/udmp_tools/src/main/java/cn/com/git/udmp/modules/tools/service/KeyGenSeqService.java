

package cn.com.git.udmp.modules.tools.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import cn.com.git.udmp.modules.tools.dao.KeyGenSeqDao;
import cn.com.git.udmp.modules.tools.entity.KeyGenSeq;
import cn.com.git.udmp.modules.tools.entity.Record;

/**
 * @descrption 业务主键序列生成服务
 * @author liang
 */
@Service
@Transactional(readOnly = true)
public class KeyGenSeqService {
    @Autowired
    private KeyGenSeqDao seqDao;

    private static Map<String, Record> recordMap = Maps.newConcurrentMap();
    private int distance = 1000;//每次取出的序列数

    @Transactional(readOnly = false)
    public long getSeq(String genId, String range, int segment, int incrementNum) {
        /**
         * 序列获取逻辑：
         * 1.判断内存中是否已经取出对应规则ID的序列值域段
         * 2.如果内存没有序列值或者内存中序列值已用尽则重新获取从数据库获取新的序列值域段，从数据库获取一段序列值域段之后再更新数据库中的序列值，并返回新的序列编号;
         *   如果序列超过范围则重新开始从0获取序列
         * 3.如果不符合条件2则从代表内存中还有可用的序列域段，从内存中获取新的序列值并返回
         */
        if(segment==0){
            segment = distance;
        }
        Record record = recordMap.get(genId);
        if (record == null) {
            record = new Record();
        }
        // record对象读写的时候需要加锁，不然有可能脏读
        synchronized (record) {

            long startNum = record.getStartNum();
            long endNum = record.getEndNum();

            try {
                if (record.getStartNum() >= record.getEndNum()
                        || (record.getStartNum() + incrementNum) > record.getEndNum()) {
                    KeyGenSeq seqObj = seqDao.selectByPrimaryKey(genId.trim());
                    if(seqObj!=null){
                        startNum = Long.valueOf(seqObj.getSeqNum());
                    }
                    endNum = startNum + segment;
                    //准备更新序列表中的序列值
                    KeyGenSeq updateSeq = new KeyGenSeq();
                    updateSeq.setGenId(genId);
                    if(endNum>=Long.valueOf(range)){
                        //如果批次取出的最大值大于序列范围，则设置批次终止值为序列范围边界，同时初始化数据库中的序列值
                        endNum = Long.valueOf(range);
                        updateSeq.setSeqNum("0");
                    }else{
                        updateSeq.setSeqNum(String.valueOf(endNum));
                    }
                    
                    if(seqObj!=null){
                        seqDao.updateByPrimaryKey(updateSeq);
                    }else{
                        seqDao.insert(updateSeq);
                    }
                }
                return startNum;
            } finally {
                record.setStartNum(startNum + incrementNum);
                record.setEndNum(endNum);
                recordMap.put(genId, record);
            }
        }
    }

}
