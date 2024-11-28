package com.personal.sphere_net.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class AppConfigurations {

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("********************inside encoder bean******************");
        return new BCryptPasswordEncoder();
    }
}
