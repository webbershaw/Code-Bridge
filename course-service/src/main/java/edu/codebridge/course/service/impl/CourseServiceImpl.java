package edu.codebridge.course.service.impl;

import edu.codebridge.course.mapper.CourseMapper;
import edu.codebridge.course.service.CourseService;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.code.IdentityCode;
import edu.codebridge.feign.entity.Course;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Component
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Course getCourseById(Long id) {
        Course course = courseMapper.getCourseById(id);
        return  course;
    }

    @Override
    public Result addCourse(Course course, HttpServletRequest request) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }

        course.setUserId(user.getId());

        courseMapper.addCourse(course);

        return new Result(ErrorCode.OK,null,"添加成功");
    }

    @Override
    public List<Course> getAllCourse() {

        return courseMapper.getAllCourses();
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.updateCourseById(course);
    }

    @Override
    public void deletedCourseById(Long id) {
        courseMapper.deleteCourseById(id);
    }
}
