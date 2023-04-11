package edu.codebridge.task.mapper;

import edu.codebridge.feign.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;



@Mapper
@Repository
public interface TaskMapper {
    /**
     * 根据taskid查询
     * @param taskId
     * @return  List<Task>
     */

    public Task queryTaskByTaskId(Integer taskId);

    /**
     * 根据modelid
     * @param modelId
     * @return  List<Task>
     */
//    @Select("select * from cb_task.task where course_id = #{courseId}")
    public Task queryTaskByModelId(Integer modelId);

    /**
     * 根据条件查询
     * @param task
     * @return List<Task>
     */
    public List<Task> queryTaskByCondition(Task task);

    /**
     * 更新task数据表
     * @param task
     * @return Boolean
     */
    public Boolean updateTask(Task task);

    @Insert("insert into cb_task.task" +
            "(task_id, model_id, task_name, task_type)" +
            "value " +
            "(#{taskId}, #{modelId}, #{taskName}, #{taskType}) ")
    public Boolean insertTask(Task task);





}
