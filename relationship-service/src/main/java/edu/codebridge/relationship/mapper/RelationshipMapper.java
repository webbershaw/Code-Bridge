package edu.codebridge.relationship.mapper;

import edu.codebridge.feign.entity.StudentTask;
import edu.codebridge.feign.entity.StudentTaskResource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RelationshipMapper {

    /*---------------------------------StudentTaskResource-----------------------------------*/
    /**
     * 根据userid 和 taskid查询
     * @param userId
     * @param taskId
     * @return List<StudentTaskResource>
     */
    @Select("select * from `student-task-resource` where user_id = #{userId} and task_id = #{taskId}")
    public List<StudentTaskResource> queryStudentTaskResourceByUserIdAndTaskId(Long userId, Integer taskId);

    /**
     * 根据taskid 和 resourceid查询
     * @param taskId
     * @param resourceId
     * @return List<StudentTaskResource>
     */

//    @Select("select * from `student-task-resource` where task_id = #{taskId} and resource_id = #{resourceId}")
    @Select("select * from `student-task-resource` where task_id =#{taskId} and resource_id = #{resourceId}")
    public List<StudentTaskResource> queryStudentTaskResourceByTaskIdAndResourceId(Integer taskId, Integer resourceId);


    /**
     * 根据userid和resourceid查询taskid
     * @param userId
     * @param resourceId
     * @return List<StudentTaskResource>
     */
    @Select("select * from `student-task-resource` where user_id = #{userId} and resource_id = #{resourceId}")
    public List<StudentTaskResource> queryStudentTaskResourceByUserIdAndResourceId(Long userId, Integer resourceId);

    /**
     * 插入
     * @param studentTaskResource
     */

    @Insert("insert into `student-task-resource` (user_id, task_id, resource_id, status, score)" +
            " values (#{userIde},#{taskId},#{resourceId},#{status},#{score})")
    public StudentTaskResource insertStudentTaskResource(StudentTaskResource studentTaskResource);





    /*---------------------------------StudentTask-----------------------------------*/
    /**
     * 根据userid查询
     * @param userId
     * @return List<StudentTask>
     */
    @Select("select * from `student-task` where user_id = #{userId}")
    public List<StudentTask> queryStudentTaskByUserId(Long userId);

    /**
     * 根据taskid查询
     * @param taskId
     * @return List<StudentTask>
     */
    @Select("select * from `student-task` where task_id = #{taskId}")
    public List<StudentTask> queryStudentTaskByTaskId(Integer taskId);




}
