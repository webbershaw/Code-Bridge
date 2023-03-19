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


    @GetMapping("/pr/queryUserIdByClassId/{classId}")
    public List<Long> queryUserIdByClassId(Integer classId){
        return relationshipMapper.queryUserIdByClassId(classId);
    }



    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/


}
