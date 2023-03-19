package edu.codebridge.relationship;

import edu.codebridge.feign.client.UserClient;
import edu.codebridge.feign.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

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
}
