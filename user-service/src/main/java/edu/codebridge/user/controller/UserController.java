package edu.codebridge.user.controller;


import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;
import edu.codebridge.user.service.UserService;
import edu.codebridge.user.service.impl.UserServiceImpl;
import edu.codebridge.user.util.PrivateInfoRemoval;
import edu.codebridge.user.util.PwdEncodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @PostMapping("/pr")
    public List<User> queryUsersByIds(@RequestBody List<Long> ids){
        return userService.queryUsersByIds(ids);
    }




    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/

    @PostMapping("/")
    public Result register(HttpServletRequest httpServletRequest, @PathVariable User user){


        return userService.register(user,httpServletRequest);
    }

    @PostMapping("/loginByPwd")
    public Result loginByPwd(HttpServletRequest httpServletRequest,@RequestBody User user){
        HttpSession session = httpServletRequest.getSession();
        if (user.getTel() == null) {
            return new Result(ErrorCode.ERR,null,"账号不能为空");
        }
        if (user.getPwd() == null) {
            return new Result(ErrorCode.ERR,null,"密码不能为空");
        }
        user.setPwd(PwdEncodingUtil.encodePwd(user.getPwd()));
        User user1 = userService.loginByPwd(user);
        if (user1 == null){
            return new Result(ErrorCode.ERR, null, "账号或密码错误");
        }
        session.setAttribute("user",user1);
        user1 = PrivateInfoRemoval.removeAllPrivateInfo(user1);
        System.out.println(user1);
        return new Result(ErrorCode.OK, user1, "登陆成功");

    }

    @PostMapping("/loginByVerifyCode")
    public Result loginByVerifyCode(@RequestBody User user,HttpServletRequest request){
        return userService.loginByVerifyCode(user,request);
    }


    @GetMapping("/verifyCode/{type}/{tel}")
    public Result sendVerifyCode(@PathVariable Integer type,@PathVariable String tel,HttpServletRequest request){
        Result result = null;
        try {
            result = userService.sendVerifyCode(request, tel, type);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"发送失败，请联系管理员");
        }
        return result;
    }





}
