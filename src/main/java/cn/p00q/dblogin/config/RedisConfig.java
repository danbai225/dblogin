package cn.p00q.dblogin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author danbai
 * @date 2020-02-04 16:59
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 配置自定义redisTemplate
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> getRedisTemplate(
            final RedisConnectionFactory connectionFactory
    ) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        // 配置默认的序列化器
        redisTemplate.setDefaultSerializer(getGenericJackson2JsonRedisSerializer());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 设置 Key 的默认序列化机制
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(final RedisConnectionFactory redisConnectionFactory) {
        RedisCacheManager.RedisCacheManagerBuilder builder
                = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory);

        return builder.build();
    }

    @Bean(name = "springSessionDefaultRedisSerializer")
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

}
