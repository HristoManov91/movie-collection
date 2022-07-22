package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.service.PlatformService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080/")
@RequestMapping("/platforms")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping("/all")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<List<String>> findAllPlatformsNames(){
        return new ResponseEntity<>(platformService.findAllPlatformsNames() , HttpStatus.OK);
    }
}
