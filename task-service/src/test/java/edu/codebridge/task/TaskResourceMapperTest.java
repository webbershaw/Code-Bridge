package edu.codebridge.task;


import edu.codebridge.feign.entity.TaskResource;
import edu.codebridge.task.mapper.TaskResourceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class TaskResourceMapperTest {
    @Autowired
    TaskResourceMapper taskResourceMapper;

    @Test
    public void insertTaskResourceTest(){
        TaskResource taskResource = new TaskResource();
        taskResource.setTaskId(1);
        taskResource.setResourceId(2);
        taskResource.setScore(98.5);
        taskResourceMapper.insertTaskResource(taskResource);
    }

    @Test
    public void updateTaskResourceTest(){
        TaskResource taskResource = new TaskResource();
        taskResource.setResourceId(6);
        taskResource.setTaskId(6);
        taskResource.setScore(100.0);
        taskResourceMapper.updateTaskResource(taskResource);
    }

    @Test
    public void queryTaskResourceByConditionTest(){
        TaskResource taskResource = new TaskResource();
        taskResource.setResourceId(6);
        taskResource.setTaskId(6);
        taskResource.setScore(100.0);
        taskResourceMapper.queryTaskResourceByCondition(taskResource);
    }
}
