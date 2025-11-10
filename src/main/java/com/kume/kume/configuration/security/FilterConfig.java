package com.kume.kume.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FilterConfig {
  @Bean
  org.springframework.boot.web.servlet.FilterRegistrationBean<CspNonceFilter> cspReg(CspNonceFilter f) {
    var reg = new org.springframework.boot.web.servlet.FilterRegistrationBean<>(f);
    reg.setOrder(org.springframework.core.Ordered.HIGHEST_PRECEDENCE);
    return reg;
  }
}
