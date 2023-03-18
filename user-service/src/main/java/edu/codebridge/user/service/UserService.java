package edu.codebridge.user.service;

import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;

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



    /*-----------------Division of ↓↓↓↓ public API ↓↓↓↓ and ↑↑↑↑ private API ↑↑↑↑------------------------*/
    public Result register(User user);

    public User loginByPwd(User user);
    public Result loginByVerifyCode(User user);

    public Result attachToSchool(User user);




}
