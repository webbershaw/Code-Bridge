package edu.codebridge.relationship;


import edu.codebridge.feign.entity.ClassTask;
import edu.codebridge.relationship.mapper.RelationshipMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ClassTaskMapperTest {
    @Autowired
    RelationshipMapper relationshipMapper;
    @Test
    public void insertClassTaskTest(){
        ClassTask classTask = new ClassTask();
        classTask.setClassId(1);
        classTask.setTaskId(9);
        classTask.setStartTime(LocalDateTime.now());
        LocalDateTime endTime = LocalDateTime.of(2025,10,1,23,59);
        classTask.setEndTime(endTime);
        classTask.setInvisible((short) 2);
        classTask.setAccessible((short) 1);
        classTask.setWeight(11.0);
        classTask.setResubmit(114);
        classTask.setCheckAfterSubmit((short) 120);
        classTask.setCorrectionMode((short) 1);
        System.out.println(classTask);
        relationshipMapper.insertClassTask(classTask);
    }

    @Test
    public void queryByConditionTest(){
        ClassTask classTask = new ClassTask();
        classTask.setClassId(66);
        List<ClassTask> classTasks = relationshipMapper.queryClassTasksByCondition(classTask);
        System.out.println(classTasks);
        for (ClassTask classTask1:classTasks){
            System.out.println(classTask1);
        }
    }

}
