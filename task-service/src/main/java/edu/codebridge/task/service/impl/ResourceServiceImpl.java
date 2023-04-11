package edu.codebridge.task.service.impl;

import edu.codebridge.feign.client.RelationshipClient;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.code.IdentityCode;
import edu.codebridge.feign.code.ResourceStatusCode;
import edu.codebridge.feign.code.ResourceTypeCode;
import edu.codebridge.feign.entity.*;
import edu.codebridge.task.mapper.ResourceMapper;
import edu.codebridge.task.mapper.TaskResourceMapper;
import edu.codebridge.task.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    TaskResourceMapper taskResourceMapper;

    @Autowired
    RelationshipClient relationshipClient;
    @Override
    public Result queryResourceById(HttpServletRequest request, Integer resourceId) {
        return null;
    }

    @Override
    public Result insertResource(HttpServletRequest request, Resource resource) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.ERR,null,"您的登录已过期");
        }
        User user = (User) user1;
        if(user.getIdentity().equals(IdentityCode.TEACHER)) {
            resourceMapper.insertResource(resource);

        }else {
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足");
        }

        return new Result(ErrorCode.OK,null,"添加成功");
    }

    @Override
    public Result queryResourceByClassificationId(Integer classificationId, HttpServletRequest request) {
        return null;
    }

    @Override
    public Result bindResourceWithTask(HttpServletRequest request, TaskResource taskResource) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.ERR,null,"您的登录已过期");
        }
        User user = (User) user1;
        if(user.getIdentity().equals(IdentityCode.TEACHER)) {
            taskResourceMapper.insertTaskResource(taskResource);
        }else {
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足");
        }
        return new Result(ErrorCode.OK,null,"添加成功");
    }

    @Override
    public Result bindResourceWithTasks(HttpServletRequest request, List<TaskResource> taskResources) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.ERR,null,"您的登录已过期");
        }
        User user = (User) user1;
        if(user.getIdentity().equals(IdentityCode.TEACHER)) {
            taskResources.forEach(taskResourceMapper::insertTaskResource);


        }else {
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足");
        }
        return null;
    }

    @Override
    public Result queryByTaskId(HttpServletRequest request,Integer taskId) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.ERR,null,"您的登录已过期");
        }
        User user = (User) user1;
        /**
         * 这里未来有时间接入查询是否是该班级的成员或老师以及学生是否被老师封禁查看权限（复杂），
         */

        /**
         * 之后插入判断任务是否在有效期内以及权限是否允许
         */

        List<Integer> integers = taskResourceMapper.queryResourceIdsByTaskId(taskId);
        List<Resource> resources = resourceMapper.queryByResourceIds(integers);



        return new Result(ErrorCode.OK,resources,"获取资源成功");
    }

    @Override
    public Result completeResource(HttpServletRequest request, StudentTaskResource studentTaskResource) {

        studentTaskResource.setScore(0.00);
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user = (User) user1;

        //先查询一次判断是否第一次提交
        StudentTaskResource studentTaskResource1 = new StudentTaskResource();
        studentTaskResource1.setUserId(user.getId());
        studentTaskResource1.setTaskId(studentTaskResource.getTaskId());
        List<StudentTaskResource> studentTaskResources = relationshipClient.queryStudentTaskResourceByCondition(studentTaskResource1);
        Boolean isFirstSubmit = true;
        if(!studentTaskResources.isEmpty()){
            isFirstSubmit = false;
        }

        /**
         * 此处未来补充判断学生是否跟该题目有对应关系的判断
         */
        studentTaskResource.setUserId(user.getId());
        Resource resource = resourceMapper.queryResourceByResourceId(studentTaskResource.getResourceId());
        if(resource==null){
            return new Result(ErrorCode.ERR,null,"该题目不存在");
        }
        Short resourceType = resource.getResourceType();
        if(resourceType.equals(ResourceTypeCode.DOCUMENT)||resourceType.equals(ResourceTypeCode.TEXT)||resourceType.equals(ResourceTypeCode.VIDEO)) {
            if(!isFirstSubmit&&studentTaskResource1.getStatus()==ResourceStatusCode.COMPLETED){
                return new Result(ErrorCode.OK,null,"真棒，您已经多次完成任务阅览啦！");
            }
            /**
             * 如果是阅览型的资料直接赋满分，未来可加入判断是否阅读满足够时长的方法
             */
            studentTaskResource.setScore(1.00);
            studentTaskResource.setStatus(ResourceStatusCode.COMPLETED);
        }else {
            /**
             * 只要是已提交或者已完成就不能再修改，未来可以加入判断是否允许补交
             */
            if(!isFirstSubmit&&(studentTaskResource1.getStatus()==ResourceStatusCode.SUBMITTED||studentTaskResource1.getStatus()==ResourceStatusCode.COMPLETED)){
                return new Result(ErrorCode.ERR,null,"当前题目状态不允许修改");
            }
            studentTaskResource.setStatus(ResourceStatusCode.FINISHED);
        }

        if(isFirstSubmit){
            relationshipClient.insertStudentTaskResource(studentTaskResource);
            return new Result(ErrorCode.OK,null,"作答成功");
        }else {
            relationshipClient.updateStudentTaskResourceByCondition(studentTaskResource);
            return new Result(ErrorCode.OK,null,"修改答案成功");
        }



    }


}
