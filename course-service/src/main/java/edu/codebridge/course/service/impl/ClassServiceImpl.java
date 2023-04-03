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
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class ClassServiceImpl implements ClassService {
    @Autowired
    private UserClient userClient;
    @Autowired
    private CourseMapper courseMapper;

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
        if (Check.checkUser(request).getData()==null){
           return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
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
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        Long id = user.getId();
        if (clazz.getUserId()!=id){
            return new Result(ErrorCode.ERR,null,"此为他人的班级，无法修改！");
        }

        classMapper.updateClassById(clazz);
        return new Result(ErrorCode.OK,null,"修改成功！");

    }

    /**
     * 通过classId得到userId
     * @param classId
     * @param request
     * @return
     */
    @Override
    public Result queryUserIdByClassId(Integer classId, HttpServletRequest request) {

        Long userId = classMapper.queryUserIdByClassId(classId);
        return new  Result(ErrorCode.OK,userId,"查询成功！");
    }

    @Override
    public Result queryUserIdsByClassIds(List<Integer> classIds, HttpServletRequest request) {
        List<Long> userIds = classMapper.queryUerIdsByClassIds(classIds);
        return new Result(ErrorCode.OK,userIds,"查询成功！");
    }

    @Override
    public Result queryClassByClassId(Integer classId, HttpServletRequest request) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;

        Class clazz = classMapper.getClassByIdAndNoDeleted(classId);
        User user2 = userClient.queryById(clazz.getUserId());
        clazz.setUser(user2);
        clazz.getCourse().setUser(userClient.queryById(clazz.getCourse().getUserId()));


        if(user.getId()!= clazz.getUserId()&&user.getId()!=clazz.getCourse().getUserId()&&relationshipClient.queryUserIdByClassId(clazz.getClassId()).contains(user.getId())){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的无权查看" );
        }
        return new Result(ErrorCode.OK,clazz,"查询成功！");

    }

    @Override
    public Result queryClassesByUserId(Long userId, HttpServletRequest request) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;

        List<Class> classes = classMapper.queryClassByUserId(userId);
        classes.stream().forEach(item->item.setUser(userClient.queryById(item.getUserId())));
        //此方法要对老师
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的无权查看" );
        }
        return new Result(ErrorCode.OK,classes,"查询成功！");
    }

    @Override
    public Result queryClassesByStudentUserId(HttpServletRequest request) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;

        List<Integer> classIds= relationshipClient.queryClassIdByUserId(user.getId());
        List<Class> classes = classMapper.queryClassByClassIds(classIds);
        classes.stream().forEach(item->item.setUser(userClient.queryById(item.getUserId())));

        return new Result(ErrorCode.OK,classes,"查询成功！");

    }



//    public Result queryClassesByUserId(HttpServletRequest request){
//        Object user1 = request.getSession().getAttribute("user");
//        if(user1==null){
//            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
//        }
//        User user =(User) user1;
//        List<Integer> courseId=null;
//        List<Class> classes = classMapper.queryClassByUserId(user.getId().intValue());
//        for (Class aClass : classes) {
//            if(aClass.getCourse().getEndTime().isBefore(LocalDateTime.now())){
//
//            }
//        }
//        classes.stream()
//                .peek(clazz -> clazz.setUser(userClient.queryById(clazz.getUserId())))
//                .collect(Collectors.toList());
//        return new Result(ErrorCode.OK,classes,"");

//    }
        public Result queryClassesByIds(List<Integer> classIds,HttpServletRequest request){
            List<Class> classes = classMapper.queryClassByIds(classIds);
//            for (int i = 0; i < ; i++) {
//
//            }
            return new  Result(ErrorCode.OK,classes,"查询成功！");
        }




}
