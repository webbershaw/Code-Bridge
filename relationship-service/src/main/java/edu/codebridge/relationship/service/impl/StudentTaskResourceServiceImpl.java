package edu.codebridge.relationship.service.impl;

import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.StudentTaskResource;
import edu.codebridge.feign.entity.User;
import edu.codebridge.relationship.mapper.RelationshipMapper;
import edu.codebridge.relationship.service.StudentTaskResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service
public class StudentTaskResourceServiceImpl implements StudentTaskResourceService {
    @Autowired
    RelationshipMapper relationshipMapper;
    @Override
    public Result insertStudentTaskResource(HttpServletRequest request, StudentTaskResource studentTaskResource) {
        HttpSession session = request.getSession();
        Object user1 = session.getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user = (User) user1;

        studentTaskResource.setUserId(user.getId());

        relationshipMapper.insertStudentTaskResource(studentTaskResource);



        return new Result(ErrorCode.OK,null,"保存成功");
    }
}
