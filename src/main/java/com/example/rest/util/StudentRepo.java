package com.example.rest.util;

import com.example.rest.entity.StudentEntity;
import com.example.rest.model.dto.UpdateStudentDto;

import java.util.*;

import static com.example.rest.util.Utils.generateRandomUuid;

public class StudentRepo {

    private static final Map<String, StudentEntity> studentMap = new LinkedHashMap<>();

    private StudentRepo() {
        throw new IllegalStateException("StudentRepo is Utility class");
    }

    public static boolean isLoggedIn(final String id) {
        return studentMap.containsKey(id);
    }

    public static void insertStudent(final String id,final StudentEntity studentEntity) {
        studentMap.put(id,studentEntity);
    }

    public static void updateStudent(UpdateStudentDto updateStudentDto) {
        StudentEntity redundantStudent = new StudentEntity();
        redundantStudent.setName(updateStudentDto.getName());
        redundantStudent.setEmail(updateStudentDto.getEmail());
        redundantStudent.setAge(updateStudentDto.getAge());
        redundantStudent.setAddress(updateStudentDto.getAddress());
        redundantStudent.setPassword(updateStudentDto.getPassword());

    }

    public static void deleteAll() {
        studentMap.clear();
    }

    public static void login(final String id) {
        studentMap.get(id).setLoggedIn(false);
    }

    public static void logout(final String id) {
        studentMap.get(id).setLoggedIn(false);
    }

    public static Map<String, StudentEntity> getStudentMap() {
        return studentMap;
    }
}
