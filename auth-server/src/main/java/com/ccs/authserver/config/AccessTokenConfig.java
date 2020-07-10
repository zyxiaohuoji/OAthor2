package com.ccs.authserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class AccessTokenConfig {

//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;

    @Bean
    TokenStore tokenStore() {
//        return new RedisTokenStore(redisConnectionFactory);
        return new InMemoryTokenStore();
    }

}
