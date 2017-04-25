

package cn.com.git.udmp.boot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liang
 * 2016年8月24日
 */
@Controller
public class HelloController {
    @RequestMapping("/1")
    public String hello(){
        System.out.println("hello,world");
        return "/1";
    }
    
}
