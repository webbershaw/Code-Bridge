//package edu.codebridge.user.util.impl;
//
//import com.microsoft.azure.storage.blob.CloudBlobContainer;
//import com.microsoft.azure.storage.blob.CloudBlockBlob;
//import edu.hunau.config.StorageConfig;
//import edu.hunau.utils.BlobHelper;
//import edu.hunau.utils.FileUploadUtil;
//import edu.hunau.utils.MyUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import static com.google.common.io.Files.getFileExtension;
//
//
//@Component
//public class FileUploadUtilImpl implements FileUploadUtil {
//    @Autowired
//    private StorageConfig storageConfig;
//    public String uploadFile(MultipartFile file){
//        try {
//            if (file != null) {
//                //获取或创建container
//                CloudBlobContainer blobContainer = BlobHelper.getBlobContainer("kitty", storageConfig);
//                if (!file.isEmpty()) {
//                    try {
//
//                        //拼装blob的名称(前缀名称+文件的md5值+文件扩展名称)
//                        String checkSum = MyUtils.getMD5(file.getInputStream());
//                        String fileExtension = getFileExtension(file.getOriginalFilename()).toLowerCase();
//                        String preName = getBlobPreName(0, false).toLowerCase();
//                        String blobName = preName + checkSum + "." +fileExtension;
//                        //设置文件类型，并且上传到azure blob
//                        CloudBlockBlob blob = blobContainer.getBlockBlobReference(blobName);
//                        blob.getProperties().setContentType(file.getContentType());
//                        blob.upload(file.getInputStream(), file.getSize());
//                        //将上传后的图片URL返回
//                        return blob.getUri().toString();
//                    } catch (Exception e) {
//                        System.out.println(e);
////                        log.error("upload azure oss error：{}", e);
//                    }
//                }
//            }
////            }
//        } catch (Exception e) {
//            System.out.println(e);
////            log.error("upload azure oss error：{}", e);
//
//        }
//        return null;
//    }
//    private String getBlobPreName(int fileType, Boolean thumbnail)
//    {
//        String afterName = "";
//        if (thumbnail)
//        {
//            afterName = "thumbnail/";
//        }
//
//        switch (fileType)
//        {
//            case 1:
//                return "11/" + afterName;
//            case 2:
//                return "22/" + afterName;
//            case 3:
//                return "33/" + afterName;
//            case 4:
//                return "44/" + afterName;
//            case 5:
//                return "55/" + afterName;
//            default :
//                return "";
//        }
//    }
//}
