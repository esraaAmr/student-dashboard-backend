package com.example.rest.util;

import com.example.rest.entity.StudentEntity;
import com.example.rest.model.dto.StudentSignUpDto;
import com.example.rest.model.dto.UpdateStudentDto;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utils is Utility class");
    }

    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }

    public static int increaseAgeByOneYear(final int age) {
        return age + 1;
    }

}
