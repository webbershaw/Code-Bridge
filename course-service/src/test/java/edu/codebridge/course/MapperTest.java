package edu.codebridge.course;

import edu.codebridge.course.mapper.ClassMapper;
import edu.codebridge.course.mapper.CourseMapper;

import edu.codebridge.course.mapper.ModelMapper;
import edu.codebridge.course.service.ClassService;
import edu.codebridge.course.service.ModelService;
import edu.codebridge.feign.client.UserClient;
import edu.codebridge.feign.entity.Class;
import edu.codebridge.feign.entity.Course;
import edu.codebridge.feign.entity.Model;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class MapperTest {
    @Autowired
    private ClassService classService;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ModelMapper modelMapper;
@Autowired
    private UserClient userClient;

    @Test
    public void  sssStest(){
//        Class clazz = classMapper.getClassById(1);
//        Integer courseId = clazz.getCourseId();
//        Course course = courseMapper.getCourseById(courseId);
//        clazz.setCourse(course);
//        List<Integer> objects = new ArrayList<>();
//        objects.add(1);
//        objects.add(2);
//        List<Class> classes = classMapper.queryClassByIds(objects);
//        System.out.println(classes.toString());
//        Class aClass = new Class();
//        aClass.setClassId(1002);
//        aClass.setCourseId(1);
//        aClass.setUserId(2L);
//        aClass.setClassName("zzza");
//        classMapper.updateClassById(aClass);
//        ArrayList<Integer> objects = new ArrayList<>();
//        objects.add(1);
//        objects.add(2);
//        classMapper.queryUerIdsByClassIds(objects);
//        List<Class> classes = courseMapper.queryClassById(1);
//        System.out.println(classes.toString());
//        courseMapper.queryCoursesByUserId(1l);
//        Model model = new Model();
//        model.setModelName("hh");
//
//        modelMapper.addModel(model);
//
//        List<Model> models = modelMapper.queryModelByUserId(1l);
//        System.out.println(models.toString());

//        Model model = modelMapper.getModelByIdNoDeleted(1);
//        model.setModelId(null);
//        model.setIsPublic((short)0);
//        model.setCreatedTime(null);
//        modelMapper.addModel(model);


//        Course course = courseMapper.getCourseById(1);
//        System.out.println(course.toString());
//        Course course = new Course();
//        course.setUserId(1l);
//        courseMapper.addCourse(course);
//        List<Course> courses = courseMapper.getAllCourses();
//        courses.stream()
//                .peek(course -> course.setUser(userClient.queryById(course.getUserId())))
//                .collect(Collectors.toList());
//        System.out.println(courses.toString());

//        courseMapper.updateCourseById(course);
//        Model model = new Model();
//        model.setIsPublic((short)1);
//        model.setUserId(1l);
//
//        modelMapper.addModel(model);


        Class classByIdAndNoDeleted = classMapper.getClassByIdAndNoDeleted(1);
        System.out.println(classByIdAndNoDeleted);

    }

}
