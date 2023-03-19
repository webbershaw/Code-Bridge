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
    Course  getCourseById(Long id);

    /**
     * 添加课程
     * @param course
     */
    Result addCourse(Course course, HttpServletRequest request);

    /**
     * 得到所有课程
     * @return
     */
    List<Course> getAllCourse();

    /**
     * 修改课程
     * @param course
     */
    void updateCourse(Course course);

    /**
     * 删除课程
     * @param id
     */
    void deletedCourseById(Long id);
}
