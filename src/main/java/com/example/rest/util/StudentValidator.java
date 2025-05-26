package com.example.rest.util;

import com.example.rest.model.dto.StudentSignUpDto;

public class StudentValidator {

    private StudentValidator() {
        throw new IllegalStateException("StudentValidator is Utility class");
    }

    public static boolean isEmailMatched(String sourceEmail, String  targetEmail) {
        return sourceEmail.equalsIgnoreCase(targetEmail);
    }

    public static boolean isNameMatched(String sourceName,String  targetName) {
        return sourceName.equalsIgnoreCase(targetName);
    }

    public static boolean isPasswordMatched(String sourcePassword,String  targetPassword) {
        return sourcePassword.equals(targetPassword);
    }

    public static boolean isMatched(String sourceId, String targetId) {
        return sourceId.equals(targetId);
    }

    public static boolean isAgeMatched(int sourceAge, int targetAge) {
        return  sourceAge == targetAge;
    }

    public static boolean isEmailValidated(final StudentSignUpDto studentSignUpDto) {
        return studentSignUpDto.getEmail().contains("@");
    }

    public static boolean isNameValidated(final StudentSignUpDto studentSignUpDto) {
        return !studentSignUpDto.getName().isEmpty() &&
                studentSignUpDto.getName().length() >= 2;
    }
}
