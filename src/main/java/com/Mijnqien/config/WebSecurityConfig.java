//package com.Mijnqien.config;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//public class WebSecurityConfig {
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    http.csrf().disable()
//	        .authorizeRequests()
//	            .antMatchers("/", "/register", "/api/user/add").permitAll()
//	            .anyRequest().authenticated()
//	        .and()
//	        .formLogin().loginPage("/login").permitAll()
//	        .successHandler(myAuthenticationSuccessHandler())
//	        .and()
//	        .httpBasic()
//	        .and()
//	        .logout()
//	            .logoutUrl("/logout").permitAll()
//	            .clearAuthentication(true)
//	            .logoutSuccessUrl("/")
//	            .invalidateHttpSession(true)
//	            .deleteCookies("JSESSIONID");
//	}
//
//}


