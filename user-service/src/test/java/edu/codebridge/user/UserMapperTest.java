package edu.codebridge.user;

import edu.codebridge.feign.entity.User;
import edu.codebridge.user.mapper.UserMapper;
import edu.codebridge.user.util.PwdEncodingUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.locks.Condition;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    @Test
    public void conditionalQueryTest(){
        User user = new User();
        user.setEmail("a@a.com");
        User userRes = userMapper.queryByCondition(user);
        System.out.println(userRes);

    }
    @Test
    public void conditionalUpdateTest(){
        User user = new User();
        user.setId(1L);
        user.setEmail("b@b.com");
        user.setTel("120");
        user.setName("dashabi");
        Integer integer = userMapper.updateByCondition(user);
        System.out.println(integer);
    }

    @Test
    public void pwdtest(){
        String s = PwdEncodingUtil.encodePwd("123");
        System.out.println(s);
    }
}
