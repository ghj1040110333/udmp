package cn.com.git.udmp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import cn.com.git.udmp.servcie.WebSocketService;

@Controller
public class WebsocketController {
    
    @Autowired
    private WebSocketService webSocketService;
    
    public WebSocketService getWebSocketService() {
        return webSocketService;
    }


    @MessageMapping("/greeting")
    @SendTo("/topic/greeting")
    public String greet(String word) {
        System.out.println("greeting123321");
        System.out.println(word);
        webSocketService.sendQuotes();
        return "hello world";
    }
    
    
//    @SubscribeMapping("/greeting")
//    public String getPositions(String principal) throws Exception {
//        return "test";
//    }
    
}
