package edu.codebridge.task;


import edu.codebridge.feign.entity.Resource;
import edu.codebridge.task.mapper.ResourceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResourceMapperTest {

    @Autowired
    ResourceMapper resourceMapper;

    @Test
    public void insertResourceTest(){
        Resource resource = new Resource();
        resource.setResourceId(1);
        resource.setResourceType((short) 1);
        resource.setResourceUrl("www.bing.com");
        resource.setTitle("离散数学作业");

        resourceMapper.insertResource(resource);
    }

    @Test
    public void queryResourceByResourceIdTest(){
        resourceMapper.queryResourceByResourceId(1);
    }

    @Test
    public void queryResourceByConditionTest(){
        Resource resource = new Resource();
        resource.setResourceId(0);
        resource.setResourceType((short) 2);
        resource.setTitle("离散数学作业");
        resource.setResourceUrl("www.bing.com");
        resource.setClassificationId(11);
        resourceMapper.queryResourceByCondition(resource);
    }

    @Test
    public void updateResourceTest(){
        Resource resource = new Resource();
        resource.setClassificationId(11);
        resource.setResourceType((short) 2);
        Boolean aBoolean = resourceMapper.updateResource(resource);
        System.out.println(aBoolean);
    }
}
