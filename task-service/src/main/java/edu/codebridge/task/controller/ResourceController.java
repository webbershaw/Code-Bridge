package edu.codebridge.task.controller;

import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.feign.entity.StudentTaskResource;
import edu.codebridge.feign.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping
public class ResourceController {
    @PostMapping("/StudentTaskResource/")
    public Result completeResource(HttpServletRequest request, StudentTaskResource studentTaskResource){




        return  null;
    }

}
