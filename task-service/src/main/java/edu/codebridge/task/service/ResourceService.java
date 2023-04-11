package edu.codebridge.task.service;

import edu.codebridge.feign.entity.Resource;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.StudentTaskResource;
import edu.codebridge.feign.entity.TaskResource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ResourceService {
    /**
     * 根据resource_id查询resource
     * @param resourceId
     * @return List<Resource>
     */
   Result queryResourceById(HttpServletRequest request,Integer resourceId);

    /**
     * 添加资源
     * @param request
     * @param resource
     * @return
     */
   Result insertResource(HttpServletRequest request, Resource resource);

    /**
     * 根据classification_id 查询
     * @param classificationId
     * @return List<Resource>
     */
    Result queryResourceByClassificationId(Integer classificationId,HttpServletRequest request);


    /**
     * 将任务与资源进行绑定
     * @param request
     * @param taskResource
     * @return
     */
    Result bindResourceWithTask(HttpServletRequest request, TaskResource taskResource);

    /**
     *将任务与多个资源进行绑定
     * @param request
     * @param taskResources
     * @return
     */
    Result bindResourceWithTasks(HttpServletRequest request, List<TaskResource> taskResources);

    /**
     * 通过任务查看该任务所有的资源
     * @param request
     * @return
     */
    Result queryByTaskId(HttpServletRequest request,Integer taskId);

    Result completeResource(HttpServletRequest request, StudentTaskResource studentTaskResource);


}
