package com.juaracoding.fyispringbootjpa.service;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Moh. Ikhsan a.k.a. Fitriyani
Java Developer
Created on 2/19/2023 5:53 PM
@Last Modified 2/19/2023 5:53 PM
Version 1.0
*/

import com.juaracoding.fyispringbootjpa.model.Provinsi;
import com.juaracoding.fyispringbootjpa.repo.ProvinsiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProvinsiService {

    private ProvinsiRepo provinsiRepo;
    private String [] strExceptionArr = new String[2];


    @Autowired
    public ProvinsiService(ProvinsiRepo provinsiRepo) {
        strExceptionArr[0] = "ProvinsiService";
        this.provinsiRepo = provinsiRepo;
    }
    @Transactional(rollbackFor = Exception.class)
    public void saveProvinsi(Provinsi provinsi){

        provinsiRepo.save(provinsi);

    }
    @Transactional(rollbackFor = Exception.class)
    public void saveAllProvinsi(List<Provinsi> listProvinsi){
        provinsiRepo.saveAll(listProvinsi);
    }
}




