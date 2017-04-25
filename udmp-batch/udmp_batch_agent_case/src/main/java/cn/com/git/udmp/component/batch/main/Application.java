package cn.com.git.udmp.component.batch.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.modules.batch.dao.BatchTestSouceMapper;

//@EnableAutoConfiguration
@ComponentScan(basePackages={"cn.com.git.udmp.common.utils","cn.com.git.udmp.component.batch","cn.com.git.udmp.modules.batch"})
@SpringBootApplication
public class Application{
    

    @Autowired
    private BatchTestSouceMapper testSouceMapper;

    
    
    public static void main(String[] args) {
        //不启动web容器
        ConfigurableApplicationContext context = new SpringApplicationBuilder(UdmpApplication.class).web(false).run(args);
    }

//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//      AssertionErrors.assertTrue("mybatis没有加载上", testSouceMapper!=null);
//      BatchTestSouce batchTestSouce = new BatchTestSouce();
//      batchTestSouce.setDesc(true);
//      List<BatchTestSouce> resultList = testSouceMapper.findList(batchTestSouce);
//      System.out.println(resultList.get(0).getNumA());
//      System.out.println(resultList.get(resultList.size()-1).getNumA());
//      
//      
//      Cursor<BatchTestSouce> resultCursor = testSouceMapper.findListByCursor();
//      for (BatchTestSouce obj : resultCursor) {
//        System.out.println(obj.getVcharC());
//      }
//      System.out.println("isopen():"+resultCursor.isOpen());
//      Iterator<BatchTestSouce> iterator = resultCursor.iterator();
//      System.out.println("是否cursor有结果:"+iterator.hasNext());
//      BatchTestSouce obj1 = iterator.next();
//      System.out.println(obj1.getVcharC());
//      System.out.println("isopen():"+resultCursor.isOpen());
//      
//      
////      List<Map> result = testSouceMapper.findIndexSection(0, 50);
////      System.out.println(result);
////      if(args.length==0){
////          server.accept("10000");
////      }else{
////          server.accept(args[0]);
////      }
//    }
}