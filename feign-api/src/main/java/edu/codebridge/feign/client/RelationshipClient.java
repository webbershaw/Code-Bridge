package edu.codebridge.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "relationship-service")
public interface RelationshipClient {

    @GetMapping("/pr/queryUserIdByClassId/{classId}")
    public List<Long> queryUserIdByClassId(Integer classId);

    @GetMapping("/pr/queryClassIdByUserId/{userId}")
    public List<Integer> queryClassIdByUserId(Long userId);

    @GetMapping("/pr/queryUserIdByClassIds")
    public List<Long> queryUserIdByClassIds(@RequestBody List<Integer> classIds);

    @GetMapping("/pr/queryClassIdByUserIds")
    public List<Integer> queryClassIdByUserIds(@RequestBody List<Long> userIds);




    @GetMapping("/pr/queryTaskIdByClassId/{classId}")
    public List<Integer> queryTaskIdByClassId(@PathVariable Integer classId);

    @GetMapping("/pr/queryTaskIdByClassIds")
    public List<Integer> queryTaskIdByClassIds(@RequestBody List<Integer> classIds);

    @GetMapping("/pr/queryClassIdByTaskId/{taskId}")
    public List<Integer> queryClassIdByTaskId(@PathVariable Integer taskId);

    @GetMapping("/pr/queryClassIdByTaskIds")
    public List<Integer> queryClassIdByTaskIds(@RequestBody List<Integer> taskIds);




    @GetMapping("/pr/queryUserIdByTaskId/{taskId}")
    public List<Long> queryUserIdByTaskId(@PathVariable Integer taskId);

    @GetMapping("/pr/queryUserIdByTaskIds")
    public List<Long> queryUserIdByTaskIds(@RequestBody List<Integer> taskIds);

    @GetMapping("/pr/queryTaskIdByUserId/{userId}")
    public List<Integer> queryTaskIdByUserId(@PathVariable Long userId);

    @GetMapping("/pr/queryTaskIdByUserIds")
    public List<Integer> queryTaskIdByUserIds(@RequestBody List<Long> userIds);
}
