

package cn.com.git.udmp.boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/** 
 * @description 
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年9月6日 下午3:09:11  
*/
@SpringBootApplication
@ComponentScan(basePackages={"cn.com.git.udmp.component.batch","cn.com.git.udmp.modules.batch"})
public class BatchBootConfig {

}
