package cn.com.git.udmp.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public WebSocketService(MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(cron="0/5 * * * * ? ")
    public void sendQuotes() {
        System.out.println("schedule task");
        this.messagingTemplate.convertAndSend("/topic/price.stock", "test");
    }
}
