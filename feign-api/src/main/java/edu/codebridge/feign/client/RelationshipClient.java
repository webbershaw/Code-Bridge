package edu.codebridge.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "relationship-service")
public interface RelationshipClient {

    @GetMapping("/pr/queryUserIdByClassId/{classId}")
    public List<Long> queryUserIdByClassId(Integer classId);
}
