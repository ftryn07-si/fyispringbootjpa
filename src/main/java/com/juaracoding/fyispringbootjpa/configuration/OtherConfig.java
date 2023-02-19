package com.juaracoding.fyispringbootjpa.configuration;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Moh. Ikhsan a.k.a. Fitriyani
Java Developer
Created on 2/18/2023 12:28 PM
@Last Modified 2/18/2023 12:28 PM
Version 1.0
*/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:other.properties")
public class OtherConfig {
    private static String flagLogging;//additionForLogging

    public static String getFlagLogging() {
        return flagLogging;
    }

    @Value("${flag.logging}")
    private void setFlagLogging(String flagLogging) {
        this.flagLogging = flagLogging;
    }

}