package edu.codebridge.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "relationship-service")
public interface RelationshipClient {

}
