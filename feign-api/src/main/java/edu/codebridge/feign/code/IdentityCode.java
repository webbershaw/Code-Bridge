package edu.codebridge.feign.code;

/**
 * This class is the const to denote the type of user(teacher student admin etc.)
 */

public class IdentityCode {
    public static final Short STUDENT = 0;
    public static final Short TEACHER = 1;
    public static final Short ADMINISTRATOR = 2;
    public static final Short UNAUTHORIZED_TEACHER = 3;
}
