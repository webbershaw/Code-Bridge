package edu.codebridge.relationship.service;

import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.StudentTaskResource;

import javax.servlet.http.HttpServletRequest;

public interface StudentTaskResourceService {
    public Result insertStudentTaskResource(HttpServletRequest httpServletRequest, StudentTaskResource studentTaskResource);

}
