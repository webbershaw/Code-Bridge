package edu.codebridge.user.mapper;


import edu.codebridge.feign.entity.School;
import edu.codebridge.feign.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from cb_user.user where id =#{id}")
    public User queryById(Integer id);


    @Select("select * from cb_user.user where tel = #{tel} and deleted = 0")
    public User queryByTel(Integer tel);

    public User queryByCondition(User user);

    public List<User> queryUsersByCondition(User user);

    public User updateByCondition(User user);

    public Integer updateUsersByCondition(User user);

    public School insertSchool(School school);





}
