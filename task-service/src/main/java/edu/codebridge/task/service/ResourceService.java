package edu.codebridge.task.service;

import edu.codebridge.feign.entity.Resource;
import edu.codebridge.feign.entity.Result;

import javax.servlet.http.HttpServletRequest;

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


}
