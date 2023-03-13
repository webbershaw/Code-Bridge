package edu.codebridge.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user-service")
public interface UserClinet {

}
