package van.bookbookprovider.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import van.bookbookprovider.service.RedisPubService;

@Component
public class RedisPubServiceImpl implements RedisPubService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendMessage(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }
}
