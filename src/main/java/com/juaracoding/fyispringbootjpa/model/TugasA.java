package com.juaracoding.fyispringbootjpa.model;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Moh. Ikhsan a.k.a. Fitriyani
Java Developer
Created on 2/19/2023 2:06 PM
@Last Modified 2/19/2023 2:06 PM
Version 1.0
*/
import com.juaracoding.fyispringbootjpa.utils.ConstantMessage;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "MstTugasA")
public class TugasA {
    @Id
    @GeneratedValue(generator = "IDTugasA")
    @GenericGenerator(name = "MstTugasA", strategy = "ppid2")
    @Column(name = "IDTugasA", nullable = false, length = 255)
    private String idTugasA;

    @Length(message = ConstantMessage.WARNING_CATPROD_MAX_LENGTH_NAME, max = 40)
    @NotEmpty(message = ConstantMessage.WARNING_CATPROD_NAME_CANNOT_EMPTY)
    @Column(name = "Nama", nullable = false, length = 40)
    private String nama;

    @NotEmpty(message = ConstantMessage.WARNING_CATPROD_ADDRESS_CANNOT_EMPTY)
    @Length(message = ConstantMessage.WARNING_CUST_ADDRESS_MAX_LENGTH, max = 500)
    @Column(name = "Alamat", nullable = false, length = 500)
    private String strAlamat;


    @NotEmpty(message = ConstantMessage.WARNING_CUST_BIRTHDATE_MANDATORY)
    @Column(name = "TanggalLahir", nullable = false)
    private LocalDate tanggalLahir;
    @NotEmpty(message = ConstantMessage.WARNING_CATPROD_GENDER_CANNOT_EMPTY)
    @Column(name = "JenisKelamin", nullable = false)
    private Character jenisKelamin;
    @Column(name = "MasihHidup", nullable = false)
    private Boolean masihHidup;

    /*
        start audit trails
     */
    @Column(name ="CreatedDate", nullable = false)
    private Date createdDate = new Date();

    @Column(name = "CreatedBy", nullable = false)
    private Integer createdBy;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete", nullable = false)
    private Byte isDelete = 1;
    /*
        end audit trails
     */

    public String getIdTugasA() {
        return idTugasA;
    }

    public void setIdTugasA(String idTugasA) {
        this.idTugasA = idTugasA;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStrAlamat() {
        return strAlamat;
    }

    public void setStrAlamat(String strAlamat) {
        this.strAlamat = strAlamat;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Character getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Character jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Boolean getMasihHidup() {
        return masihHidup;
    }

    public void setMasihHidup(Boolean masihHidup) {
        this.masihHidup = masihHidup;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
