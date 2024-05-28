package com.company.firstApplication.firstApplicaton.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@Slf4j
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomerClientDetailService customerClientDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerClientDetailService);
        //Only for test - simple plain text
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        /* 11 times BCrypting
        provider.setPasswordEncoder(new BCryptPasswordEncoder(11));
         */
        log.info("Authentication Provider={}",provider);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        log.info("Configure:{}", authenticationManagerBuilder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        super.configure(httpSecurity);
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll();
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//        httpSecurity.headers().frameOptions().disable();
    }

    /*
    ** - In memory authentication
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder().username("fpmoles").password("password").roles("USER", "ADMIN").build());
        users.add(User.withDefaultPasswordEncoder().username("dog").password("ihatecat").roles("USER").build());
        return new InMemoryUserDetailsManager(users);
    }
     */


}
