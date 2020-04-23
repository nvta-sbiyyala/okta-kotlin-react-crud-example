package com.okta.kotlin

import com.okta.spring.boot.oauth.Okta
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt();

        // Send a 401 message to the browser (w/o this, you'll see a blank page)
        Okta.configureResourceServer401ResponseBody(http);
    }
}
