/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import wad.auth.JpaAuthenticationProvider;
import wad.domain.Employer;

/**
 *
 * @author Mikko
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/*", "/login", "/register", "/resources/**").permitAll()
                .antMatchers("/h2-console*/**").permitAll();
                //.anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/employer")
                .failureForwardUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll();
                //.invalidateHttpSession(true);
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private JpaAuthenticationProvider jpaAuthenticationProvider;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(jpaAuthenticationProvider);
        }
    }
}
