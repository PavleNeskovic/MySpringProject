package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	  @Autowired
	    private UserDetailsService userDetailsService;
	  
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	  /*http.formLogin()
        .loginPage("/login")
        .usernameParameter("email")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .permitAll();*/
		  
	http
		.authorizeRequests()
		.antMatchers("/contacts").hasRole("ADMIN")
		.antMatchers("/create").permitAll()
//		.antMatchers("/contact/*").authenticated()
		.and().formLogin()
		.and().csrf().disable();
		  
// http.authorizeRequests()
//	.antMatchers("/users").hasRole("ADMIN") // #4
//	        .antMatchers("/messages").hasRole("ADMIN") // #6
//	        .antMatchers("/messages/send").hasRole("USER") 
//	        .anyRequest().authenticated() // 7
//	        .and()
//	    .formLogin() 
//	    .and().httpBasic().and().
//	csrf().disable();

	  }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
//            .inMemoryAuthentication()
//                .withUser("a").password("a").roles("USER");
		        .userDetailsService(userDetailsService)
		        .passwordEncoder(new BCryptPasswordEncoder());
    }
}