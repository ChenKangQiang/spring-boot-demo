package edu.tongji.comm;

import com.alibaba.fastjson.JSON;
import edu.tongji.comm.service.AccountService;
import edu.tongji.comm.service.bo.Account;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-07-21
 */
public class SpringCacheTest extends BaseUnitTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CaffeineCacheManager caffeineCacheManager;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private SimpleCacheManager simpleCacheManager;

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    @Autowired
    private ConcurrentMapCacheManager concurrentMapCacheManager;

    @Test
    public void testLocalCache() {
        try {
            Account account = accountService.getAccountById(123456);
            System.out.println(JSON.toJSONString(account));

            Account cacheAccount = accountService.getAccountById(123456);
            System.out.println(JSON.toJSONString(cacheAccount));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testRedisCache() {
        try {
            Account account = accountService.getAccountByIdWithRedis(123456);
            System.out.println(JSON.toJSONString(account));

            Account cacheAccount = accountService.getAccountByIdWithRedis(123456);
            System.out.println(JSON.toJSONString(cacheAccount));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testMutilCache() {
        try {
            Account account1 = accountService.getAccountByIdMutilCache(123456);
            System.out.println(JSON.toJSONString(account1));

            System.out.println("缓存Caffeine中的数据=" + caffeineCacheManager.getCache("user").get(123456));
            System.out.println("缓存ConcurrentMap中的数据=" + concurrentMapCacheManager.getCache("user").get(123456));

            System.out.println("删除缓存Caffeine中的数据");
            caffeineCacheManager.getCache("user").evict(123456);

            Account account2 = accountService.getAccountByIdMutilCache(123456);
            System.out.println(JSON.toJSONString(account2));

            System.out.println("缓存Caffeine中的数据=" + caffeineCacheManager.getCache("user").get(123456));
            System.out.println("缓存ConcurrentMap中的数据=" + concurrentMapCacheManager.getCache("user").get(123456));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
