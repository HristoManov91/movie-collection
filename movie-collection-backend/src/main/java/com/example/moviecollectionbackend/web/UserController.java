package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.config.jwt.JwtTokenUtil;
import com.example.moviecollectionbackend.model.dto.AuthResponse;
import com.example.moviecollectionbackend.model.dto.UserLoginDto;
import com.example.moviecollectionbackend.model.dto.UserRegisterDto;
import com.example.moviecollectionbackend.model.user.AppUserDetails;
import com.example.moviecollectionbackend.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8080/")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager,
        JwtTokenUtil jwtUtil) {
        this.userService = userService;
        this.authManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto){
        boolean result = userService.registerUser(userRegisterDto);

        return new ResponseEntity<>(result , result ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto){//ToDo validate
        try {
            Authentication authenticate = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));

            AppUserDetails user = (AppUserDetails) authenticate.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getUsername() , accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException Ex){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
    }

    @PostMapping("/logout")
    public Boolean logout(){
        return true;
    }

}
