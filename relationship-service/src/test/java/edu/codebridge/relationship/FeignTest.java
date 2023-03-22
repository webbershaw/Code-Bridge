package edu.codebridge.relationship;

import edu.codebridge.feign.client.UserClient;
import edu.codebridge.feign.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@EnableFeignClients(basePackages = "edu.codebridge.feign.client")
public class FeignTest {
    @Autowired
    UserClient userClient;
    @Test
    public void getUerTest(){
        User user = userClient.queryById(1L);
        System.out.println(user);
    }

    @Test
    public void  getUsersTest(){
        List<Long> ids =new ArrayList<>();

        ids.add(1L);
        ids.add(2L);

        List<User> users = userClient.queryUsersByIds(ids);
        System.out.println(users);




    }
}
