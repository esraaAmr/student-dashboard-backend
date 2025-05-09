package com.example.rest.controller;

import com.example.rest.model.dto.StudentLoginDto;
import com.example.rest.model.dto.StudentSignUpDto;
import com.example.rest.model.dto.StudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("student")
public class StudentController {

    private final Set<StudentSignUpDto> students;
    private final Set<String> loggedInUsers;


    public StudentController() {
        students = new LinkedHashSet<>();
        loggedInUsers = new HashSet<>();
    }

    @PostMapping("signup")
    public void signUpApi(@RequestBody StudentSignUpDto studentSignUpDto) {
        if(studentSignUpDto.getEmail().contains("@") &&
                !studentSignUpDto.getName().isEmpty()&&
                studentSignUpDto.getName().length()>=2
        ){
            students.add(studentSignUpDto);
        }else{
            System.out.println("Invalid Email");
        }
    }

    // TODO: Prevent hit all APIs if the students is not logged in
    @PostMapping("login")
    public void loginApi(@RequestBody StudentLoginDto  studentLoginDto) {
        students.stream()
                .filter(student -> student.getEmail().equalsIgnoreCase(studentLoginDto.getEmail())
                && student.getPassword().equalsIgnoreCase(studentLoginDto.getPassword()))
                .findFirst()
                .ifPresentOrElse(student -> {
                    loggedInUsers.add(student.getEmail());
                    System.out.println("Student " + student.getName() + " is successfully logged in");
                },()->System.out.println("Student not found"));
    }

    @DeleteMapping("logout")
    public void logoutApi(@RequestBody StudentLoginDto studentLoginDto) {
        String email = studentLoginDto.getEmail().toLowerCase();
        if (loggedInUsers.contains(email)) {
            loggedInUsers.remove(email);
            System.out.println("Student with email " + email + " has been logged out");
        } else {
            System.out.println("Student is not logged in.");
        }
    }
    @GetMapping("findAll")
    public List<StudentResponse> findAllApi() {
        return students.stream()
                .map(studentSignUpDto ->  new StudentResponse(studentSignUpDto.getName(),
                        studentSignUpDto.getEmail(),studentSignUpDto.getAge(),
                        studentSignUpDto.getAddress())).toList();
    }

    @GetMapping("findFirst")
    public StudentResponse findFirstApi() {
        return students.stream()
                .map(studentSignUpDto ->  new StudentResponse(studentSignUpDto.getName(),
                        studentSignUpDto.getEmail(),studentSignUpDto.getAge(),
                        studentSignUpDto.getAddress())).findFirst().orElse(null);
    }

    @GetMapping("findByName")
    public ResponseEntity<StudentResponse> findByNameApi(@RequestParam String name) {
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(student -> {
                    System.out.println("Student with name " + name + " has been found");
                    StudentResponse response = new StudentResponse(student.getName(),
                            student.getEmail(),
                            student.getAge(),
                            student.getAddress());
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                });
    }

    @GetMapping("findByAge")
    public List<StudentResponse> findByAgeApi(@RequestParam int age) {
        return students.stream()
                .filter(student -> student.getAge() == age)
                .map(student -> new StudentResponse(
                        student.getName(),
                        student.getEmail(),
                        student.getAge(),
                        student.getAddress())).toList();
    }

    @DeleteMapping("deleteByNameAndAge")
    public ResponseEntity<String> deleteByNameAndAge(@RequestParam String name, @RequestParam int age) {
        boolean removed = students.removeIf(student -> student.getName().equalsIgnoreCase(name)
                && student.getAge() == age);
        if (removed) {
            return ResponseEntity.ok("Students with name '" + name + "' and age " + age + " were deleted.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("update")
    public ResponseEntity<String> updateStudentInfo(@RequestBody StudentSignUpDto updatedStudentDto) {
        String email = updatedStudentDto.getEmail().toLowerCase();

        for (StudentSignUpDto student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                student.setName(updatedStudentDto.getName());
                student.setAge(updatedStudentDto.getAge());
                student.setAddress(updatedStudentDto.getAddress());
                student.setPassword(updatedStudentDto.getPassword());
                return ResponseEntity.ok("Student information updated successfully.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with email " + email + " not found.");
    }

    @DeleteMapping("deleteAll")
    public void deleteAllApi() {
        students.clear();
    }

    @GetMapping("increaseAge")
    public StudentResponse increaseStudentAgeByOneApi(@RequestBody StudentSignUpDto studentSignUpDto) {
        int newAge= increaseAgeByOneYear(studentSignUpDto.getAge());
        return new StudentResponse(studentSignUpDto.getName(),studentSignUpDto.getEmail(),newAge, studentSignUpDto.getAddress());
    }

    public int increaseAgeByOneYear(final int age) {
        return age + 1;
    }

}
