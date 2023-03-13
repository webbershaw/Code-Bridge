package edu.codebridge.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "task-service")
public interface TaskClient {

}
