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
    public User queryById(@PathVariable Long id){
        User user = userService.queryUserById(id);
        return user;
    }

    @PostMapping("/pr")
    public List<User> queryUsersByIds(@RequestBody List<Long> ids){
        return userService.queryUsersByIds(ids);
    }




    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/

    @PostMapping("/")
    public Result register(HttpServletRequest httpServletRequest, @RequestBody User user){
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
            System.out.println(e);
            return new Result(ErrorCode.ERR,null,"发送失败，请联系管理员");
        }
        return result;
    }

    @GetMapping("/checkTel/{tel}")
    public  Result checkTel(@PathVariable String tel){
        return userService.checkTel(tel);
    }

    @GetMapping("/school")
    public  Result getAllSchools(){
        try {
            return userService.getSchools();
        } catch (Exception e) {
            System.out.println(e);
            return new Result(ErrorCode.ERR,null,"获取学校失败");
        }
    }

    @GetMapping("/get")
    public Result getLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            user.setPwd(null);
            return new Result(ErrorCode.OK,user);
        }
        return new Result(ErrorCode.ERR,false,"您未登录");
    }
    @GetMapping("/sendEmail/{to}")
    public Result sendRegEmail(HttpServletRequest request,@PathVariable String to){
        try {
            return userService.sendEmailVerifyCode(request,to);
        } catch (Exception e) {
            System.out.println(e);
            return new Result(ErrorCode.ERR,null,"发送失败，请联系管理员");
        }
    }

    @PutMapping ("/completeInfo")
    public  Result completeInfo(HttpServletRequest request,@RequestBody User user){
        try {
            return  userService.completeInfo(request, user);
        } catch (Exception e) {
            System.out.println(e);
            return new Result(ErrorCode.ERR,null,"保存失败，请联系管理员");
        }

    }
    @DeleteMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return new Result(ErrorCode.OK,null);
    }
    @GetMapping ("/tel/{tel}")
    public  Result completeInfo(@PathVariable String tel){
        try {
            return  userService.getByTel(tel);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ErrorCode.ERR,null,"电话号码为:"+tel+" 的用户不存在");
        }

    }






}
