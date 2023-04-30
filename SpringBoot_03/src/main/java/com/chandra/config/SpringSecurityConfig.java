package com.chandra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	// 1.Adding the roles
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	                .withUser("chandra")
	                .password("chandra@123")
	                .authorities("admin_role")
	                .and()
	                .withUser("adi")
	                .password("adi@123")
	                .authorities("student")
	                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
	    }
	
	// 2.Configuring the api
    //   according to the roles.
	 protected void configure(HttpSecurity http) throws Exception {
	        http.
	                httpBasic()
	                .and()
	                .authorizeRequests()
	                .antMatchers("/api/welcome").authenticated()
	                .and()
	                .formLogin()
	                .and()
	                .httpBasic();
	    }
	
	// 3.Function to encode the password
    //   assign to the particular roles.
	/*
	 * @Bean public PasswordEncoder getPasswordEncoder(){ return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
}
