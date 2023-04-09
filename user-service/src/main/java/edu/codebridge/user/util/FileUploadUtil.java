package edu.codebridge.user.util;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadUtil {
    String uploadFile(MultipartFile file);
}
