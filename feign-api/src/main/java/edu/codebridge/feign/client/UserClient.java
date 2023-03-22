package edu.codebridge.feign.client;

import edu.codebridge.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "user-service")
public interface UserClient {
    @GetMapping("/users/pr/{id}")
    User queryById(@PathVariable("id") Long id);

    @PostMapping("/users/pr")
    public List<User> queryUsersByIds(@RequestBody List<Long> ids);

}
