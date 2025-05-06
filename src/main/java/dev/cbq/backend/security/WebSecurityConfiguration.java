package dev.cbq.backend.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/api/**").hasRole("admin")
			.anyRequest().authenticated();
		http.formLogin(login -> login
			.loginProcessingUrl("/api/auth/sign-in")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(this::onAuthenticationSuccess)
			.failureHandler(this::onAuthenticationFailure)

		);
		http.logout(logout -> logout
			.logoutUrl("/api/auth/sign-out")
			.logoutSuccessHandler(this::onLogoutSuccess)
		);

	}

	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		log.info("WebSecurityConfiguration.onAuthenticationSuccess {}", authentication);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write("登录成功");
	}

	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		log.info("WebSecurityConfiguration.onAuthenticationFailure {}", exception.getMessage());
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write("登录失败");
	}

	public void onLogoutSuccess(HttpServletRequest request,
								HttpServletResponse response,
								Authentication authentication) throws IOException, ServletException {
		log.info("WebSecurityConfiguration.onLogoutSuccess {}", authentication);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write("退出成功");
	}

}
