package edu.codebridge.course.common;

import java.util.Random;

public class CourseNumberGenerator {



    private static final int COURSE_NUMBER_LENGTH = 5;

    private static final Random RANDOM = new Random();

    public static int generateCourseNumber() {
        int courseNumber = 0;
        for (int i = 0; i < COURSE_NUMBER_LENGTH; i++) {
            int digit = RANDOM.nextInt(10);
            courseNumber = courseNumber * 10 + digit;
        }
        return courseNumber;
    }




}
