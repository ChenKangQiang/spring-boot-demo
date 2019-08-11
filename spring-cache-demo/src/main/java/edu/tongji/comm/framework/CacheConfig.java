package edu.tongji.comm.framework;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.assertj.core.util.Lists;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019-07-21
 */
@Configuration
public class CacheConfig {

    /**
     * RedisCacheManager
     * @param connectionFactory
     * @return
     */
    @Bean("redisCacheManager")
    RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        //user信息缓存配置
        RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .disableCachingNullValues()
                .prefixKeysWith("user");

        //product信息缓存配置
        RedisCacheConfiguration productCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues()
                .prefixKeysWith("product");

        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("user", userCacheConfiguration);
        redisCacheConfigurationMap.put("product", productCacheConfiguration);
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);


        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        //ClassLoader loader = this.getClass().getClassLoader();
        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);
        return redisCacheManager;
    }

    /**
     * CaffeineCacheManager
     * @return
     */
    @Bean("caffeineCacheManager")
    CaffeineCacheManager caffeineCacheManager() {
        @NonNull Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(50, TimeUnit.SECONDS);
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        //是否允许空值
        caffeineCacheManager.setAllowNullValues(true);
        //传入Caffeine Builder
        caffeineCacheManager.setCaffeine(caffeine);

        caffeineCacheManager.setCacheLoader(new CacheLoader<Object, Object>() {
            @Nullable
            @Override
            public Object load(@NonNull Object key) throws Exception {
                return null;
            }
        });
        //根据名字可以创建多个cache，但是多个cache使用相同的策略
        List<String> cacheNames = Lists.newArrayList("user", "product");

        caffeineCacheManager.setCacheNames(cacheNames);
        return caffeineCacheManager;
    }

    /**
     * SimpleCacheManager
     * @return
     */
    @Bean("simpleCacheManager")
    @Primary
    SimpleCacheManager simpleCacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List<Cache> caches = Lists.newArrayList();

        CaffeineCache caffeineCache = new CaffeineCache("user", Caffeine.newBuilder().initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(50, TimeUnit.SECONDS)
                .build()
        );
        caches.add(caffeineCache);

        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

    /**
     * EhCacheManagerFactoryBean，用于生成EhCacheCacheManager
     * @return
     */
    @Bean
    EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        // 设置完属性后，cacheManagerFactoryBean会执行afterProertiesSet()方法，
        // 所以不能在这里直接执行cacheManagerFactoryBean.getObject(),直接执行的话，因为在afterPropertiesSet()方法之前执行，所以：getObject()会得到null值
        return cacheManagerFactoryBean;
    }

    /**
     * EhCacheCacheManager
     * @return
     */
    @Bean("ehCacheCacheManager")
    EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
        return ehCacheCacheManager;
    }

    /**
     * ConcurrentMapCacheManager
     * @return
     */
    @Bean("concurrentMapCacheManager")
    ConcurrentMapCacheManager concurrentMapCacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        concurrentMapCacheManager.setAllowNullValues(true);
        concurrentMapCacheManager.setCacheNames(Lists.newArrayList("user", "product"));
        concurrentMapCacheManager.setStoreByValue(false);
        return concurrentMapCacheManager;
    }


}
