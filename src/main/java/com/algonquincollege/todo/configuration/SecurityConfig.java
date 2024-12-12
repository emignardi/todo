package com.algonquincollege.todo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The Class SecurityConfig is the main configuration class used to set up Spring Security for the application.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    /**
     * Handles authorization based on specific URL paths and also specifies login related information such as the designated login path and the path to return to after successful login.
     * @param http
     *              the HTTPSecurity object used to build the security configuration
     * @return the SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/registration", "/register", "/css/register.css", "/css/style.css", "js/utils.js", "js/userManager.js", "/css/login.css").permitAll();
                    request.requestMatchers("/","/create-task").hasAnyRole("USER","ADMIN");
                    request.anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/login");
                    form.permitAll();
                    form.defaultSuccessUrl("/");
                })
                .build();
    }

//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, user2);
//    }

    /**
     * The method providing a UserDetailsService to Spring Security using the CustomUserDetailsService we created/provided
     * @return the UserDetailsService for handling locating users and from which creates a UserDetails object
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    /**
     * The AuthenticationProvider (specifically the DoaAuthenticationProvider) which is configured using the CustomUserDetailsService and the BCrypt password encoder.
     * @return the AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * The PasswordEncoder for encoded the passwords of a User for protection.
     * @return the BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
