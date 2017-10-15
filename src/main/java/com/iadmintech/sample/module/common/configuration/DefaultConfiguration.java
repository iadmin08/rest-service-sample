package com.iadmintech.sample.module.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class DefaultConfiguration {

    @Configuration
    @Profile("default")
    @PropertySource("spring-profile-not-set")
    protected static class ProfileNotSet{//This causes the application startup to fail. This is intentional.

    }
}
