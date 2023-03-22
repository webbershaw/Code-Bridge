package edu.codebridge.user.service;

import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    public List<Integer> getUsersByUserIds(List<Integer> userIds);

    /**
     * Get user by attributes in the user object, but return only one user object.
     * @param user
     * @return
     */
    public User getUserByCondition(User user);


    /**
     * Get user by attributes in the user object, but return only one user object.
     * @param user
     * @return
     */
    public List<User> getUsersByCondition(User user);

   public User queryUserById(Integer id);



   public List<User> queryUsersByIds(List<Long> ids);



    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/
    public Result register(User user, HttpServletRequest request);

    public User loginByPwd(User user);
    public Result loginByVerifyCode(User user,HttpServletRequest request);

    public Result attachToSchool(User user);

    public Result sendVerifyCode(HttpServletRequest request,String tel,Integer type);




}
