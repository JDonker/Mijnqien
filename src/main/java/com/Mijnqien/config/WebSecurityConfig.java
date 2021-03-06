package com.Mijnqien.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Properties;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		final Properties users = new Properties();
		users.put("developer", encoder().encode("developer") + ",ROLE_DEVELOPER,enabled");
		users.put("trainee", encoder().encode("trainee") + ",ROLE_TRAINEE,enabled");
		users.put("admin", encoder().encode("admin") + ",ROLE_ADMIN,enabled");
		users.put("klant", encoder().encode("klant") + ",ROLE_KLANT,enabled");
		
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(inMemoryUserDetailsManager());
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(inMemoryUserDetailsManager());
	}
	
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new UrlAuthenticationSuccessHandler();
    }
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/register", "/api/user/add", "/logo.svg" , "/mijnQien.css" , "/achtergrond.jpg").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .loginPage("/index.html")
            .loginProcessingUrl("/login")
            .successHandler(myAuthenticationSuccessHandler())
            .and()
            .httpBasic()
            .and()
            .logout()
                .logoutUrl("/logout").permitAll()
                .clearAuthentication(true)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

}
