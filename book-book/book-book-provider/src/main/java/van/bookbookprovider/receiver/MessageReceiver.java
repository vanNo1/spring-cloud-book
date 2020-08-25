package van.bookbookprovider.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import utils.MultiCache;

import javax.annotation.Resource;

@Component
@Slf4j
public class MessageReceiver {
    @Resource
    private MultiCache cache;

    /**
     * 接收消息的方法1
     **/
    public void receiveMessage(String message) {
        log.info("delete {}", message);
        cache.delete(message);
    }

    /**
     * 接收消息的方法2
     **/
    public void receiveMessage2(String message) {
        System.out.println("receiveMessage2接收到的消息：" + message);
    }

}
