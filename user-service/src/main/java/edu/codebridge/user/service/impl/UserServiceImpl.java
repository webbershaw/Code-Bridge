package edu.codebridge.user.service.impl;

import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;
import edu.codebridge.user.mapper.UserMapper;
import edu.codebridge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Integer> getUsersByUserIds(List<Integer> userIds) {
        return null;
    }

    @Override
    public User getUserByCondition(User user) {
        return null;
    }

    @Override
    public List<User> getUsersByCondition(User user) {
        return null;
    }

    @Override
    public User queryUserById(Integer id) {
        return userMapper.queryById(id);
    }

    @Override
    public Result register(User user) {
        /*用户信息的判断逻辑*/
        //用于判断电话号码是否符合中国大陆规则的正则
        String regex2 = "^((\\+86)|(86))?((13[0-9])|(14[5|7])|(15[0-3|5-9])|(17[0-1|3|5-8])|(18[0-9])|(19[0-9]))\\d{8}$";
        //用于判断密码是否为8位数字字母组合的正则
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        String tel = user.getTel();
        String password = user.getPwd();

//        if(!userMapper.queryByTelephone(tel)){//判断电话号码是否重复
//            return new Result(ErrorCode.ERR,false,"该电话号码已经注册，换一个吧！");
//        } else if(!tel.matches(regex2)){//判断电话号码格式
//
//            return new Result(ErrorCode.ERR,false,"电话号码格式有误");
//        }
//        if(!password.matches(regex)){//判断密码强度
//            return new Result(ErrorCode.ERR,false,"就这？密码不够强啊");
//        }

        return null;
    }

    @Override
    public User loginByPwd(User user) {
        User userRes = userMapper.queryByCondition(user);
        return userRes;
    }

    @Override
    public Result loginByVerifyCode(User user) {
        return null;
    }

    @Override
    public Result attachToSchool(User user) {
        return null;
    }
}
