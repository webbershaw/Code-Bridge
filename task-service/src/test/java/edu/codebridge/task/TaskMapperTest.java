package edu.codebridge.task;


import edu.codebridge.feign.entity.Resource;
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
        task.setModelId(0);
        taskMapper.insertTask(task);
    }


    @Test
    public void queryTaskByConditionTest(){
    //查出该任务详细信息,任务的所有资源
    Task task = new Task();
    task.setTaskId(7);
    task.setModelId(2);
    task.setTaskName("高等小学");
    task.setDeleted((short)0);
    task.setTaskType((short)3);
 taskMapper.insertTask(task);
}


    @Test
    public void updateTaskTest(){
        Task task = new Task();

        task.setTaskId(6);
        taskMapper.updateTask(task);
    }

}
