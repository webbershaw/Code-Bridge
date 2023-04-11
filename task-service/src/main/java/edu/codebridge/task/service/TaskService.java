package edu.codebridge.task.service;

import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.Task;

import javax.servlet.http.HttpServletRequest;

public interface TaskService {
    /**
     * 根据taskId查询
     * @param request
     * @param taskId
     * @return
     */
  Result queryTaskByTaskId(HttpServletRequest request,Integer taskId);

    /**
     * 根据modelId
     * @param modelId
     * @param request
     * @return
     */
  Result queryTaskModelId(Integer modelId,HttpServletRequest request);

    /**
     * 根据条件查询
     * @param task
     * @param request
     * @return
     */
  Result  queryTaskByCondition(Task task,HttpServletRequest request);

    /**
     * 更新任务
     * @param task
     * @param request
     * @return
     */
  Result updateTask(Task task,HttpServletRequest request);

    /**
     * 添加task
     * @param task
     * @return
     */
  Result InsertTask(Task task,HttpServletRequest request);




}
