package edu.codebridge.relationship.mapper;

import edu.codebridge.feign.entity.*;
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
     * 根据userid和taskid查询resourceid
     * @param userId
     * @param taskId
     * @return resourceid
     */
    @Select("select resource_id from `student-task-resource` where user_id = #{userId} and task_id = #{taskId}")
    public Integer queryResourceIdByUserIdAndTaskId(Long userId, Integer taskId);


    /**
     * 根据taskid 和 resourceid查询
     * @param taskId
     * @param resourceId
     * @return List<StudentTaskResource>
     */
    @Select("select * from `student-task-resource` where task_id =#{taskId} and resource_id = #{resourceId}")
    public List<StudentTaskResource> queryStudentTaskResourceByTaskIdAndResourceId(Integer taskId, Integer resourceId);


    /**
     * 根据taskid和resourceid查询userid
     * @param taskId
     * @param resourceId
     * @return userid
     */
    @Select("select user_id from `student-task-resource` where task_id =#{taskId} and resource_id = #{resourceId}")
    public Long queryUserIdByTaskIdAndResourceId(Integer taskId, Integer resourceId);


    /**
     * 根据userid和resourceid查询taskid
     * @param userId
     * @param resourceId
     * @return List<StudentTaskResource>
     */
    @Select("select * from `student-task-resource` where user_id = #{userId} and resource_id = #{resourceId}")
    public List<StudentTaskResource> queryStudentTaskResourceByUserIdAndResourceId(Long userId, Integer resourceId);


    /**
     * 根据userid和resourceid查询taskid
     * @param userId
     * @param resourceId
     * @return taskid
     */
    @Select("select task_id from `student-task-resource` where user_id = #{userId} and resource_id = #{resourceId}")
    public Integer queryTaskIdByUserIdAndResourceId(Long userId, Integer resourceId);


    /**
     * 插入
     * @param studentTaskResource
     */

    @Insert("insert into `student-task-resource` (user_id, task_id, resource_id, status, score)" +
            " values (#{userIde},#{taskId},#{resourceId},#{status},#{score})")
    public Boolean insertStudentTaskResource(StudentTaskResource studentTaskResource);





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


    /**
     * 根据taskid查询userid
     * @param taskId
     * @return List<Long>
     */
    @Select("select user_id from `student-task` where task_id = #{taskId}")
    public List<Long> queryUserIdByTaskId(Integer taskId);


    /**
     * 根据userid查询taskid
     * @param userId
     * @return Integer
     */
    @Select("select task_id from `student-task` where user_id = #{userId}")
    public Integer queryTaskIdByUserId(Long userId);


    /**
     * 往数据表student-task插入数据
     * @param studentTask
     * @return Boolean
     */
    @Insert("insert into cb_relationship.`student-task`" +
            "(user_id, task_id, status, score, `accessible`)" +
            "values " +
            "(#{userId}, #{taskId}, #{status}, #{score}, #{ccessible})")
    public Boolean insertStudentTask(StudentTask studentTask);





    /*------------------------------------------------ClassTask--------------------------------*/

    /**
     * 根据条件查询calsstask
     * @param classTask
     * @return ClassTask
     */
    public ClassTask queryClassTaskByCondition(ClassTask classTask);


    /**
     * 根据条件查询
     * @param classTask
     * @return List<ClassTask>
     */
    public List<ClassTask> queryClassTasksByCondition(ClassTask classTask);


    /**
     * 往class-task数据表中插入数据
     * @param classTask
     * @return Boolean
     */
    @Insert("insert into cb_relationship.`class-task`" +
            "(class_id, task_id, start_time, end_time, invisible, `accessible`, weight, resubmit, check_after_submit, correction_mode) " +
            "values " +
            "(#{classId}, #{taskId}, #{startTime}, #{endTime}, #{invisible}, #{accessible}, #{weight}, #{resubmit}, #{checkAfterSubmit}, #{correctionMode})")
    public Boolean insertClassTask(ClassTask classTask);


    /**
     * 更新class-task数据表
     * @return Boolean
     */
    public Boolean updateClassTask(ClassTask classTask);





    /*------------------------------------------StudentClass---------------------------*/
    /**
     * 根据userid查询
     * @param userId
     * @return List<StudentClass>
     */
    @Select("select * from cb_relationship.`student-class` where user_id = #{userId}")
    public List<StudentClass> queryStudentClassByUserId(Long userId);

    /**
     * 根据classid查询
     * @param classId
     * @return List<StudentClass>
     */
    @Select("select * from cb_relationship.`student-class` where class_id = #{classId}")
    public List<StudentClass> queryStudentClassByClassId(Integer classId);

    @Insert("insert into cb_relationship.`student-class` " +
            "(user_id, class_id)" +
            "values " +
            "(#{userId}, #{classId})"
            )
    public Boolean insertStudentClass(StudentClass studentClass);


    public Boolean updateStudentClass(StudentClass studentClass);

}
