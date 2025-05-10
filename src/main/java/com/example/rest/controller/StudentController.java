package com.example.rest.controller;

import com.example.rest.model.dto.StudentLoginDto;
import com.example.rest.model.dto.StudentSignUpDto;
import com.example.rest.model.dto.StudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("student")
public class StudentController {

    private final Set<StudentSignUpDto> students;
    private final Set<String> loggedInUsers;

    public StudentController() {
        students = new LinkedHashSet<>();
        loggedInUsers = new HashSet<>();
    }

    private boolean isLoggedIn(String email) {
        return email != null && loggedInUsers.contains(email.toLowerCase());
    }

    @PostMapping("signup")
    public ResponseEntity<String> signUpApi(@RequestBody StudentSignUpDto studentSignUpDto) {
        if (studentSignUpDto.getEmail().contains("@") &&
                !studentSignUpDto.getName().isEmpty() &&
                studentSignUpDto.getName().length() >= 2) {

            boolean added = students.add(studentSignUpDto);
            if (added) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Student already exists");
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signup details");
        }
    }

    @PostMapping("login")
    public ResponseEntity<String> loginApi(@RequestBody StudentLoginDto studentLoginDto) {
        Optional<StudentSignUpDto> match = students.stream()
                .filter(student -> student.getEmail().equalsIgnoreCase(studentLoginDto.getEmail())
                        && student.getPassword().equals(studentLoginDto.getPassword()))
                .findFirst();

        if (match.isPresent()) {
            loggedInUsers.add(match.get().getEmail().toLowerCase());
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
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
    public ResponseEntity<List<StudentResponse>> findAllApi(@RequestHeader String email) {
        if (!isLoggedIn(email)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<StudentResponse> result = students.stream()
                .map(s -> new StudentResponse(s.getName(), s.getEmail(), s.getAge(), s.getAddress()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("findFirst")
    public ResponseEntity<StudentResponse> findFirstApi(@RequestHeader String email) {
        if(!isLoggedIn(email)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return students.stream()
                .map(studentSignUpDto ->  ResponseEntity.ok(new StudentResponse(studentSignUpDto.getName(),
                        studentSignUpDto.getEmail(),studentSignUpDto.getAge(),
                        studentSignUpDto.getAddress()))).findFirst().orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("findByName")
    public ResponseEntity<StudentResponse> findByNameApi(@RequestParam String name,@RequestHeader String email) {
        if (!isLoggedIn(email)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(s -> ResponseEntity.ok(new StudentResponse(s.getName(), s.getEmail(), s.getAge(), s.getAddress())))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("findByAge")
    public ResponseEntity<List<StudentResponse>> findByAgeApi(@RequestParam int age,@RequestHeader String email) {
        if (!isLoggedIn(email)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<StudentResponse> responses=  students.stream()
                .filter(student -> student.getAge() == age)
                .map(student -> new StudentResponse(
                        student.getName(),
                        student.getEmail(),
                        student.getAge(),
                        student.getAddress())).toList();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("deleteByNameAndAge")
    public ResponseEntity<String> deleteByNameAndAge(@RequestParam String name, @RequestParam int age,@RequestHeader String email) {
        if (!isLoggedIn(email)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        boolean removed = students.removeIf(student -> student.getName().equalsIgnoreCase(name)
                && student.getAge() == age);
        if (removed) {
            return ResponseEntity.ok("Students with name '" + name + "' and age " + age + " were deleted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("update")
    public ResponseEntity<String> updateStudentInfo(@RequestBody StudentSignUpDto updatedStudentDto,@RequestHeader String email) {
        if (!isLoggedIn(email)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String targetEmail  = updatedStudentDto.getEmail().toLowerCase();

        for (StudentSignUpDto student : students) {
            if (student.getEmail().equalsIgnoreCase(targetEmail )) {
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
    public ResponseEntity<String> deleteAllApi(@RequestHeader String email) {
        if (!isLoggedIn(email)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        students.clear();
        return ResponseEntity.ok("All students deleted");
    }

    @PostMapping("increaseAge")
    public ResponseEntity<StudentResponse> increaseStudentAgeByOneApi(@RequestBody StudentSignUpDto studentSignUpDto, @RequestHeader String email) {
        if (!isLoggedIn(email)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        int newAge = increaseAgeByOneYear(studentSignUpDto.getAge());
        StudentResponse response = new StudentResponse(studentSignUpDto.getName(), studentSignUpDto.getEmail(), newAge, studentSignUpDto.getAddress());
        return ResponseEntity.ok(response);
    }

    public int increaseAgeByOneYear(final int age) {
        return age + 1;
    }

}
