package edu.codebridge.course.service.impl;

import edu.codebridge.course.common.Check;
import edu.codebridge.course.mapper.ModelMapper;
import edu.codebridge.course.service.ModelService;
import edu.codebridge.feign.client.UserClient;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.code.IdentityCode;
import edu.codebridge.feign.entity.Model;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserClient userClient;

    /**
     * 添加
     * @param model
     * @param request
     * @return
     */
    @Override
    public Result addModel(Model model, HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        //必须老师才能有权限
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //添加模块
        modelMapper.addModel(model);
        return new Result(ErrorCode.OK,null,"新增模板成功！");
    }

    /**
     * 复制模板
     * @param modelId
     * @param request
     * @return
     */
    @Override
    public Result copyModelByModelId(Integer modelId,HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        //身份需要为老师
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //复制别人的模板
        Model model = modelMapper.getModelByIdNoDeleted(modelId);
        model.setModelId(null);
        model.setIsPublic((short)0);
        model.setCreatedTime(null);
        modelMapper.addModel(model);

        return new Result(ErrorCode.OK,model,"复制成功！");
    }

    /**
     * 查询所有模板
     * @param request
     * @return
     */
    @Override
    public Result queryAllModel(HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //需要为老师
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //查询所有
        List<Model> models = modelMapper.queryAllModel();
        List<Long> userIds = new ArrayList<>();
        models.stream().forEach(item->{item.setUser(null);userIds.add(item.getUserId());});
        List<User> users = userClient.queryUsersByIds(userIds);
        models.stream().forEach(item->{users.stream().filter(u->u.getId().equals(item.getUserId())).findFirst().orElse(null);
        item.setUser(user);});
        return new Result(ErrorCode.OK,models,"查询成功");
    }

    /**
     * 通过关键词去查询
     * @param name
     * @param request
     * @return
     */
    @Override
    public Result queryModelByKeyWord(String name, HttpServletRequest request) {
        //登录
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();
        //需要是老师
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        //通过关键词查询
        List<Model> models = modelMapper.queryByKeyWord(name);
        List<Long> userIds = new ArrayList<>();
        models.stream().forEach(item->{item.setUser(null);userIds.add(item.getUserId());});
        List<User> users = userClient.queryUsersByIds(userIds);
        models.stream().forEach(item->{users.stream().filter(u->u.getId().equals(item.getUserId())).findFirst().orElse(null);
            item.setUser(user);});
        return new Result(ErrorCode.OK,models,"查询成功");
    }

    /**
     * 给老师使用
     * @param request
     * @return
     */
    @Override
    public Result queryModelByUserId(HttpServletRequest request) {
        //登录校验
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        //需要是老师
        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }

        List<Model> models = modelMapper.queryModelByUserId(user.getId());

        List<Long> userIds = new ArrayList<>();
        models.stream().forEach(item->{item.setUser(null);userIds.add(item.getUserId());});
        List<User> users = userClient.queryUsersByIds(userIds);
        models.stream().forEach(item->{users.stream().filter(u->u.getId().equals(item.getUserId())).findFirst().orElse(null);
            item.setUser(user);});
            return new Result(ErrorCode.OK,models,"查询成功！");
    }


}
