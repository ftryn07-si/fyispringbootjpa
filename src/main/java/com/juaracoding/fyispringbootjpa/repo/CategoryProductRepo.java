package com.juaracoding.fyispringbootjpa.repo;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Moh. Ikhsan a.k.a. Fitriyani
Java Developer
Created on 2/18/2023 10:41 AM
@Last Modified 2/18/2023 10:41 AM
Version 1.0
*/


import com.juaracoding.fyispringbootjpa.model.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepo extends JpaRepository<CategoryProduct,Long> {


//    List<CategoryProduct> findByNameCategoryProduct(String value);

    /*
        findByNameCategoryProduct
        SELECT * FROM MstCategoryProduct WHERE NameCategoryProduct = ?
    */
}
