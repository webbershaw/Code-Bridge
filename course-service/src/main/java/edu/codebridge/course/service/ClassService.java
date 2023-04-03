package edu.codebridge.course.service;

import edu.codebridge.feign.entity.Class;
import edu.codebridge.feign.entity.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClassService {
        /**
         * 新增班级
         * @param clazz
         * @param request
         * @return
         */
        Result  addClass(Class clazz, HttpServletRequest request);


        /**
         * 修改班级
         * @param clazz
         * @param request
         * @return
         */
        Result  updateClassById(Class clazz,HttpServletRequest request);

        /**
         * 根据班级id返回userId
         * @param classId
         * @param request
         * @return
         */
        Result  queryUserIdByClassId(Integer classId,HttpServletRequest request);

        /**
         * 根据班级ids得到UserIds
         * @param classIds
         * @param request
         * @return
         */
    Result queryUserIdsByClassIds(List<Integer> classIds,HttpServletRequest request);


    Result queryClassByClassId(Integer classId,HttpServletRequest request);

    Result queryClassesByUserId(HttpServletRequest request);

    Result queryClassesByStudentUserId(HttpServletRequest request);

}
