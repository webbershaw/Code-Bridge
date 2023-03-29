package edu.codebridge.task.mapper;


import edu.codebridge.feign.entity.TaskResource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface TaskResourceMapper {
    /**
     * 根据taskId查询
     * @param taskId
     * @return  List<TaskResource>
     */
    @Select("select * from cb_task.`task-resource` where task_id = #{taskId}")
    public List<TaskResource> queryTaskResourceByTaskId(Integer taskId);

    /**
     * 根据resourceid查询
     * @param resourceId
     * @return  List<TaskResource>
     */
    @Select("select * from cb_task.`task-resource` where resource_id = #{resourceId}")
    public List<TaskResource> queryTaskResourceByResourceId(Integer resourceId);

    /**
     * 根据条件查询
      * @param taskResource
     * @return List<TaskResource>
     */
    public List<TaskResource> queryTaskResourceByCondition(TaskResource taskResource);

    /**
     * 插入TaskResource数据表
     * @param taskResource
     * @return Boolean
     */
    @Insert("insert into cb_task.`task-resource`" +
            "(task_id, resource_id, score)" +
            "value " +
            "(#{taskId}, #{resourceId}, #{score})")
    public Boolean insertTaskResource(TaskResource taskResource);

    /**
     * 更新TaskResource数据表
     * @param taskResource
     * @return Boolean
     */
    public Boolean updateTaskResource(TaskResource taskResource);

}
