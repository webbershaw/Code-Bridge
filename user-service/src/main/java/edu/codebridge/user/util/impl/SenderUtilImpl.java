package edu.codebridge.user.util.impl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import edu.codebridge.user.util.SenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SenderUtilImpl implements SenderUtil {
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public boolean sendEmail(String to, String title, String text) {
        try {


            int code = (int) (Math.random()*1000000+1);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject(title);
            simpleMailMessage.setText(text);
            simpleMailMessage.setFrom("wenboxiao@stu.hunau.edu.cn");
            simpleMailMessage.setTo(to);
            javaMailSender.send(simpleMailMessage);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean sendTextMessage(String to, int code,int type) {

                try{
                    Credential cred = new Credential("AKIDkGXXGjuoRouiFWWFhtoSpuoRmqeBRQ7X", "B4g15rMaXDcja9RSCfrmRpxnMfiUzECu");
                    // 实例化一个http选项，可选的，没有特殊需求可以跳过
                    HttpProfile httpProfile = new HttpProfile();
                    httpProfile.setEndpoint("sms.tencentcloudapi.com");
                    // 实例化一个client选项，可选的，没有特殊需求可以跳过
                    ClientProfile clientProfile = new ClientProfile();
                    clientProfile.setHttpProfile(httpProfile);
                    // 实例化要请求产品的client对象,clientProfile是可选的
                    SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
                    // 实例化一个请求对象,每个接口都会对应一个request对象
                    SendSmsRequest req = new SendSmsRequest();
                    String[] phoneNumberSet1 = {to};
                    req.setPhoneNumberSet(phoneNumberSet1);

                    req.setSmsSdkAppId("1400773584");
                    req.setSignName("灵犀小程序个人网");
                    String[] templateParamSet1 = null;
                    if(type==1) {
//                        req.setTemplateId("1657724");
                        req.setTemplateId("1735659");

                        templateParamSet1 = new String[]{Integer.toString(code), "3"};
                    }else if(type==0||type ==2){
                        req.setTemplateId("1735660");

                        templateParamSet1 = new String[]{Integer.toString(code)};
                    }
                    req.setTemplateParamSet(templateParamSet1);

                    // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
                    SendSmsResponse resp = client.SendSms(req);
                    // 输出json格式的字符串回包
                    System.out.println(SendSmsResponse.toJsonString(resp));
                    return true;
                } catch (TencentCloudSDKException e) {
                    return false;
                }


    }


}
