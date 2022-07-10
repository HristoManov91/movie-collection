//package com.example.moviecollectionbackend.web;
//
//import com.example.moviecollectionbackend.model.dto.UserRegisterDto;
//import com.example.moviecollectionbackend.service.UserService;
//import javax.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<Boolean> registerUser(@Valid UserRegisterDto userRegisterDto){
//        boolean result = userService.registerUser(userRegisterDto);
//
//        return new ResponseEntity<>(result , result ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
//    }
//
//}
