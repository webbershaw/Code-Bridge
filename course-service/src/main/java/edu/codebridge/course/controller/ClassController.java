package edu.codebridge.course.controller;

import edu.codebridge.course.mapper.ClassMapper;
import edu.codebridge.course.service.ClassService;
import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Class;
import edu.codebridge.feign.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("course/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    /**
     * 添加分班
     * @param clazz
     * @param request
     * @return
     */
    @PostMapping
    public Result addClass(Class clazz, HttpServletRequest request){
        try {
            return classService.addClass(clazz,request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }
    }

    /**
     * 更新分班
     * @param clazz
     * @param request
     * @return
     */
    @PutMapping
    public Result updateClass(Class clazz,HttpServletRequest request){
        try {
            return classService.updateClassById(clazz,request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"修改失败，请联系管理员");
        }
    }

    /**
     * 得到userId通过classId
     * @param classId
     * @param request
     * @return
     */
    @GetMapping
    public Result queryUserIdByClassId(Integer classId,HttpServletRequest request){
        try {
            return  classService.queryUserIdByClassId(classId,request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }

    }

    /**
     * 得到userIds通过classIds
     * @param classIds
     * @param request
     * @return
     */
//    public Result queryUserIdsByClassIds(List<Integer> classIds,HttpServletRequest request){
//        try {
//            return classService.queryUserIdsByClassIds(classIds,request);
//        } catch (Exception e) {
//            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
//        }
//
//    }
    @GetMapping("{classId}")
    public  Result queryClassByClassId(@PathVariable Integer classId,HttpServletRequest request){

        try {
            return classService.queryClassByClassId(classId,request);
        } catch (Exception e) {
            return new Result(ErrorCode.ERR,null,"添加失败，请联系管理员");
        }

    }





}
