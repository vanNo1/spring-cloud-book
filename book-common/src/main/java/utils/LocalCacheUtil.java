package utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class LocalCacheUtil {
    private Cache<String, Object> localCache;

   public LocalCacheUtil() {
        this.localCache = Caffeine.newBuilder().initialCapacity(50)
                .maximumSize(100)
                .expireAfterWrite(2, TimeUnit.HOURS)
                .build();
    }

    public void put(String name, Object value) {
        this.localCache.put(name, value);
    }

    public Object get(String name) {
        String cache = (String) this.localCache.getIfPresent(name);
        if (cache == null) {
            cache = RedisPoolUtil.get(name);
            if (cache == null) {
                return null;
            } else {
                this.localCache.put(name, cache);
                return cache;
            }
        } else {
            return cache;
        }
    }

    public void delete(String name) {
        this.localCache.invalidate(name);
    }
}
