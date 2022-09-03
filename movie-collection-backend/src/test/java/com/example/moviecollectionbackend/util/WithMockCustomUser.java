//package com.example.moviecollectionbackend.util;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.util.List;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.test.context.support.WithSecurityContext;
//
//@Retention(RetentionPolicy.RUNTIME)
//@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
//public @interface WithMockCustomUser {
//
//    String username() default "hristo";
//
//    Long id() default 1L;
//
//    String password() default "topsecret";
//
//    List<GrantedAuthority> authorities() default List.of(new Granted("ROLE_USER"));
//}
