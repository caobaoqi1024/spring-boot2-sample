package dev.cbq.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTest {

	@Autowired
	ApplicationContext context;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		assertNotNull(context);
		assertNotNull(context.getBean("redisTemplate", RedisTemplate.class));
		assertNotNull(context.getBean("stringRedisTemplate", RedisTemplate.class));
		assertNotNull(context.getBean(DataSource.class));
	}

	@Test
	void password() {
		System.out.println(passwordEncoder.encode("user-pwd"));
		System.out.println(passwordEncoder.encode("admin-pwd"));
	}

}
