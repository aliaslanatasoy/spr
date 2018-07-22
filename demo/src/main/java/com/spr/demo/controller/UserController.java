package com.spr.demo.controller;

import com.spr.demo.entity.ApplicationUser;
import com.spr.demo.repository.ApplicationUserRepository;
import com.spr.demo.service.UserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailService userDetailService;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation(value = "SignUp user", response = ApplicationUser.class)
    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    @ApiOperation(value = "Update user", response = ApplicationUser.class)
    @PostMapping("/update")
    public ResponseEntity updateUser(@RequestBody ApplicationUser user){
        return ResponseEntity.ok().body(userDetailService.updateUser(user));
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){
        return ResponseEntity.ok().body("User deleted");
    }

    @ApiOperation(value = "Add user",response = ApplicationUser.class)
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody ApplicationUser user){
        return ResponseEntity.ok().body(userDetailService.addUser(user));
    }

    @ApiOperation(value = "Retrieve user by userId",response = ApplicationUser.class)
    @GetMapping("/retrieve/{userId}")
    public ResponseEntity retrieveUserByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(userDetailService.getUserByUserId(userId));
    }
}