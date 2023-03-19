package edu.codebridge.user.util;


public interface SenderUtil {

    public static final int REGISTER_TEXT_MESSAGE_CODE=1;
    public static final int LOGIN_TEXT_MESSAGE_CODE=0;
    public static final int FORGET_PWD_MESSAGE_CODE=2;
    boolean sendEmail(String to,String title,String text);

    boolean sendTextMessage(String to,int code,int type);

//    boolean sendRegisterTextMessage(String to,String code);


}
