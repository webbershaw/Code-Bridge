package edu.codebridge.user.controller;

import edu.codebridge.feign.code.ErrorCode;
import edu.codebridge.feign.entity.Result;
import edu.codebridge.user.controller.image.Data;
import edu.codebridge.user.controller.image.ImageReturn;
import edu.codebridge.feign.entity.User;
import edu.codebridge.user.service.UserService;
import edu.codebridge.user.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

//@Api(value = "微软云存储", description = "微软云存储")

/**
 * 将就一下吧
 * 这部分代码写的确实是翔山
 */
@RestController
@RequestMapping("/files")
@Slf4j
public class FileUploadController {



    @Autowired
    FileUploadUtil fileUploadUtil;
    @Autowired
    UserService userService;





    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST, consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result uploadAvatar(@RequestBody MultipartFile file, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return new Result(ErrorCode.NOT_LOGIN,null,"用户登录已失效，请重新登录");
        }

        if (!(file.getContentType().toLowerCase().equals("image/jpg")
                || file.getContentType().toLowerCase().equals("image/jpeg")
                || file.getContentType().toLowerCase().equals("image/png"))) {
            return new Result(ErrorCode.ERR,null,"头像上传失败,仅支持jpg,png或jpeg格式的图片");
        }
        String url = fileUploadUtil.uploadFile(file);
        if(url!=null){
            user.setAvatarUrl(url);
            userService.updateUser(user);
            request.getSession().setAttribute("user",user);
            return new Result(ErrorCode.OK,url,"头像上传成功");
        }
        return new Result(ErrorCode.ERR,null,"头像上传失败");


    }
//    @PostMapping("/uploadImage")
    @RequestMapping(value = "/image", method = RequestMethod.POST, consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Object uploadImage(@RequestBody MultipartFile file, HttpServletRequest request) {
        Object user1 = request.getSession().getAttribute("user");
        if(user1==null){
            return new ImageReturn(1,"登录已过期，不允许进行此操作");
        }


        if (!(file.getContentType().toLowerCase().equals("image/jpg")
                || file.getContentType().toLowerCase().equals("image/jpeg")
                || file.getContentType().toLowerCase().equals("image/png"))) {
            return new ImageReturn(1,"图片上传失败,仅支持jpg,png或jpeg格式的图片");
        }
        String url = fileUploadUtil.uploadFile(file);
        if(url!=null){
            return new ImageReturn(0,new Data(url,"[图片]",null));
        }
        return new ImageReturn(1,"图片上传失败");


    }



}