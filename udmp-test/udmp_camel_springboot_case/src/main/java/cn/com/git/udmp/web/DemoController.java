package cn.com.git.udmp.web;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

@RestController
public class DemoController {
    @RequestMapping("/demo")
    public Map test(String name){
        System.out.println("Hello,world"+name);
        Map<String,String> map = Maps.newHashMap();
        map.put("name", "liang");
        map.put("IQ", "180");
        return map;
    }
}
