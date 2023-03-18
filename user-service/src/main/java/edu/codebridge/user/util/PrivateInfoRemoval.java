package edu.codebridge.user.util;

import edu.codebridge.feign.entity.User;

public class PrivateInfoRemoval {
    public static User removePwd(User user){
        user.setPwd(null);
        return user;
    }
    public static User removeAllPrivateInfo(User user){
        user.setTel(null);
        user.setEmail(null);
        user.setPwd(null);
        user.setCertified(null);
        user.setDeleted(null);
        user.setPwd(null);
        user.setName(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        return user;
    }
}
