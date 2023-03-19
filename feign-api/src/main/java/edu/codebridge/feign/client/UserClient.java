package edu.codebridge.feign.client;

import edu.codebridge.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service")
public interface UserClient {
    @GetMapping("/users/pr/{id}")
    User queryById(@PathVariable("id") Long id);

}
