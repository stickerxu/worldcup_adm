package com.worldcup.adm.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdmSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new  AdminUserDetailsService();
    }

    @Bean
    public AdminTotpAuthenticationConfigurer totpAuthenticationConfigurer() {
        return new AdminTotpAuthenticationConfigurer(userDetailsService());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
            .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .authenticationDetailsSource(AdminTotpAuthenticationDetails::new)
                .loginPage("/login")
                .loginProcessingUrl("/loginSub")
                .defaultSuccessUrl("/")
                .permitAll()
            .and()
                .logout().logoutUrl("/logout")
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/webjars/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.apply(totpAuthenticationConfigurer());
    }
}
