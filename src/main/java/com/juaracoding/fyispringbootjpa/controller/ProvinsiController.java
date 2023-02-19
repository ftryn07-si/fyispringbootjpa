package com.juaracoding.fyispringbootjpa.controller;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Moh. Ikhsan a.k.a. Fitriyani
Java Developer
Created on 2/19/2023 5:37 PM
@Last Modified 2/19/2023 5:37 PM
Version 1.0
*/

import com.juaracoding.fyispringbootjpa.handler.ResponseHandler;
import com.juaracoding.fyispringbootjpa.model.Provinsi;
import com.juaracoding.fyispringbootjpa.service.ProvinsiService;
import com.juaracoding.fyispringbootjpa.utils.ConstantMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/prov")
public class ProvinsiController {

    private ProvinsiService provinsiService;
    private String [] strExceptionArr = new String[2];

    @Autowired
    private ModelMapper modelMapper;

    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    List<Provinsi> lsCPUpload = new ArrayList<Provinsi>();

    @Autowired
    public ProvinsiController(ProvinsiService provinsiService) {
        strExceptionArr[0] = "ProvinsiController";
        this.provinsiService = provinsiService;
    }

    @PostMapping("/v2/save")
    public ResponseEntity<Object> saveProvinsi(@Valid
                                               @RequestBody Provinsi provinsi
    ){

        provinsiService.saveProvinsi(provinsi);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,null,null,null);
    }


    @PostMapping("/v2/saveAll")
    public ResponseEntity<Object> saveProvinsiList(@Valid
                                                   @RequestBody List<Provinsi> listProvinsi
    ){

        provinsiService.saveAllProvinsi(listProvinsi);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);

    }

}