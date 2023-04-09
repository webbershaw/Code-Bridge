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

    public List<Task> queryTaskByTaskId(Integer taskId);

    /**
     * 根据courseid
     * @param CourseId
     * @return  List<Task>
     */
//    @Select("select * from cb_task.task where course_id = #{courseId}")
    public List<Task> queryTaskByCourseId(Integer CourseId);

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
            "(task_id, course_id, task_name, task_type)" +
            "value " +
            "(#{taskId}, #{courseId}, #{taskName}, #{taskType}) ")
    public Boolean InsertTask(Task task);





}
