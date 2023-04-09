package edu.codebridge.course.service.impl;

import edu.codebridge.course.common.Check;
import edu.codebridge.course.mapper.CourseMapper;
import edu.codebridge.course.service.ClassService;
import edu.codebridge.course.service.CourseService;
import edu.codebridge.feign.client.RelationshipClient;
import edu.codebridge.feign.client.UserClient;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.code.IdentityCode;
import edu.codebridge.feign.entity.Class;
import edu.codebridge.feign.entity.Course;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserClient userClient;
    @Autowired
    private RelationshipClient relationshipClient;
    @Autowired
    private ClassService classService;

    /**通过课程id
     * 查询课程
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public Result getCourseById(Integer id,HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();


        //查询课程
        //此操作把课程和分班的老师传入
        Course course = courseMapper.getCourseById(id);
        //把课程老师传入
        User user1 = userClient.queryById(course.getUserId());
        course.setUser(user1);
        List<Class> classes = course.getClasses();

        List<Long> userIds = new ArrayList<>();
        for (Class c : classes) {
            userIds.add(c.getUserId());
        }


        List<User> users = userClient.queryUsersByIds(userIds);


        for (Class c : classes) {

            User classUser = users.stream()
                    .filter(u -> u.getId().equals(c.getUserId()))
                    .findFirst()
                    .orElse(null);

            // Assign the user to the class
            c.setUser(classUser);
        }
        course.setClasses(classes);

        return  new Result(ErrorCode.OK,course,"查询成功！");
    }

    /**
     * 添加课程
     * @param course
     * @param request
     * @return
     */
    @Override
    public Result addCourse(Course course, HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //校验教师身份
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //将当前登录用户的id作为创建人的编号
        course.setUserId(user.getId());
        //添加课程
        courseMapper.addCourse(course);

        return new Result(ErrorCode.OK,null,"添加课程成功");
    }

    /**
     * 得到所有课程
     * @param request
     * @return
     */
    @Override
    public Result getAllCourse(HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        /**
         * 优化？
         */
        List<Course> courses = courseMapper.getAllCourses();
        courses.stream()
                .peek(course -> course.setUser(userClient.queryById(course.getUserId())))
                .collect(Collectors.toList());
        return new  Result(ErrorCode.OK,courses,"获取成功");
    }

    /**
     * 更新课程信息
     * @param course
     * @param request
     * @return
     */
    @Override
    public Result updateCourse(Course course,HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //老师才能修改课程
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //判断课程的创建者是否与登录者一致
        Long id = user.getId();
        if (course.getUserId()!=id){
            return new Result(ErrorCode.ERR,null,"此为他人的课程，无法修改！");
        }
        //修改课程
        course.setUserId(user.getId());
        courseMapper.updateCourseById(course);
        return new  Result(ErrorCode.OK,null,"修改课程成功");
    }

    /**
     * 软删除
     * @param id
     * @param request
     * @return
     */
    @Override
    public Result deletedCourseById(Integer id,HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //必须为老师才能删除
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //删除课程
        courseMapper.deleteCourseById(id);
        return new Result(ErrorCode.OK,null,"删除成功！");
    }

    /**
     *对老师开放
     * @param request
     * @return
     */
    @Override
    public Result queryCoursesByUserId(HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //校验老师身份
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //couser类中的userId与用户登录的id相等来查询课程
        //教师发起的课程
        List<Course> courses = courseMapper.queryCoursesByUserId(user.getId());

        return new Result(ErrorCode.OK,courses,"查询成功！");
    }

    /**
     *
     * @param request
     * @return
     */
    @Override
    public Result queryCoursesByStudentUserId(HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();


        //得到班级的id
        List<Integer> classIds = relationshipClient.queryClassIdByUserId(user.getId());
        //关联课程
        List<Course> courses = courseMapper.queryCourseByClassIds(classIds);

        return new Result(ErrorCode.OK,courses,"查询成功！");
    }
}
