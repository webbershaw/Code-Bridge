package edu.codebridge.course.mapper;

import edu.codebridge.feign.entity.Model;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ModelMapper {
    /**
     * 通过modelId查询未被删除的model
     * @param modelId
     * @return
     */
    @Select("select * from model where model_id=#{modelId} and deleted=0")
    Model getModelByIdNoDeleted(Integer modelId);

    /**
     * 查询所有未被删除公开的model
     * @return
     */
    @Select("select * from model where deleted =0 and is_public=1")
    List<Model> queryAllModel();

    /**
     * 添加model
     * @param model
     */
    void addModel(Model model);

    /**
     * 软删除model通过model_id
     * @param id
     */
    @Delete("update model set deleted =1 where model_id=#{modelId} ")
    void  deleteModelById(Integer id);

    /**
     * 修改model
     * @param model
     */
    void updateModelById(Model model);

    /**
     * 查询mode通过关键词
     * @param name
     * @return
     */

    List<Model> queryByKeyWord(String name);


    /**
     * 查询models通过userId
     * @param userId
     * @return
     */
    List<Model>  queryModelByUserId(Long userId);










}
