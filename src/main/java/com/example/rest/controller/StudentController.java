package com.example.rest.controller;

import com.example.rest.entity.StudentEntity;
import com.example.rest.model.dto.StudentLoginDto;
import com.example.rest.model.dto.StudentSignUpDto;
import com.example.rest.model.dto.StudentResponse;
import com.example.rest.model.dto.UpdateStudentDto;
import com.example.rest.util.StudentRepo;
import com.example.rest.util.StudentValidator;
import com.example.rest.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.rest.util.Utils.generateRandomUuid;
import static com.example.rest.util.transormation.StudentTransformation.mapFromStudentDtoToStudentEntity;

@RestController
@RequestMapping("student")
public class StudentController {

    @PostMapping("signup")
    public ResponseEntity<String> signUpApi(@RequestBody StudentSignUpDto studentSignUpDto) {
        if (!StudentValidator.isEmailValidated(studentSignUpDto) || !StudentValidator.isNameValidated(studentSignUpDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signup details");
        }

        Optional<StudentEntity> optionalRegisteredStudents = StudentRepo.getStudentMap().values().stream()
                .filter(registeredStudent ->registeredStudent.getEmail().equalsIgnoreCase(studentSignUpDto.getEmail()))
                .findFirst();

        if (optionalRegisteredStudents.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student already exists");

        }

        StudentEntity studentEntity = mapFromStudentDtoToStudentEntity(studentSignUpDto);
        StudentRepo.insertStudent(generateRandomUuid(),studentEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully");

    }

    @PostMapping("login")
    public ResponseEntity<String> loginApi(@RequestBody StudentLoginDto studentLoginDto) {
        Optional<StudentEntity> optionalStudentEntity = StudentRepo.getStudentMap().values().stream()
                .filter(student -> StudentValidator.isEmailMatched(student.getEmail(),studentLoginDto.getEmail())
                        && StudentValidator.isPasswordMatched(student.getPassword(),studentLoginDto.getPassword()))
                .findFirst();

        if (optionalStudentEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        StudentRepo.login(optionalStudentEntity.get().getId());
        StudentRepo.insertStudent(optionalStudentEntity.get().getId(),optionalStudentEntity.get());
        return ResponseEntity.ok("Login successful");
    }

    @DeleteMapping("logout/{email}")
    public ResponseEntity<String> logoutApi(@PathVariable String id) {
        if (!StudentRepo.isLoggedIn(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Student is not logged in.");
        }
        StudentRepo.logout(id);
        return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully");
    }

    @GetMapping("findAll")
    public ResponseEntity<List<StudentResponse>> findAllApi(@RequestParam String id) {
        if (!StudentRepo.isLoggedIn(id)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<StudentResponse> result = StudentRepo.getStudentMap().values().stream()
                .map(student -> new StudentResponse(student.getName(), student.getEmail(),
                        student.getAge(), student.getAddress()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("findFirst")
    public ResponseEntity<StudentResponse> findFirstApi(@RequestParam String id) {
        if(!StudentRepo.isLoggedIn(id)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return StudentRepo.getStudentMap().values().stream()
                .map(studentSignUpDto ->
                        ResponseEntity.ok(new StudentResponse(studentSignUpDto.getName(),
                        studentSignUpDto.getEmail(),studentSignUpDto.getAge(),
                        studentSignUpDto.getAddress()))).findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("findByName")
    public ResponseEntity<StudentResponse> findByNameApi(@RequestParam String name,@RequestParam String id) {
        if (!StudentRepo.isLoggedIn(id)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return StudentRepo.getStudentMap().values().stream()
                .filter(student -> StudentValidator.isNameMatched(student.getName(), name))
                .findFirst()
                .map(student -> ResponseEntity.ok(new StudentResponse(student.getName(), student.getEmail(), student.getAge(), student.getAddress())))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("findByAge")
    public ResponseEntity<List<StudentResponse>> findByAgeApi(@RequestParam int age,@RequestParam String id) {
        if (!StudentRepo.isLoggedIn(id)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<StudentResponse> responses=  StudentRepo.getStudentMap().values().stream()
                .filter(student -> StudentValidator.isAgeMatched(student.getAge(), age))
                .map(student -> new StudentResponse(
                        student.getName(),
                        student.getEmail(),
                        student.getAge(),
                        student.getAddress())).toList();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("deleteByNameAndAge")
    public ResponseEntity<String> deleteByNameAndAge(@RequestParam String name, @RequestParam int age,@RequestParam String id) {
        if (!StudentRepo.isLoggedIn(id)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        boolean isRemoved = StudentRepo.getStudentMap().values().removeIf(student -> StudentValidator.isNameMatched(student.getName(), name) &&
                StudentValidator.isAgeMatched(student.getAge(), age));
        if (!isRemoved) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("students are not found");
        }
        return ResponseEntity.ok("Students with name '" + name + "' and age " + age + " were deleted");

    }
    //TODO: i should change the dataStructure to solve it
    @PutMapping("update")
    public ResponseEntity<String> updateStudentInfoApi(@RequestBody UpdateStudentDto updateStudentDto, @RequestParam String id) {
        Optional<StudentEntity>optionalStudentEntity = StudentRepo.getStudentMap().values().stream()
                .filter(student -> StudentValidator.isMatched(id, student.getId()))
                .findFirst();
        if (optionalStudentEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " not found");
        }
        StudentRepo.updateStudent(updateStudentDto);
        return ResponseEntity.status(HttpStatus.OK).body("Student with id " + id + " updated successfully");
    }

    //TODO: Admin should do this (try to solve it)
    @DeleteMapping("deleteAll")
    public ResponseEntity<String> deleteAllApi() {
        StudentRepo.deleteAll();
        return ResponseEntity.ok("All students deleted");
    }

    @PostMapping("increaseAge")
    public ResponseEntity<StudentResponse> increaseStudentAgeByOneApi(@RequestBody StudentSignUpDto studentSignUpDto, @RequestHeader String id) {
        if (!StudentRepo.isLoggedIn(id)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        int newAge = Utils.increaseAgeByOneYear(studentSignUpDto.getAge());
        StudentResponse response = new StudentResponse(studentSignUpDto.getName(), studentSignUpDto.getEmail(), newAge, studentSignUpDto.getAddress());
        return ResponseEntity.ok(response);
    }


}
