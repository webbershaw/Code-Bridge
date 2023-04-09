package edu.codebridge.user.mapper;


import edu.codebridge.feign.entity.School;
import edu.codebridge.feign.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select id, username, email, deleted, avatar_url, identity, intro, certified, user.school_id, person_id, name, tel,school_name from cb_user.user,cb_user.school where id =#{id} and user.school_id=school.school_id")
    public User queryById(Long id);


    @Select("select * from cb_user.user where tel = #{tel} and deleted = 0")
    public User queryByTel(String tel);


    public User queryByCondition(User user);

    public List<User> queryUsersByCondition(User user);

    public Integer updateByCondition(User user);

    @Insert("insert into school (image_path, school_name, school_intro) values" +
            "(#{imagePath},#{schoolName},#{schoolIntro}) ")
    public School insertSchool(School school);


    public boolean insertUser(User user);

    public List<User> queryUsersByIds(List<Long> ids);

    @Select("select * from school where school_id != 0")
    public List<School> queryAllSchools();

    @Select("select * from cb_user.school where school_id = #{schoolId}")
    public School querySchoolBySchoolId(Integer schoolId);






}
