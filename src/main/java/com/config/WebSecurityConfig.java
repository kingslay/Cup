package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/webjars/**","*/*.js","*/*.css","*/*.css.map","/user/login","/user/regist","/user/phonelogin");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("Spring Boot");
        http.exceptionHandling().authenticationEntryPoint(entryPoint);
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
////                .antMatchers("*/*.js","*/*.css","*/*.css.map","/user/login","/user/regist","/user/phonelogin").permitAll()
//                .and().csrf().disable()
//                .formLogin()
//                .defaultSuccessUrl("/", true)
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/");
        http.requestMatchers().antMatchers("/**").anyRequest()
                .and().httpBasic().and().anonymous().and().csrf().disable();
//                .and().httpBasic().and().anonymous().disable().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT phone as username, 'phone' as password, 1 as enabled from user_info where phone = ? ")
                .authoritiesByUsernameQuery("SELECT DISTINCT phone as username, 'USER' as authority from user_info where phone = ?");
    }

}