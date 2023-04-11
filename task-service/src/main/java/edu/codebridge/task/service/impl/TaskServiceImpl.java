package edu.codebridge.task.service.impl;

import edu.codebridge.feign.client.RelationshipClient;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.code.IdentityCode;
import edu.codebridge.feign.entity.Resource;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.Task;
import edu.codebridge.feign.entity.User;
import edu.codebridge.task.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TaskServiceImpl implements edu.codebridge.task.service.TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private RelationshipClient relationshipClient;

    /**
     * 对老师和学生开放查询任务
     * @param request
     * @param taskId
     * @return
     */
    @Override
    public Result queryTaskByTaskId(HttpServletRequest request, Integer taskId) {
        //校验登录
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;
        //查出该任务详细信息,任务的所有资源
        Task tasks = taskMapper.queryTaskByTaskId(taskId);
        //如果是题目则给出不给出正确答案
        List<Resource> resources = tasks.getResources();
        resources.stream().filter(resource -> resource.getResourceType() == 1).
                forEach(resource -> resource.setCorrectAnswer(""));
        return new Result(ErrorCode.OK,tasks,"查询成功！");
    }

    /**
     * 通过course_id查询
     * @param modelId
     * @param request
     * @return
     */
    @Override
    public Result queryTaskModelId(Integer modelId, HttpServletRequest request) {
        //校验登录
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;
        //查询
        Task tasks = taskMapper.queryTaskByModelId(modelId);
        //题目不给出正确答案
        List<Resource> resources = tasks.getResources();
        resources.stream().filter(resource -> resource.getResourceType() == 1).
                forEach(resource -> resource.setCorrectAnswer(""));
        return new Result(ErrorCode.OK,tasks,"查询成功！");
    }

    /**
     *
     * @param task
     * @param request
     * @return
     */
    @Override
    public Result queryTaskByCondition(Task task, HttpServletRequest request) {
        return null;
    }

    @Override
    public Result updateTask(Task task, HttpServletRequest request) {
        //校验登录
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;
        //修改对老师开放
        if(!user.getIdentity().equals(IdentityCode.TEACHER)){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的无权查看" );
        }
        //只能本人修改
        List<Long> longs = relationshipClient.queryUserIdByTaskId(task.getTaskId());
        Long first = longs.stream().findFirst().orElse(null);
        if (first!=user.getId()){
            return  new Result(ErrorCode.ERR,null,"你不能修改其他人的任务");

        }
        taskMapper.updateTask(task);
        return  new Result(ErrorCode.OK,null,"修改成功!");





    }

    @Override
    public Result InsertTask(Task task,HttpServletRequest request) {
        //校验登录
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"您的登录已过期");
        }
        User user =(User) user1;
        //添加对老师开放
        if(!user.getIdentity().equals(IdentityCode.TEACHER)){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的无权查看" );
        }
        taskMapper.insertTask(task);
        return new Result(ErrorCode.OK,null,"添加成功！");
    }
}
