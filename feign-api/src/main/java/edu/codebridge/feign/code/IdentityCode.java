package edu.codebridge.feign.code;

/**
 * This class is the const to denote the type of user(teacher student admin etc.)
 */
public class IdentityCode {
    public static final Integer STUDENT = 0;
    public static final Integer TEACHER = 1;
    public static final Integer ADMINISTRATOR = 2;
    public static final Integer UNAUTHORIZED_TEACHER = 3;
}
