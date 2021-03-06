package com.itstep.securiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private UserSecuriryDetailsService detailService;
	
	@Autowired
	public SecurityConfig(UserSecuriryDetailsService detailService) {
		this.detailService = detailService;
	}


	//настраиваем авторизацию и доплниетльные разные параметры
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/notes/**").authenticated()
		.antMatchers("/admin_page/**").hasRole("ADMIN")
		.antMatchers("/admin_page/**").hasRole("MANAGER")
		.and()
		.formLogin().loginPage("/login")
		.and()
		.logout()
		.and()
		.rememberMe().userDetailsService(detailService)
		.and()
		.exceptionHandling().accessDeniedPage("/login?denied")
		.and()
		.csrf().disable();
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(detailService);
		provider.setPasswordEncoder(passwordEncoder());
		//как по юзер найм найти пользователя UserDatails
		auth.authenticationProvider(provider);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
