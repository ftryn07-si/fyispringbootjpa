//package com.juaracoding.fyispringbootjpa.controller;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Moh. Ikhsan a.k.a. Fitriyani
Java Developer
Created on 2/18/2023 12:28 PM
@Last Modified 2/18/2023 12:28 PM
Version 1.0
*/

//import com.juaracoding.fyispringbootjpa.handler.ResponseHandler;
////import com.juaracoding.fyispringbootjpa.model.Test;
////import com.juaracoding.fyispringbootjpa.service.TestServices;
//import com.juaracoding.fyispringbootjpa.utils.ConstantMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/tests")
//public class TestController {
//
//
//    private TestServices testServices;
//
//    @Autowired
//    public TestController(TestServices testServices) {
//        this.testServices = testServices;
//    }
//
//    @PostMapping("/v1/s")
//    public ResponseEntity<Object> saveAll(
//            @RequestBody List<Test> listTest
//    ){
//
//        testServices.saveAllTest(listTest);
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,null,null,null);
//
//    }
//}
