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
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserClient userClient;
    @Override
    public Result addModel(Model model, HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }

        modelMapper.addModel(model);
        return new Result(ErrorCode.OK,null,"新增模板成功！");


    }

    @Override
    public Result copyModelByModelId(Integer modelId,HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        Model model = modelMapper.getModelByIdNoDeleted(modelId);
        model.setModelId(null);
        model.setIsPublic((short)0);
        model.setCreatedTime(null);
        modelMapper.addModel(model);

        return new Result(ErrorCode.OK,model,"复制成功！");
    }

    @Override
    public Result queryAllModel(HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        List<Model> models = modelMapper.queryAllModel();
        models.stream().forEach(item->item.setUser(userClient.queryById(item.getUserId())));
        return new Result(ErrorCode.OK,models,"查询成功");
    }

    @Override
    public Result queryModelByKeyWord(String name, HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        List<Model> models = modelMapper.queryByKeyWord(name);

        models.stream().forEach(item->item.setUser(userClient.queryById(item.getUserId())));

        return new Result(ErrorCode.OK,models,"查询成功");
    }

    @Override
    public Result queryModelByUserId(Long userId,HttpServletRequest request) {
        if (Check.checkUser(request).getData()==null){
            return Check.checkUser(request);
        }
        User user =(User)Check.checkUser(request).getData();

        if(user.getIdentity()!= IdentityCode.TEACHER){
            return new Result(ErrorCode.PERMISSION_DENIED,null,"您的权限不足" );
        }
        List<Model> models = modelMapper.queryModelByUserId(userId);

        models.stream().forEach(item->item.setUser(userClient.queryById(item.getUserId())));
            return new Result(ErrorCode.OK,models,"查询成功！");
    }


}
