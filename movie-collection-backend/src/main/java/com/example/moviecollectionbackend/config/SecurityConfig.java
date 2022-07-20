package com.example.moviecollectionbackend.config;

import com.example.moviecollectionbackend.config.jwt.JwtTokenFilter;
import com.example.moviecollectionbackend.repository.UserRepository;
import com.example.moviecollectionbackend.service.impl.AppUserDetailsService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().disable()
            .authorizeRequests()
                .antMatchers("/", "/users/**" , "/platforms/all" , "/genres/all").permitAll()
            .anyRequest()
                .authenticated()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(
                    (request, response, ex) -> {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                    })
            .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            .and()
            .addFilterBefore(jwtTokenFilter , UsernamePasswordAuthenticationFilter.class);

        return http.build();
        //        http
//            .cors().and().csrf().disable()
//            // define which requests are allowed and which not
//            .authorizeRequests()
//            // everyone can download static resources (css, js, images)
//            //.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//            // everyone can login and register
//            .antMatchers("/", "/users/**").permitAll()
//            // all other pages are available for logger in users
//            .anyRequest()
//                .authenticated()
//            .and()
//            // configuration of form login
//                .formLogin()
//            // the custom login form
//                .loginPage("/users/login")
//            // the name of the username form field
//                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
//            // the name of the password form field
//                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
//            // where to go in case that the login is successful
//                .defaultSuccessUrl("/")
//            // where to go in case that the login failed
//                .failureForwardUrl("/")
//            .and()
//            // configure logut
//                .logout()
//            // which is the logout url, must be POST request
//                .logoutUrl("/users/logout")
//            // on logout go to the home page
//                .logoutSuccessUrl("/")
//            // invalidate the session and delete the cookies
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID");

//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }

}
