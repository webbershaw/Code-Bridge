package edu.codebridge.relationship.controller;

import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.StudentClass;
import edu.codebridge.feign.entity.StudentTaskResource;
import edu.codebridge.feign.entity.User;
import edu.codebridge.relationship.mapper.RelationshipMapper;
import edu.codebridge.relationship.service.StudentTaskResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/relationships")
public class RelationshipController {

    @Autowired
    StudentTaskResourceService studentTaskResourceService;

    @Autowired
    RelationshipMapper relationshipMapper;


    /*----------------------------------------------------*/
    @GetMapping("/pr/queryUserIdByClassId/{classId}")
    public List<Long> queryUserIdByClassId(@PathVariable Integer classId){
        return relationshipMapper.queryUserIdByClassId(classId);
    }

    @GetMapping("/pr/queryClassIdByUserId/{userId}")
    public List<Integer> queryClassIdByUserId(@PathVariable Long userId){
        return relationshipMapper.queryClassIdByUserId(userId);
    }

    @PostMapping("/pr/queryUserIdByClassIds")
    public List<Long> queryUserIdByClassIds(@RequestBody List<Integer> classIds){
        return relationshipMapper.queryUserIdByClassIds(classIds);
    }

    @PostMapping("/pr/queryClassIdByUserIds")
    public List<Integer> queryClassIdByUserIds(@RequestBody List<Long> userIds){
        return relationshipMapper.queryClassIdByUserIds(userIds);
    }



    /*----------------------------------------------*/
    @GetMapping("/pr/queryTaskIdByClassId/{classId}")
    public List<Integer> queryTaskIdByClassId(@PathVariable Integer classId){
        return relationshipMapper.queryTaskIdByClassId(classId);
    }

    @PostMapping ("/pr/queryTaskIdByClassIds")
    public List<Integer> queryTaskIdByClassIds(@RequestBody List<Integer> classIds){
        return relationshipMapper.queryTaskIdByClassIds(classIds);
    }

    @GetMapping("/pr/queryClassIdByTaskId/{taskId}")
    public List<Integer> queryClassIdByTaskId(@PathVariable Integer taskId){
        return relationshipMapper.queryClassIdByTaskId(taskId);
    }

    @PostMapping("/pr/queryClassIdByTaskIds")
    public List<Integer> queryClassIdByTaskIds(@RequestBody List<Integer> taskIds){
        return relationshipMapper.queryClassIdByTaskIds(taskIds);
    }







    /*--------------------------------------------*/
    @GetMapping("/pr/queryUserIdByTaskId/{taskId}")
    public List<Long> queryUserIdByTaskId(@PathVariable Integer taskId){
        return relationshipMapper.queryUserIdByTaskId(taskId);
    }

    @PostMapping("/pr/queryUserIdByTaskIds")
    public List<Long> queryUserIdByTaskIds(@RequestBody List<Integer> taskIds){
        return relationshipMapper.queryUserIdByTaskIds(taskIds);
    }

    @GetMapping("/pr/queryTaskIdByUserId/{userId}")
    public List<Integer> queryTaskIdByUserId(@PathVariable Long userId){
        return relationshipMapper.queryTaskIdByUserId(userId);
    }

    @PostMapping("/pr/queryTaskIdByUserIds")
    public List<Integer> queryTaskIdByUserIds(@RequestBody List<Long> userIds){
        return relationshipMapper.queryTaskIdByUserIds(userIds);
    }




    /*-----------------------------------------*/
    @PostMapping("/pr/insertStudetTaskResource")
    public Boolean insertStudentTaskResource(@RequestBody StudentTaskResource studentTaskResource){
        return relationshipMapper.insertStudentTaskResource(studentTaskResource);
    }

    @PostMapping("pr/queryStudentTaskResourceByCondition")
    public List<StudentTaskResource> queryStudentTaskResourceByCondition(@RequestBody StudentTaskResource studentTaskResource){
        return relationshipMapper.queryStudentTasksResourceByCondition(studentTaskResource);
    }

    @PostMapping("pr/updateStudentTaskResourceByCondition")
    public Boolean updateStudentTaskResourceByCondition(@RequestBody StudentTaskResource studentTaskResource){
        return relationshipMapper.updateStudentTaskResource(studentTaskResource);
    }




    /*-----------------------------------------*/



    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/

    @PostMapping("/StudentTaskResource")
    public Result insertStudentTaskResource(@RequestBody StudentTaskResource studentTaskResource, HttpServletRequest request){
        try {
            return studentTaskResourceService.insertStudentTaskResource(request, studentTaskResource);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"出错啦！服务异常");
        }
    }

    @GetMapping ("/StudentClass/{classId}")
    public Result bindClass(@PathVariable Integer classId,HttpServletRequest request){
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user = (User) user1;
        StudentClass studentClass = new StudentClass();
        studentClass.setClassId(classId);
        studentClass.setUserId(user.getId());
        try {
            relationshipMapper.insertStudentClass(studentClass);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ErrorCode.ERR,null,"添加失败");
        }
        return new Result(ErrorCode.OK,null,"您已成功加入班级");
    }




}
