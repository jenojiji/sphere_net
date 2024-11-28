package com.personal.sphere_net.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Slf4j
public class AuthenticationManagerBuilderConfigurer {

    final AuthenticationProvider authenticationProvider;
    final UserDetailsService userDetailsService;


    public AuthenticationManagerBuilderConfigurer(AuthenticationProvider authenticationProvider, UserDetailsService userDetailsService) {
        this.authenticationProvider = authenticationProvider;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureAuthenticationProvider(AuthenticationManagerBuilder auth) {
        log.info("****************inside provider config bean**************");
        auth.authenticationProvider(authenticationProvider);
    }

    @Autowired
    public void configureUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        log.info("***************inside userdetailsservice config bean*******************");
        auth.userDetailsService(userDetailsService);
    }
}
