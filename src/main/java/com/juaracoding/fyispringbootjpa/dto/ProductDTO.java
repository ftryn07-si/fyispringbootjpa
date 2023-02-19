package com.juaracoding.fyispringbootjpa.dto;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Moh. Ikhsan a.k.a. Fitriyani
Java Developer
Created on 2/18/2023 12:28 PM
@Last Modified 2/18/2023 12:28 PM
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private Long idProduct;
    private String nameProduct;
    private CategoryProductDTO categoryProduct;
    private String descriptionProduct;

    public CategoryProductDTO getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProductDTO categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

//    public CategoryProductDTO getCategoryProductDTO() {
//        return categoryProductDTO;
//    }
//
//    public void setCategoryProductDTO(CategoryProductDTO categoryProductDTO) {
//        this.categoryProductDTO = categoryProductDTO;
//    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }
}
