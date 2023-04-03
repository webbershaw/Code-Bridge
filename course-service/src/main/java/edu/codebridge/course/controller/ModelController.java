package edu.codebridge.course.controller;

import edu.codebridge.course.mapper.ModelMapper;
import edu.codebridge.course.service.ModelService;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Model;
import edu.codebridge.feign.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("courses/model")
public class ModelController {
    @Autowired
    private ModelService modelService;
    @PostMapping
    public Result addModel(Model model, HttpServletRequest request){
        try {
            return  modelService.addModel(model,request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }
    }


    public Result copyModel(Integer modelId,HttpServletRequest request) {
        try {
            return  modelService.copyModelByModelId(modelId,request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }
    }
    @GetMapping("/ff")
    public Result queryAllModels(HttpServletRequest request){
        try {
            return  modelService.queryAllModel(request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }
    }
    @GetMapping("/aa")

    public Result queryModelsByKeyWord(HttpServletRequest request,String name){
        try {
            return  modelService.queryModelByKeyWord(name,request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }

    }
    public Result queryModelsByUserId(Long userId,HttpServletRequest request){
        try {
            return  modelService.queryModelByUserId(request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }
    }







}
