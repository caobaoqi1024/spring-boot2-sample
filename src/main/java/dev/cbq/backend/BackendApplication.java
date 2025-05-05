package dev.cbq.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        log.info("BackendApplication.onApplicationReady");
        ConfigurableApplicationContext context = event.getApplicationContext();
        log.info(String.valueOf(context.getBean("redisTemplate",RedisTemplate.class)));
        log.info(String.valueOf(context.getBean("stringRedisTemplate",RedisTemplate.class)));
        log.info(String.valueOf(context.getBean(DataSource.class)));
    }
}
