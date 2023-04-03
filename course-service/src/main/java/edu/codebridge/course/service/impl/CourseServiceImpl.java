package edu.codebridge.course.service.impl;

import edu.codebridge.course.common.Check;
import edu.codebridge.course.mapper.CourseMapper;
import edu.codebridge.course.service.CourseService;
import edu.codebridge.feign.client.UserClient;
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
import java.util.stream.Collectors;

@Component
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserClient userClient;
    @Override
    public Result getCourseById(Integer id,HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }

        Course course = courseMapper.getCourseById(id);

        return  new Result(ErrorCode.OK,course,"查询成功！");
    }

    @Override
    public Result addCourse(Course course, HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }

        course.setUserId(user.getId());

        courseMapper.addCourse(course);

        return new Result(ErrorCode.OK,null,"添加成功");
    }

    @Override
    public Result getAllCourse(HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        List<Course> courses = courseMapper.getAllCourses();
        courses.stream()
                .peek(course -> course.setUser(userClient.queryById(course.getUserId())))
                .collect(Collectors.toList());
        return new  Result(ErrorCode.OK,courses,"获取成功");
    }

    @Override
    public Result updateCourse(Course course,HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        Long id = user.getId();
        if (course.getUserId()!=id){
            return new Result(ErrorCode.ERR,null,"此为他人的课程，无法修改！");
        }

        course.setUserId(user.getId());
        courseMapper.updateCourseById(course);
        return new  Result(ErrorCode.OK,null,"更新成功");
    }

    @Override
    public Result deletedCourseById(Integer id,HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        courseMapper.deleteCourseById(id);
        return new Result(ErrorCode.OK,null,"删除成功！");
    }

    @Override
    public Result queryCoursesByUserId(Long id, HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        List<Course> courses = courseMapper.queryCoursesByUserId(id);

        return new Result(ErrorCode.OK,courses,"查询成功！");
    }
}
