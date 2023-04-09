package edu.codebridge.task;


import edu.codebridge.feign.entity.Task;
import edu.codebridge.task.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;

    @Test
    public void InsertTaskTest(){
        Task task = new Task();
        task.setTaskId(1);
        task.setTaskName("高等数学");
        task.setTaskType((short) 3);
        task.setCourseId(2);
        taskMapper.InsertTask(task);
    }

@Transactional
    @Test
    public void queryTaskByConditionTest(){
    List<Task> tasks = taskMapper.queryTaskByCourseId(2);
    System.out.println(tasks);
}


    @Test
    public void updateTaskTest(){
        Task task = new Task();
        task.setCourseId(2);
        task.setTaskId(6);
        taskMapper.updateTask(task);
    }

}
