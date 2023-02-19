package com.juaracoding.fyispringbootjpa.utils;


import org.springframework.web.multipart.MultipartFile;

public class ExcelReader {

    public static boolean isExcel(MultipartFile multipartFile)
    {
        if(!ConstantMessage.CONTENT_TYPE_XLS.equals(multipartFile.getContentType()) && !ConstantMessage.CONTENT_TYPE_XLSX.equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;


//        if(ConstantMessage.CONTENT_TYPE_XLS.equals(multipartFile.getContentType()) || ConstantMessage.CONTENT_TYPE_XLSX.equals(multipartFile.getContentType()))
//        {
//            return true;
//        }
//        return false;
    }
}