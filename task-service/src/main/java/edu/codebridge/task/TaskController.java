package edu.codebridge.task;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks/task")
public class TaskController {
    @GetMapping("/test")
    public String test(){
    return "The server is running!";
    }
}
