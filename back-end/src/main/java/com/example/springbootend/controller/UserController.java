package com.example.springbootend.controller;


import com.example.springbootend.entity.User;
import com.example.springbootend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public Map getMyInfo(@RequestAttribute int uid){
        return Map.of("user",userService.getUserById(uid));
    }

    @PostMapping("/set/user/authority")
     public Map updateAuthority(@RequestBody User user){
        int newAuthority = user.getAuthority()==1? 2:1;
        User u = userService.UpdateUserAuthority(newAuthority,user.getId());
        return Map.of("user",u);
     }

     @GetMapping("/get/users")
    public Map getUsers(){
        List<User> list = userService.listNonAdminUsers();
        return Map.of("users",list);
    }

    @GetMapping("/get/allusers")
    public Map getAllUsers(){
        List<User> list = userService.listAllUsers();
        return Map.of("users",list);
    }

    @PostMapping("/set/update/user")
    public Map postUser(@RequestBody User user){
        User u = userService.updateUser(user);
        return Map.of("user",u);
    }

}
