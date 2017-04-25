package cn.com.git.udmp.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

@RestController
public class TestController {
    @RequestMapping(path="/test/core")
    public Map testCore(){
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("123", "rees");
        System.out.println("test/core");
        return map;
    }
}
