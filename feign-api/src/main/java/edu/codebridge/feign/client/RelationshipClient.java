package edu.codebridge.feign.client;

import edu.codebridge.feign.entity.StudentTaskResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "relationship-service")
public interface RelationshipClient {

    @GetMapping("/relationships/pr/queryUserIdByClassId/{classId}")
    public List<Long> queryUserIdByClassId(@PathVariable Integer classId);

    @GetMapping("/relationships/pr/queryClassIdByUserId/{userId}")
    public List<Integer> queryClassIdByUserId(@PathVariable Long userId);

    @PostMapping("/relationships/pr/queryUserIdByClassIds")
    public List<Long> queryUserIdByClassIds(@RequestBody List<Integer> classIds);

    @PostMapping("/relationships/pr/queryClassIdByUserIds")
    public List<Integer> queryClassIdByUserIds(@RequestBody List<Long> userIds);




    @GetMapping("/relationships/pr/queryTaskIdByClassId/{classId}")
    public List<Integer> queryTaskIdByClassId(@PathVariable Integer classId);

    @PostMapping("/relationships/pr/queryTaskIdByClassIds")
    public List<Integer> queryTaskIdByClassIds(@RequestBody List<Integer> classIds);

    @GetMapping("/relationships/pr/queryClassIdByTaskId/{taskId}")
    public List<Integer> queryClassIdByTaskId(@PathVariable Integer taskId);

    @PostMapping("/relationships/pr/queryClassIdByTaskIds")
    public List<Integer> queryClassIdByTaskIds(@RequestBody List<Integer> taskIds);




    @GetMapping("/relationships/pr/queryUserIdByTaskId/{taskId}")
    public List<Long> queryUserIdByTaskId(@PathVariable Integer taskId);

    @PostMapping("/relationships/pr/queryUserIdByTaskIds")
    public List<Long> queryUserIdByTaskIds(@RequestBody List<Integer> taskIds);

    @GetMapping("/relationships/pr/queryTaskIdByUserId/{userId}")
    public List<Integer> queryTaskIdByUserId(@PathVariable Long userId);

    @PostMapping("/relationships/pr/queryTaskIdByUserIds")
    public List<Integer> queryTaskIdByUserIds(@RequestBody List<Long> userIds);


    @PostMapping("/relationships/pr/insertStudetTaskResource")
    public Boolean insertStudentTaskResource(@RequestBody StudentTaskResource studentTaskResource);

    @PostMapping("/relationships/pr/queryStudentTaskResourceByCondition")
    public List<StudentTaskResource> queryStudentTaskResourceByCondition(@RequestBody StudentTaskResource studentTaskResource);

    @PostMapping("/relationships/pr/updateStudentTaskResourceByCondition")
    public Boolean updateStudentTaskResourceByCondition(@RequestBody StudentTaskResource studentTaskResource);
}


