package edu.codebridge.course.mapper;

import edu.codebridge.feign.entity.Class;
import edu.codebridge.feign.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassMapper {

    /**
     * 通过class_id得到未被删除的class
     * @param id
     * @return
     */

    Class getClassByIdAndNoDeleted(Integer id);

    /**
     * 通过class_id得到所有class（包括被删除的）
     * @param id
     * @return
     */
    @Select("select * from class where class_id=#{classId} ")
    Class getClassById(Integer id);

    /**
     * 添加class
     * @param class1
     */
    void addClass(Class class1);


    /**
     * 查询未被删除所有的class
     * @return
     */
    @Select("select  * from  class where deleted =0")
    List<Class> getAllClass();

    /**
     * 根据id更新数据
     * @param class1
     */
    void updateClassById(Class class1);

    /**
     * 软删除通过class_id
     * @param id
     */
    @Update("update class set deleted =1 where class_id=#{classId}")
    void deletedClassById(Integer id);

    /**
     * 通过userId查询该用户的所有分班
     * 未被删除
     * @param userId
     * @return
     */
//    ------------------------------------------要不要把course放入
    @Select("select * from class where user_id=#{userId} and deleted=0")
    List<Class> queryClassByUserId(Integer userId);

    /**
     * 查出classes通过classId
     * 且将course赋值
     * @param classIds
     * @return
     */
    List<Class> queryClassByIds(List<Integer> classIds);

    /**
     * 通过classId查询Userid
     * @param classId
     * @return
     */
    Long    queryUserIdByClassId(Integer classId);

    /**
     *通过classIds查询对应的UserIds集合
     * @param classIds
     * @return
     */
    List<Long> queryUerIdsByClassIds(List<Integer> classIds);



}
