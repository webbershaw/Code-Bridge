package edu.codebridge.task.mapper;

import edu.codebridge.feign.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;


@Mapper
@Repository
public interface ResourceMapper {
    /**
     * 根据resource_id查询resource
     * @param resourceId
     * @return List<Resource>
     */
    @Select("select * from cb_task.resource where resource_id = #{resourceId} and deleted=0")
    public Resource queryResourceByResourceId(Integer resourceId);

    /**
     * 根据条件查询Resource
     * @param resource
     * @return List<Resource>
     */
    public List<Resource> queryResourceByCondition(Resource resource);

    @Insert("insert into cb_task.resource " +
            "(resource_id, resource_type, title, content, resource_url, classification_id)" +
            "value " +
            "(#{resourceId}, #{resourceType}, #{title}, #{content}, #{resourceUrl}, #{classificationId})")
    public Boolean insertResource(Resource resource);

    /**
     * 根据classification_id 查询
     * @param classificationId
     * @return List<Resource>
     */
    @Select("select * from cb_task.resource where classification_id = #{classificationId}")
    public List<Resource> queryResourceByClassificationId(Integer classificationId);

    /**
     * 更新Resource数据表
     * @param resource
     * @return Boolean
     */
    public Boolean updateResource(Resource resource);

}


