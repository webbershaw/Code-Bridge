package edu.codebridge.course.common;

import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.code.IdentityCode;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;

import javax.servlet.http.HttpServletRequest;

public   class Check {
    public static Result checkUser(HttpServletRequest request){
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;
        return new Result(ErrorCode.OK,user,"用户登录了");
//        if(user.getIdentity()!= IdentityCode.TEACHER){
//            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
//        }

    }

}
