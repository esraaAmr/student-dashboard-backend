package com.example.rest.controller;

import com.example.rest.model.dto.StudentSignUpDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("welcome")
public class WelcomeController {

    @GetMapping("{name}/{age}/{address}")
    public String welcomeStudentWithPathVariableApi(
            @PathVariable String name,
            @PathVariable int age,
            @PathVariable String address) {
        return "<h>Welcome "+name+ " to tech pioneers Hub <br>age = " + age + "<br>address: "+ address +"</h>";
    }

    @GetMapping
    public String welcomeStudentWithRequestParamApi(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String address) {
        return "<h>Welcome "+name+ " to tech pioneers Hub <br>age = " + age + "<br>address: "+ address +"</h>";
    }

    @GetMapping("studentWithBody")
    public String welcomeStudentWithRequestBodyApi(@RequestBody StudentSignUpDto studentSignUpDto) {
        return "<h>Welcome "+ studentSignUpDto.getName()+ " to tech pioneers Hub <br>age = " + studentSignUpDto.getAge() +
                "<br>address: "+ studentSignUpDto.getAddress() +"<br>Password: "+ studentSignUpDto.getPassword()+"</h>";
    }


}
