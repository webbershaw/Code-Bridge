package edu.codebridge.user;

import edu.codebridge.feign.client.RelationshipClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class FeignTest {
    @Autowired
    RelationshipClient relationshipClient;

    @Test
    public void RelationshipTest(){
        List<Integer> classids = new ArrayList<>();
        classids.add(1);
        classids.add(2);
        List<Long> longs = relationshipClient.queryUserIdByClassIds(classids);
        System.out.println(longs);
    }
}
