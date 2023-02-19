package com.juaracoding.fyispringbootjpa.repo;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Moh. Ikhsan a.k.a. Fitriyani
Java Developer
Created on 2/18/2023 12:28 PM
@Last Modified 2/18/2023 12:28 PM
Version 1.0
*/
import com.juaracoding.fyispringbootjpa.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepo  extends CrudRepository<Person,Long> {

    List<Person> findByFirstName(String val);

}
