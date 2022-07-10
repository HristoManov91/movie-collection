//package com.example.moviecollectionbackend.config;
//
//import com.example.moviecollectionbackend.repository.UserRepository;
//import com.example.moviecollectionbackend.service.impl.AppUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true , prePostEnabled = true)
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new Pbkdf2PasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//            .csrf()
//            .disable()
//            .authorizeRequests()
////            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//            .antMatchers("/", "users/login", "users/register").permitAll()
////            .antMatchers("").hasRole(UserRole.USER.name())
////            .antMatchers("/**").authenticated()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/users/login")
////            .usernameParameter("email") if login with email and login field name is 'email'
//            .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
//            .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
//            .defaultSuccessUrl("/movies")
////            .failureForwardUrl("/users/login-error")
//            .and()
//            .logout()
//            .logoutUrl("/users/logout")
//            .invalidateHttpSession(true)
//            .deleteCookies("JSESSIONID");
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return new AppUserDetailsService(userRepository);
//    }
//}
