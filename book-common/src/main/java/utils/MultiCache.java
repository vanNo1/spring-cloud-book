package utils;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MultiCache {
    @Resource
    private LocalCacheUtil localCacheUtil;

    public void delete(String name) {
        localCacheUtil.delete(name);
        RedisPoolUtil.del(name);

    }
}
