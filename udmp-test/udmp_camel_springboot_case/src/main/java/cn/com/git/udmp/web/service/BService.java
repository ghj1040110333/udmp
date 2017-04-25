package cn.com.git.udmp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BService {
    @Autowired
    AService a;

    public String b() {
//        return a.a();
        return "b";
    }
    
    public String c(){
        return a.a();
    }
}
