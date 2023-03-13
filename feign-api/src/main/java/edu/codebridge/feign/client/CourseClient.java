package edu.codebridge.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "course-service")
public interface CourseClient {

}
