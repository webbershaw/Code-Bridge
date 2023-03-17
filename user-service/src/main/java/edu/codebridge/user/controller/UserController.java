package edu.codebridge.user.controller;


import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;
import edu.codebridge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users/")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/")
    public Result register(HttpServletRequest httpServletRequest, User user){
        userService.register(user);

        return null;
    }



    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/


}
