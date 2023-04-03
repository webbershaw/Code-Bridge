package edu.codebridge.course;

import edu.codebridge.feign.client.RelationshipClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RSR {
    @Autowired
    RelationshipClient relationshipClient;
    @Test
    public void  test(){
        List<Long> longs = relationshipClient.queryUserIdByClassId(1);
        System.out.println(longs);
    }
}
