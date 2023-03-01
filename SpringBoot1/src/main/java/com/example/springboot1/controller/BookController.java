package com.example.springboot1.controller;
import com.example.springboot1.Bean.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private Enterprise environment;
    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id) {
        System.out.println("--------------------------------");
        System.out.println(environment.getCategory());
        System.out.println("FrontEnd: id is "+id);
        return "Backend is Success!";
    }
}
//@RestController
//@RequestMapping("/books")
//public class BookController {
//    @GetMapping("/{id}")
//    public String getById(@PathVariable Integer id) {
//        System.out.println("--------------------------------");
//        System.out.println("FrontEnd: id is "+id);
//        return "Backend is Success!";
//    }
//}