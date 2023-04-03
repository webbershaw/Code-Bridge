package edu.codebridge.course.controller;

import edu.codebridge.course.service.CourseService;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Course;
import edu.codebridge.feign.entity.Result;
import org.springframework.web.bind.annotation.*;

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
            e.printStackTrace();
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }

    }
    @PutMapping
    public Result upadte(Course course,HttpServletRequest request){
        try {
             return courseService.updateCourse(course,request);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }

    }

    @GetMapping()
    public Result queryCourseByCourseId(Integer courseId,HttpServletRequest request){

        try {
            return courseService.getCourseById(courseId,request);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }

    }


@GetMapping("/zz")
    public Result queryAll(HttpServletRequest request){

        try {
            return courseService.getAllCourse(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }
    }



    public Result  queryCoursesByUserId(HttpServletRequest request){
        try {
            return courseService.queryCoursesByUserId(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }
    }

    public Result deletedCourseById(HttpServletRequest request,Integer courseId){
        try {
            return courseService.deletedCourseById(courseId,request);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }
    }
}
