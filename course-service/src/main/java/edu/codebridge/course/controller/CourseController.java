package edu.codebridge.course.controller;

import edu.codebridge.course.service.CourseService;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Course;
import edu.codebridge.feign.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/courses/course")
public class CourseController {
    private CourseService courseService;
    @PostMapping()
    public Result save(Course course, HttpServletRequest request){

        try {
            return courseService.addCourse(course, request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }

    }

}
