package edu.codebridge.course.mapper;

import edu.codebridge.feign.entity.Class;
import edu.codebridge.feign.entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {
    /**
     * 通过course_id得到未被删除的课程
     * @return
     */
    @Select("select * from course where course_id=#{courseId} and deleted=0")
    Course getCourseByIdAndNoDeleted(Integer id);

    /**
     * 通过course_id得到的课程(包括被删除的课程)
     * @param id
     * @return
     */
//    @Select("select * from course where course_id=#{courseId}")
    Course getCourseById(Integer id);
    /**
     * 添加课程
     * @param course
     */

    void addCourse(Course course);

    /**
     * 获得所有未被删除的课程
     * @return
     */
    @Select("select * from course where deleted=0")
    List<Course>  getAllCourses();

    /**
     * 修改课程
     * @param course
     */

    void  updateCourseById(Course course);

    /**
     * 通过id删除课程
     * @param id
     */
    @Update("update course set deleted =1 where course_id=#{courseId}   ")
    void deleteCourseById(Integer id);

    /**
     *通过courseId查询未被删除的class
     * @param courseId
     * @return
     */
    List<Class> queryClassById(Integer courseId);


    /**
     * 通过userId查询未被删除的course
     * @param userId
     * @return
     */
    List<Course> queryCoursesByUserId(Long  userId);

    /**
     * 通过userIds查询未被删除的courses
     * @param userIds
     * @return
     */

    List<Course> queryCoursesByUserIds(List<Integer> userIds);
}
