package com.shopify.shoplite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    public static final String ADMIN_ROLE = "ADMINISTRATOR";
    public static final String STAFF_ROLE = "STAFF";
    public static final String MANAGER_ROLE = "MANAGER";

    // List the mappings/methods for which no authorisation is required.
    // By default we allow all GETs and full access to the H2 console.
    private static final RequestMatcher[] NO_AUTH = { new AntPathRequestMatcher("/webjars/**", "GET"),
            new AntPathRequestMatcher("/**", "GET"), new AntPathRequestMatcher("/h2-console/**") };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // By default, all requests are authenticated except our specific list.
        http.authorizeRequests().requestMatchers(NO_AUTH).permitAll().anyRequest().hasAnyRole(ADMIN_ROLE, MANAGER_ROLE);

        // Use form login/logout for the Web.
        http.formLogin().loginPage("/sign-in").permitAll();
        http.logout().logoutUrl("/sign-out").logoutSuccessUrl("/").permitAll();

        // Use HTTP basic for the API.
        http.requestMatcher(new AntPathRequestMatcher("/api/**")).httpBasic();

        // Only use CSRF for Web requests.
        // Disable CSRF for the API and H2 console.
        http.antMatcher("/**").csrf().ignoringAntMatchers("/api/**", "/h2-console/**");

        // Disable X-Frame-Options for the H2 console.
        http.headers().frameOptions().disable();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails neko = User.withUsername("Neko").password(encoder.encode("Asakura")).roles(ADMIN_ROLE).build();
        UserDetails staff = User.withUsername("Staff").password(encoder.encode("Staff")).roles(STAFF_ROLE).build();
        UserDetails manager = User.withUsername("Manager").password(encoder.encode("Manager")).roles(MANAGER_ROLE).build();

        return new InMemoryUserDetailsManager(neko, staff, manager);
    }
}
