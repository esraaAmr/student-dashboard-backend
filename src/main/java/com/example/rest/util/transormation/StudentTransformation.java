package com.example.rest.util.transormation;

import com.example.rest.entity.StudentEntity;
import com.example.rest.model.dto.StudentSignUpDto;

import static com.example.rest.util.Utils.generateRandomUuid;

public class StudentTransformation {
    private StudentTransformation() {
        throw new IllegalStateException("StudentTransformation is Utility class");
    }

    public static StudentEntity mapFromStudentDtoToStudentEntity(StudentSignUpDto studentSignUpDto) {
        return new StudentEntity(generateRandomUuid(),studentSignUpDto.getName(),
                studentSignUpDto.getEmail(),studentSignUpDto.getAge(),
                studentSignUpDto.getAddress(),studentSignUpDto.getPassword(),false);
    }
}
