

package cn.com.git.udmp.component.batch.common.utils;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * @description 负载均分工具类
 * @author Liang liuliang1@git.com.cn
 * @date 2016年9月18日 下午2:10:23
 */
public class BalanceUtil {

    /**
     * @title
     * @description 闭区间[startNum,endNum]按照线程数num做均分，startNum和endNum不能为负数
     * 
     * @param num 线程数
     * @param startNum 闭区间起始值
     * @param endNum 闭区间结束值
     * @return 分区集
    */
    public static List<Section> balanceArray(int num, long startNum, long endNum) {
        Preconditions.checkArgument(num != 0);
        List<Section> list = Lists.newArrayList();
        long counts = endNum - startNum + 1;
        long eachCounts =0;
        if(counts>num){
            eachCounts = counts / num;
        }
        long mod = counts%num;//取模
        //若刚好取模等于0（即能被整除，每个分片处理总数+1）
        if(mod==0){
            eachCounts+=1;
        }
        for (int i = 1; i < num + 1&&startNum<=endNum&&startNum>=0&&endNum>=0; i++) {
            Section section = new Section();
            section.setStartNum(startNum);
            if(startNum+eachCounts>endNum){
                section.setEndNum(endNum);
                startNum+=eachCounts;
            }else if(i==num){
                section.setEndNum(endNum);
                startNum=endNum+1;
            }else{
                if(i<mod+1){
                    //若当前分片序号未达到取到的模，则当前分片需要多处理一条记录
                    section.setEndNum(startNum + eachCounts);
                    startNum+=eachCounts+1;
                }else{
                    //若已超过取到的模，则由于当前区间显示的是闭区间，所以需要设置endNum为起始值+eachCounts-1
                    section.setEndNum(startNum + eachCounts-1);
                    startNum+=eachCounts;
                }
            }
            list.add(section);
        }
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println(BalanceUtil.balanceArray(10, 0, 3));
        System.out.println(BalanceUtil.balanceArray(10, 0, 9));
        System.out.println(BalanceUtil.balanceArray(10, 0, 10));
        System.out.println(BalanceUtil.balanceArray(10, 0, 16));
        System.out.println(BalanceUtil.balanceArray(10, 0, 20));
        System.out.println(BalanceUtil.balanceArray(10, 0, 0));
        System.out.println(BalanceUtil.balanceArray(10, 0, 1));
        System.out.println(BalanceUtil.balanceArray(10, 0, -1));
        System.out.println(BalanceUtil.balanceArray(10, -1, 1));
        System.out.println(BalanceUtil.balanceArray(10, -1, -1));
        System.out.println(BalanceUtil.balanceArray(10, 0, 200));
        System.out.println(BalanceUtil.balanceArray(10, 0, 201));
        System.out.println(BalanceUtil.balanceArray(10, 0, 199));
        System.out.println(BalanceUtil.balanceArray(1, 0, 1));
    }

    public static class Section {
        private Long startNum;
        private Long endNum;

        /**
         * @return startNum
         */
        public Long getStartNum() {
            return startNum;
        }

        /**
         * @param startNum 要设置的 startNum
         */
        public void setStartNum(Long startNum) {
            this.startNum = startNum;
        }

        /**
         * @return endNum
         */
        public Long getEndNum() {
            return endNum;
        }

        /**
         * @param endNum 要设置的 endNum
         */
        public void setEndNum(Long endNum) {
            this.endNum = endNum;
        }

        /**
         * @title
         * @description
         *
         * @see java.lang.Object#toString()
         * @return
         */
        @Override
        public String toString() {
            return "["+startNum + ":" + endNum+"]";
        }
    }

}
