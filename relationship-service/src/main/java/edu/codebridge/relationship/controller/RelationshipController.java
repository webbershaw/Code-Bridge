package edu.codebridge.relationship.controller;

import edu.codebridge.feign.entity.StudentClass;
import edu.codebridge.relationship.mapper.RelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relationships")
public class RelationshipController {

    @Autowired
    RelationshipMapper relationshipMapper;


    /*----------------------------------------------------*/
    @GetMapping("/pr/queryUserIdByClassId/{classId}")
    public List<Long> queryUserIdByClassId(@PathVariable Integer classId){
        return relationshipMapper.queryUserIdByClassId(classId);
    }

    @GetMapping("/pr/queryClassIdByUserId/{userId}")
    public List<Integer> queryClassIdByUserId(@PathVariable Long userId){
        return relationshipMapper.queryClassIdByUserId(userId);
    }

    @GetMapping("/pr/queryUserIdByClassIds")
    public List<Long> queryUserIdByClassIds(@RequestBody List<Integer> classIds){
        return relationshipMapper.queryUserIdByClassIds(classIds);
    }

    @GetMapping("/pr/queryClassIdByUserIds")
    public List<Integer> queryClassIdByUserIds(@RequestBody List<Long> userIds){
        return relationshipMapper.queryClassIdByUserIds(userIds);
    }



    /*----------------------------------------------*/
    @GetMapping("/pr/queryTaskIdByClassId/{classId}")
    public List<Integer> queryTaskIdByClassId(@PathVariable Integer classId){
        return relationshipMapper.queryTaskIdByClassId(classId);
    }

    @GetMapping("/pr/queryTaskIdByClassIds")
    public List<Integer> queryTaskIdByClassIds(@RequestBody List<Integer> classIds){
        return relationshipMapper.queryTaskIdByClassIds(classIds);
    }

    @GetMapping("/pr/queryClassIdByTaskId/{taskId}")
    public List<Integer> queryClassIdByTaskId(@PathVariable Integer taskId){
        return relationshipMapper.queryClassIdByTaskId(taskId);
    }

    @GetMapping("/pr/queryClassIdByTaskIds")
    public List<Integer> queryClassIdByTaskIds(@RequestBody List<Integer> taskIds){
        return relationshipMapper.queryClassIdByTaskIds(taskIds);
    }



    /*--------------------------------------------*/
    @GetMapping("/pr/queryUserIdByTaskId/{taskId}")
    public List<Long> queryUserIdByTaskId(@PathVariable Integer taskId){
        return relationshipMapper.queryUserIdByTaskId(taskId);
    }

    @GetMapping("/pr/queryUserIdByTaskIds")
    public List<Long> queryUserIdByTaskIds(@RequestBody List<Integer> taskIds){
        return relationshipMapper.queryUserIdByTaskIds(taskIds);
    }

    @GetMapping("/pr/queryTaskIdByUserId/{userId}")
    public List<Integer> queryTaskIdByUserId(@PathVariable Long userId){
        return relationshipMapper.queryTaskIdByUserId(userId);
    }

    @GetMapping("/pr/queryTaskIdByUserIds")
    public List<Integer> queryTaskIdByUserIds(@RequestBody List<Long> userIds){
        return relationshipMapper.queryTaskIdByUserIds(userIds);
    }




    /*-----------------------------------------*/


    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/


}
