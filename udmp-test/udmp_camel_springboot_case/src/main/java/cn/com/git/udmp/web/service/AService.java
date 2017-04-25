package cn.com.git.udmp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AService {
    @Autowired
    BService b;
    
    public String a(){
        System.out.println(b.b());
        return "a";
    }
    
}
