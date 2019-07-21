package edu.tongji.comm.service;

import edu.tongji.comm.service.bo.Account;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-07-21
 */
@Component
public class AccountService {

    @Cacheable(cacheNames = {"user"}, cacheManager = "caffeineCacheManager")
    public Account getAccountById(Integer id) {
        //方法内部实现不考虑缓存逻辑，直接实现业务
        System.out.println("query account which id=" + id);
        return getFromDB(id);
    }

    @Cacheable(cacheNames = {"user"}, cacheManager = "redisCacheManager")
    public Account getAccountByIdWithRedis(Integer id) {
        //方法内部实现不考虑缓存逻辑，直接实现业务
        System.out.println("query account which id=" + id);
        return getFromDB(id);
    }

    @Caching(cacheable = {
            @Cacheable(cacheNames = {"user"}, cacheManager = "concurrentMapCacheManager"),
            @Cacheable(cacheNames = {"user"}, cacheManager = "caffeineCacheManager")
        }
    )
    public Account getAccountByIdMutilCache(Integer id) {
        System.out.println("query account which id=" + id);
        return getFromDB(id);
    }

    @CachePut(cacheNames = {"user"}, cacheManager = "redisCacheManager")
    public Account putAccountById(Integer id) {
        System.out.println("query account which id=" + id);
        return getFromDB(id);
    }

    @CacheEvict(cacheNames = {"user"}, cacheManager = "redisCacheManager")
    public void deleteAcount(Integer id) {
        System.out.println("delete account which id:=" + id);
    }

    @CacheEvict(cacheNames = {"user"}, cacheManager = "redisCacheManager", allEntries = true)
    public void deleteAllAcount() {
        System.out.println("delete all cache account");
    }

    private Account getFromDB(Integer id) {
        System.out.println("query account from db which id=" + id);
        return new Account(123456, "Jacky");
    }

}
