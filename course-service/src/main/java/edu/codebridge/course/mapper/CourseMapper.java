package edu.codebridge.course.mapper;

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
     * 通过id得到课程
     * @return
     */
    @Select("select * from course where course_id=#{courseId} and deleted=0")
    Course getCourseById(Long id);

    /**
     * 添加课程
     * @param course
     */

    void addCourse(Course course);

    /**
     * 获得所有课程
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
    void deleteCourseById(Long id);




}
