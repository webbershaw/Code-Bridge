package edu.codebridge.user.controller;


import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;
import edu.codebridge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users/")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/pr/{id}")
    public User queryById(@PathVariable Integer id){
        User user = userService.queryUserById(id);
        return user;
    }



    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/

    @PostMapping("/")
    public Result register(HttpServletRequest httpServletRequest, User user){
        userService.register(user);

        return null;
    }



}
