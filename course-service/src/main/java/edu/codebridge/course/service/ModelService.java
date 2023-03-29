package edu.codebridge.course.service;

import edu.codebridge.feign.entity.Model;
import edu.codebridge.feign.entity.Result;

import javax.servlet.http.HttpServletRequest;

public interface ModelService {

    Result  addModel(Model model, HttpServletRequest request);

    Result copyModelByModelId(Integer modelId,HttpServletRequest request);
    Result queryAllModel(HttpServletRequest request);

    Result queryModelByKeyWord(String name,HttpServletRequest request);

    Result queryModelByUserId(Long userId,HttpServletRequest request);
}
