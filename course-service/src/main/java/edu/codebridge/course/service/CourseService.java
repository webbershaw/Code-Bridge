package edu.codebridge.course.service;

import edu.codebridge.feign.entity.Course;
import edu.codebridge.feign.entity.Result;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Component
public interface CourseService {
    /**
     * 得到couser通过id
     * @return
     */
    Result  getCourseById(Integer id,HttpServletRequest request);

    /**
     * 添加课程
     * @param course
     */
    Result addCourse(Course course, HttpServletRequest request);

    /**
     * 得到所有课程
     * @return
     */
    Result getAllCourse(HttpServletRequest request);

    /**
     * 修改课程
     * @param course
     */
    Result updateCourse(Course course,HttpServletRequest request);

    /**
     * 删除课程
     * @param id
     */
    Result deletedCourseById(Integer id,HttpServletRequest request);


    /**
     * 对老师开放
     * @param request
     * @return
     */
    Result queryCoursesByUserId(HttpServletRequest request);


    /**
     * 对学生开放
     * @param request
     * @return
     */
    Result queryCoursesByStudentUserId(HttpServletRequest request);
}
