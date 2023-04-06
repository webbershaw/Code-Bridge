package edu.codebridge.course.service.impl;

import edu.codebridge.course.common.Check;
import edu.codebridge.course.mapper.ClassMapper;
import edu.codebridge.course.mapper.CourseMapper;
import edu.codebridge.course.service.ClassService;
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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class ClassServiceImpl implements ClassService {
    @Autowired
    private UserClient userClient;
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private RelationshipClient relationshipClient;
    @Autowired
    private ClassMapper classMapper;
    final int QUERY_TYPE_EXPIRED = 0;
    final int QUERY_TYPE_UNEXPIRED = 1;


    /**
     * 添加分班
     * @param clazz
     * @return
     */
    @Override
    public Result addClass(Class clazz,HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
           return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        //添加需要教师权限
        if(!user.getIdentity().equals(IdentityCode.TEACHER)){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //添加分班
        classMapper.addClass(clazz);
        return new Result(ErrorCode.OK,null,"新增班级成功！");
    }

    /**
     * 修改班级
     * @param clazz
     * @param request
     * @return
     */
    @Override
    public Result updateClassById(Class clazz, HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //修改需要教师权限
        if(!user.getIdentity().equals(IdentityCode.TEACHER)){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }

        //只能操作自己的班级
        Long id = user.getId();
        if (clazz.getUserId()!=id){
            return new Result(ErrorCode.ERR,null,"此为他人的班级，无法修改！");
        }
        //修改自己的班级
        classMapper.updateClassById(clazz);
        return new Result(ErrorCode.OK,null,"修改班级成功！");

    }

    /**
     * 通过classId得到userId
     * @param classId
     * @param request
     * @return
     */
    @Override
    public Result queryUserIdByClassId(Integer classId, HttpServletRequest request) {
        //校验登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        //查询
        Long userId = classMapper.queryUserIdByClassId(classId);
        return new  Result(ErrorCode.OK,userId,"查询成功！");
    }

    /**
     * 通过clssIds得到userIds
     * @param classIds
     * @param request
     * @return
     */
    @Override
    public Result queryUserIdsByClassIds(List<Integer> classIds, HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //查询
        List<Long> userIds = classMapper.queryUerIdsByClassIds(classIds);
        return new Result(ErrorCode.OK,userIds,"查询成功！");
    }

    /**
     * 通过classId得到class
     * @param classId
     * @param request
     * @return
     */
    @Override
    public Result queryClassByClassId(Integer classId, HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //把用户敏感信息去掉
        Class clazz = classMapper.getClassByIdAndNoDeleted(classId);
        User user2 = userClient.queryById(clazz.getUserId());
        user2.setPwd(null);
        user2.setEmail(null);
        user2.setTel(null);
        clazz.setUser(user2);
        User user3 = userClient.queryById(clazz.getCourse().getUserId());
        user3.setPwd(null);
        user3.setTel(null);
        user3.setEmail(null);
        clazz.getCourse().setUser(user3);

        //这个课程的学生和这个分班老师和课程老师都可以看
        if(user.getId()!= clazz.getUserId()&&user.getId()!=clazz.getCourse().getUserId()&&!relationshipClient.queryUserIdByClassId(clazz.getClassId()).contains(user.getId())){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的无权查看" );
        }
        return new Result(ErrorCode.OK,clazz,"查询成功！");

    }

    /**
     * 老师id查看分班
     * @param request
     * @return
     */
    @Override
    public Result queryClassesByUserId(HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        //此方法要对老师开放
        if(!user.getIdentity().equals(IdentityCode.TEACHER)){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的无权查看" );
        }
        //通过老师的id查带的班
        List<Class> classes = classMapper.queryClassByUserId(user.getId());
        if(classes.isEmpty()){
            return new Result(ErrorCode.OK,new ArrayList<>(),"您没有任何分班");
        }
        classes.stream()
                .forEach(c -> c.setUser(userClient.queryUsersByIds(Collections.singletonList(c.getUserId())).stream().findFirst().orElse(null)));
        return new Result(ErrorCode.OK,classes,"查询成功！");
    }

    /**
     * 学生查询class
     * @param request
     * @return
     */
    @Override
    public Result queryClassesByStudentUserId(HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        //查询
        List<Integer> classIds= relationshipClient.queryClassIdByUserId(user.getId());
        if(classIds.isEmpty()){
            return new Result(ErrorCode.OK,new ArrayList<>(),"您没有任何班级");
        }
        List<Class> classes = classMapper.queryClassByClassIds(classIds);
        classes.stream()
                .forEach(c -> c.setUser(userClient.queryUsersByIds(Collections.singletonList(c.getUserId())).stream().findFirst().orElse(null)));


        return new Result(ErrorCode.OK,classes,"查询成功！");

    }

}
