package io.github.ankushs92.benchmark.vertxsample.config

import io.vertx.core.Vertx
import io.vertx.redis.RedisClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by ankushsharma on 26/02/18.
 */
@Configuration
class RedisConfig {

    @Autowired
    private Vertx vertx

    @Bean("redis")
    RedisClient redisClient() {
        RedisClient.create(vertx)
    }

}
