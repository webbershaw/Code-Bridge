package edu.codebridge.user.service.impl;

import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;
import edu.codebridge.user.mapper.UserMapper;
import edu.codebridge.user.service.UserService;
import edu.codebridge.user.util.PwdEncodingUtil;
import edu.codebridge.user.util.SenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    SenderUtil senderUtil;

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
    public Result register(User user, HttpServletRequest request) {

        /*用户信息的判断逻辑*/
        //用于判断电话号码是否符合中国大陆规则的正则
        String regex2 = "^((\\+86)|(86))?((13[0-9])|(14[5|7])|(15[0-3|5-9])|(17[0-1|3|5-8])|(18[0-9])|(19[0-9]))\\d{8}$";
        //用于判断密码是否为8位数字字母组合的正则
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        String tel = user.getTel();
        String password = user.getPwd();

        if(userMapper.queryByCondition(user)!=null){//判断电话号码是否重复
            return new Result(ErrorCode.ERR,false,"该电话号码已经注册，换一个吧！");
        } else if(!tel.matches(regex2)){//判断电话号码格式

            return new Result(ErrorCode.ERR,false,"电话号码格式有误");
        }

        if(!password.matches(regex)){//判断密码强度
            return new Result(ErrorCode.ERR,false,"就这？密码不够强啊");
        }else if(user.getIdentity()==1||user.getIdentity()==2){
            return new Result(ErrorCode.ERR,false,"畜牲，别想绕过我的技术");//防止非法注册数据
        }




        /*验证码的判断逻辑*/
        HttpSession session = request.getSession();//获取Session
        Object verifyCodeTimeObj = session.getAttribute("verifyCodeTime");//获取发送验证码时存入Session中的验证码发送时间
        long verifyCodeTime =0;
        if(verifyCodeTimeObj!=null){
            verifyCodeTime=(long)verifyCodeTimeObj;
        }
        //获取发送验证码时存入Session中的验证码
        Object verifyCodeObj = session.getAttribute("verifyCode");
        int verifyCode =0;
        if(verifyCodeObj!=null){
            verifyCode=(int)verifyCodeObj;
        }else {
            //验证码不存在则返回前端错误信息
            return new Result(ErrorCode.ERR,false,"请先完成手机号码验证");
        }
        //获取发送验证码的时间和当前时间的间隔
        long gapTime=(System.currentTimeMillis()-verifyCodeTime)/1000;
        if(verifyCode==0){
            return new Result(ErrorCode.ERR,false,"手机号码验证失败");
        } else if (user.getVerifyCode()!=verifyCode) {//验证码错误
            return new Result(ErrorCode.ERR,false,"验证码错误");
        } else if (gapTime>180) {//验证码发送时间与当前间隔大于三分钟
            System.out.println(gapTime);
            return new Result(ErrorCode.ERR,false,"验证码已过期，请重试");
        }

        /*持久化保存用户数据*/
        try {
            //利用工具类采用SHA-256对密码进行加密而非明文存储
            user.setPwd(PwdEncodingUtil.encodePwd(user.getPwd()));
            //将数据存入数据库
            userMapper.insertUser(user);
        }catch (Exception e){
            System.out.println(e);
            return new Result(ErrorCode.ERR,false,"注册失败，请联系管理员");
        }

        /*用户状态管理*/
        //用户需要存到session中并与前端交互，故不能在交互中向前端暴露密码
        user.setPwd(null);
        //设置默认的用户头像
        user.setAvatarUrl("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");
        //将用户存入session，保存登录状态，无需重复登录
        session.setAttribute("user",user);
        //将用户登录成功代码及用户信息返回给前端
        return new Result(ErrorCode.OK,user,"注册成功，3秒后跳转至资料完善页面");

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

    @Override
    public Result sendVerifyCode(HttpServletRequest request,String tel,Integer type) {

        //根据场景为session设置不同的字段
        String sessionName = null;
        String sessionTimeName = null;
        if(type==SenderUtil.LOGIN_TEXT_MESSAGE_CODE){
            sessionName="loginVerifyCode";
            sessionTimeName="loginVerifyCodeTime";
        }else if(type==SenderUtil.REGISTER_TEXT_MESSAGE_CODE){
            sessionName="verifyCode";
            sessionTimeName="verifyCodeTime";
            User user = new User();
            user.setTel(tel);
            userMapper.queryByCondition(user);
            if(userMapper.queryByCondition(user)!=null){
                return new Result(ErrorCode.ERR,null,"该号码已被注册");
            }
        } else if (type==SenderUtil.FORGET_PWD_MESSAGE_CODE) {
            sessionName="findVerifyCode";
            sessionTimeName="findVerifyCodeTime";
        }





        HttpSession session = request.getSession();
        if(session.getAttribute(sessionName)!=null){
            Object verifyCodeTimeObj = session.getAttribute(sessionTimeName);
            long verifyCodeTime =0;
            if(verifyCodeTimeObj!=null){
                verifyCodeTime = (long)verifyCodeTimeObj;
            }else {
                return new Result(ErrorCode.ERR,null,"上次发送时间未记录，请联系管理员");
            }
            long gapTime=(System.currentTimeMillis()-verifyCodeTime)/1000;
            if(gapTime<60){
                return new Result(ErrorCode.ERR,null,"验证码已发送,请耐心等待，或在"+(60-gapTime)+"秒后重试");
            }
        }
        int verifyCode = (int) (Math.random()*1000000+1);
        boolean isSent = senderUtil.sendTextMessage(tel, verifyCode, type);
        if(!isSent){
            return new Result(ErrorCode.ERR,null,"发送失败，请联系管理员");
        }
        if(verifyCode!=0) {
            session.setAttribute(sessionName, verifyCode);
        }else {
            return new Result(ErrorCode.ERR,null,"发送失败，请联系管理员");
        }
        session.setAttribute(sessionTimeName,System.currentTimeMillis());

        return new Result(ErrorCode.OK,null,"发送成功，请注意查收，180秒内有效");

    }
}
