package com.worldcup.adm.config.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AdminTotpAuthenticationConfigurer extends UserDetailsAwareConfigurer<AuthenticationManagerBuilder, UserDetailsService> {
    private AdminTotpAuthenticationProvider provider = new AdminTotpAuthenticationProvider();

    private final AdminUserDetailsService userDetailsService;

    protected AdminTotpAuthenticationConfigurer(UserDetailsService userDetailsService) {
        this.userDetailsService = (AdminUserDetailsService) userDetailsService;
        this.provider.setUserDetailsService(userDetailsService);
    }

    /*public AdminTotpAuthenticationConfigurer passwordEncoder(PasswordEncoder passwordEncoder) {
        this.provider.setPasswordEncoder(passwordEncoder);
        return this;
    }*/

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        this.provider = postProcess(this.provider);
        builder.authenticationProvider(this.provider);
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }
}
