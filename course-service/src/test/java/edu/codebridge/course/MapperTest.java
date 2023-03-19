package edu.codebridge.course;

import edu.codebridge.course.mapper.CourseMapper;
import edu.codebridge.course.service.CourseService;
import edu.codebridge.feign.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {
    @Autowired
    private  CourseService courseService;
    @Test
    public void  sssStest(){
        Course course=new Course();
        course.setCourseId(1);
        course.setUserId(1L);
        course.setCourseIntro("ffff");
       courseService.updateCourse(course);
    }

}
