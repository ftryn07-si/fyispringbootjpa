//package com.juaracoding.fyispringbootjpa.controller;/*
//Created By IntelliJ IDEA 2022.2.3 (Community Edition)
//Build #IU-222.4345.14, built on October 5, 2022
//@Author Moh. Ikhsan a.k.a. Fitriyani
//Java Developer
//Created on 2/20/2023 5:44 AM
//@Last Modified 2/20/2023 5:44 AM
//Version 1.0
//*/
//
//
//
//
//import com.juaracoding.fyispringbootjpa.configuration.OtherConfig;
//
//import com.juaracoding.fyispringbootjpa.dto.ProvinsiDTO;
//import com.juaracoding.fyispringbootjpa.handler.ResourceNotFoundException;
//import com.juaracoding.fyispringbootjpa.handler.ResponseHandler;
//
//import com.juaracoding.fyispringbootjpa.model.Provinsi;
//import com.juaracoding.fyispringbootjpa.service.ProvinsiService;
//import com.juaracoding.fyispringbootjpa.utils.ConstantMessage;
//import com.juaracoding.fyispringbootjpa.utils.CsvReader;
//import com.juaracoding.fyispringbootjpa.utils.ExcelReader;
//import com.juaracoding.fyispringbootjpa.utils.LoggingFile;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.Valid;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.*;
//
//@RestController
//@RequestMapping("/api/prov/pr")
//public class qq {
//
//    private ProvinsiService provinsiService;
//    private String [] strExceptionArr = new String[2];
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    private Map<String,Object> objectMapper = new HashMap<String,Object>();
//    List<Provinsi> lsCPUpload = new ArrayList<Provinsi>();
//
//    @Autowired
//    public ProvinsiController(ProvinsiService provinsiService) {
//        strExceptionArr[0] = "ProvinsiController";
//        this.provinsiService = provinsiService;
//    }
//
//    @PostMapping("/v2/save")
//    public ResponseEntity<Object> saveProvinsi(@Valid
//                                               @RequestBody Provinsi provinsi
//    ){
//
//        provinsiService.saveProvinsi(provinsi);
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,null,null,null);
//    }
//
//
//    @PostMapping("/v2/saveAll")
//    public ResponseEntity<Object> saveProvinsiList(@Valid
//                                                   @RequestBody List<Provinsi> listProvinsi
//    ){
//
//        provinsiService.saveAllProvinsi(listProvinsi);
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
//
//    }
//
//    @PostMapping("/v2/upl/batch")
//    public ResponseEntity<Object> uploadCsvMaster(@Valid
//                                                  @RequestParam("fileDemo")
//                                                  @RequestHeader  MultipartFile multipartFile,
//                                                  WebRequest request
//    ) throws Exception {
//
//        if(CsvReader.isCsv(multipartFile))
//        {
//            return provinsiService.saveUploadFile(
//                    csvToProvinsi(
//                            multipartFile.getInputStream()),multipartFile,request);
//        }
//        else
//        {
//            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename(),
//                    HttpStatus.NOT_ACCEPTABLE,null,"FI01021",request);
//        }
//
//    }
//    @PostMapping("/v2/uplxls/batch")
//    public ResponseEntity<Object> uploadExcelMaster(@Valid
//                                                    @RequestParam("fileDemo")
//                                                    @RequestHeader  MultipartFile multipartFile,
//                                                    WebRequest request
//    ) throws Exception {
//
//        if(ExcelReader.isExcel(multipartFile))
//        {
//            return provinsiService.saveUploadFile(
//                    excelToProvinsi(multipartFile.getInputStream()),
//                    multipartFile,
//                    request);
//        }
//        else
//        {
//            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_NOT_EXCEL_FILE+" -- "+multipartFile.getOriginalFilename(),
//                    HttpStatus.NOT_ACCEPTABLE,null,"FI01021",request);
//        }
//    }
//
//    @PutMapping("/v2/saveAll/{id}")
//    public ResponseEntity<Object> updateProvinsiById(@Valid
//                                                     @RequestBody Provinsi provinsi,
//                                                     @PathVariable String id
//    ) throws Exception {
//
//        provinsiService.updateProvinsi(provinsi,id);
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,null,null,null);
//    }
//
//    @GetMapping("/view/f")
//    public ResponseEntity<Object> findAll(){
//
//        List<Provinsi> ls = provinsiService.findAllProvinsi();
//
//        if(ls.size()==0)
//        {
//            return new ResponseHandler().
//                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
//                            HttpStatus.NOT_FOUND,
//                            provinsiService.findAllProvinsi(),
//                            null,
//                            null);
//        }
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
//                        HttpStatus.OK,
//                        ls,
//                        null,
//                        null);
//
//
//    }
//
//    /*
//        PAGINATION SORTING DEFAULT by ID TANPA PARAMETER SORT BY YANG SPESIFIK DAN MASIH RETURN PAGE BUKAN LIST
//        Problem :
//        1. tidak dapat menggunakan validasi jika data kosong. artinya informasi hanya seadanya saja.
//        2. Informasi paging masih berantakan, informasi yang tidak perlu juga masih dikembalikan
//     */
//    @GetMapping("/v2/fp1/{size}/{page}/{sort}")
//    public ResponseEntity<Object> findAllPagination(
//            @PathVariable("size") Integer sizez,
//            @PathVariable("page") Integer pagez,
//            @PathVariable("sort") String sortz
//    ){
//
//        Pageable pageable = null;
//        if(sortz.equalsIgnoreCase("desc"))
//        {
//            pageable = PageRequest.of(pagez,sizez, Sort.by("idProvinsi").descending());
//        }
//        else
//        {
//            pageable = PageRequest.of(pagez,sizez);
//        }
//        Page<Provinsi> page = provinsiService.findAllProvinsiByPage(pageable);
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
//                        HttpStatus.OK,
//                        page,
//                        null,
//                        null);
//    }
//
//    /*
//        PAGINATION SORTING DEFAULT by ID TANPA PARAMETER SORT BY YANG SPESIFIK DAN RESPONSE SUDAH MENGGUNAKAN LIST
//        Sudah ada validasi semisal data kosong dan informasi di response lebih dinamis
//        Problem :
//           1. Data content sudah lebih baik namun informasi Paging tidak diikutsertakan, sehingga menghambat front end nantinya
//           2. belum menggunakan DTO data content untuk response masih belum rapih
//     */
//    @GetMapping("/v2/fp2/{size}/{page}/{sort}")
//    public ResponseEntity<Object> findAllPagination2(
//            @PathVariable("size") Integer sizez,
//            @PathVariable("page") Integer pagez,
//            @PathVariable("sort") String sortz
//    ){
//
//        Pageable pageable = null;
//        if(sortz.equalsIgnoreCase("desc"))
//        {
//            pageable = PageRequest.of(pagez,sizez, Sort.by("idProvinsi").descending());
//        }
//        else
//        {
//            pageable = PageRequest.of(pagez,sizez);
//
//        }
//        Page<Provinsi> page = provinsiService.findAllProvinsiByPage(pageable);
//        List<Provinsi> listProvinsi = page.getContent();
//        if(listProvinsi.size()==0)
//        {
//            return new ResponseHandler().
//                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
//                            HttpStatus.NOT_FOUND,
//                            null,
//                            null,
//                            null);
//        }
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
//                        HttpStatus.OK,
//                        listProvinsi,
//                        null,
//                        null);
//    }
//
//    /*
//       PAGINATION DENGAN SORT BY DAN RESPONSE SUDAH MENGGUNAKAN LIST NAMUN VALIDASI SORT BY MASIH DI EMBED DI METHOD REQUEST
//       Problem :
//       1. Data content sudah lebih baik namun Data Paging tidak diikutsertakan, sehingga menghambat front end nantinya
//       2. belum menggunakan DTO data content untuk response masih belum rapih
//    */
//    @GetMapping("/v2/fpsb1/{size}/{page}/{sort}/{sortby}")
//    public ResponseEntity<Object> findPaginationSortBy1(
//            @PathVariable("size") Integer sizez,
//            @PathVariable("page") Integer pagez,
//            @PathVariable("sort") String sortz,
//            @PathVariable("sortby") String sortzBy
//    ){
//
//        Pageable pageable = null;
//        String strSortBy = "";
//
//        if(sortzBy.equalsIgnoreCase("id"))
//        {
//            strSortBy = "idProvinsi";
//        }
//        else if(sortzBy.equalsIgnoreCase("nama"))
//        {
//            strSortBy = "namaProvinsi";
//        }
//        else if(sortzBy.equalsIgnoreCase("sing"))
//        {
//            strSortBy = "singkatan";
//        }
//        else if(sortzBy.equalsIgnoreCase("lat"))
//        {
//            strSortBy = "lat";
//        }
//        else if(sortzBy.equalsIgnoreCase("lon"))
//        {
//            strSortBy = "lon";
//        }
//        else if(sortzBy.equalsIgnoreCase("pemimpin"))
//        {
//            strSortBy = "namaPemimpin";
//        }
//        else
//        {
//            strSortBy = "idProvinsi";
//        }
//
//        if(sortz.equalsIgnoreCase("desc"))
//        {
//            pageable = PageRequest.of(pagez,sizez, Sort.by(strSortBy).descending());
//        }
//        else
//        {
//            pageable = PageRequest.of(pagez,sizez, Sort.by(strSortBy).ascending());
//        }
//
//        Page<Provinsi> page = provinsiService.findAllProvinsiByPage(pageable);
//        List<Provinsi> listProvinsi = page.getContent();
//
//        if(listProvinsi.size()==0)
//        {
//            return new ResponseHandler().
//                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
//                            HttpStatus.NOT_FOUND,
//                            provinsiService.findAllProvinsi(),
//                            null,
//                            null);
//        }
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
//                        HttpStatus.OK,
//                        listProvinsi,
//                        null,
//                        null);
//    }
//
//    /*
//       PAGINATION DENGAN SORT BY DAN RESPONSE SUDAH MENGGUNAKAN LIST SERTA VALIDASI SUDAH DIBUAT METHOD SENDIRI
//       Problem :
//       1. belum menggunakan DTO data content untuk response masih belum rapih
//    */
//    @GetMapping("/v2/fpsb2/{size}/{page}/{sort}/{sortby}")
//    public ResponseEntity<Object> findPaginationSortBy2(
//            @PathVariable("size") Integer sizez,
//            @PathVariable("page") Integer pagez,
//            @PathVariable("sort") String sortz,
//            @PathVariable("sortby") String sortzBy
//    ){
//
//        Pageable pageable = filterPagination(pagez,sizez,sortz,sortzBy);
//        Page<Provinsi> page = provinsiService.findAllProvinsiByPage(pageable);
//        List<Provinsi> listProvinsi = page.getContent();
//
//        if(listProvinsi.size()==0)
//        {
//            return new ResponseHandler().
//                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
//                            HttpStatus.NOT_FOUND,
//                            provinsiService.findAllProvinsi(),
//                            null,
//                            null);
//        }
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
//                        HttpStatus.OK,
//                        listProvinsi,
//                        null,
//                        null);
//    }
//
//    /*
//       PAGINATION DENGAN SORT BY DAN RESPONSE SUDAH MENGGUNAKAN LIST SERTA VALIDASI SUDAH DIBUAT METHOD SENDIRI + DTO di embed di method request
//       Problem :
//       1. kurang rapih karena DTO nya masih diembed di method request
//    */
//    @GetMapping("/v2/fpsbd1/{size}/{page}/{sort}/{sortby}")
//    public ResponseEntity<Object> findPaginationSortByDTO1(
//            @PathVariable("size") Integer sizez,
//            @PathVariable("page") Integer pagez,
//            @PathVariable("sort") String sortz,
//            @PathVariable("sortby") String sortzBy
//    ){
//
//        Pageable pageable = filterPagination(pagez,sizez,sortz,sortzBy);
//        Page<Provinsi> page = provinsiService.findAllProvinsiByPage(pageable);
//        List<Provinsi> listProvinsi = page.getContent();
//
//        /*
//            CATATAN PENTING!!
//            VALIDASI INI DILETAKKAN DISINI AGAR TIDAK PERLU MELAKUKAN PROSES APAPUN (PROSES MAPPING ATAU TRANSFORM DTO) JIKA DATA YANG DICARI KOSONG
//         */
//        if(listProvinsi.size()==0)
//        {
//            return new ResponseHandler().
//                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
//                            HttpStatus.NOT_FOUND,
//                            null,
//                            null,
//                            null);
//        }
//
//        List<ProvinsiDTO> listProvinsiDTO = modelMapper.map(listProvinsi, new TypeToken<List<ProvinsiDTO>>() {}.getType());
//
//
//        objectMapper.put("data",listProvinsiDTO);
//        objectMapper.put("currentPage",page.getNumber());
//        objectMapper.put("totalItems",page.getTotalElements());
//        objectMapper.put("totalPages",page.getTotalPages());
//        objectMapper.put("sort",page.getSort());
//        objectMapper.put("numberOfElements",page.getNumberOfElements());
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
//                        HttpStatus.OK,
//                        objectMapper,
//                        null,
//                        null);
//    }
//
//    /*
//       PAGINATION DENGAN SORT BY DAN RESPONSE SUDAH MENGGUNAKAN LIST SERTA VALIDASI SUDAH DIBUAT METHOD SENDIRI + DTO sudah dibuat method sendiri
//    */
//    @GetMapping("/v2/fpsbd2/{size}/{page}/{sort}/{sortby}")
//    public ResponseEntity<Object> findPaginationSortByDTO2(
//            @PathVariable("size") Integer sizez,
//            @PathVariable("page") Integer pagez,
//            @PathVariable("sort") String sortz,
//            @PathVariable("sortby") String sortzBy
//    ){
//
//        Pageable pageable = filterPagination(pagez,sizez,sortz,sortzBy);
//        Page<Provinsi> page = provinsiService.findAllProvinsiByPage(pageable);
//        List<Provinsi> listProvinsi = page.getContent();
//
//        /*
//            CATATAN PENTING!!
//            VALIDASI INI DILETAKKAN DISINI AGAR TIDAK PERLU MELAKUKAN PROSES APAPUN (PROSES MAPPING ATAU TRANSFORM DTO) JIKA DATA YANG DICARI KOSONG
//         */
//        if(listProvinsi.size()==0)
//        {
//            return new ResponseHandler().
//                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
//                            HttpStatus.NOT_FOUND,
//                            null,
//                            null,
//                            null);
//        }
//
//        List<ProvinsiDTO> listProvinsiDTO = modelMapper.map(listProvinsi, new TypeToken<List<ProvinsiDTO>>() {}.getType());
//        Map<String, Object> mapResult = transformObject(objectMapper,listProvinsiDTO,page);
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
//                        HttpStatus.OK,
//                        mapResult,
//                        null,
//                        null);
//    }
//
//    private Map<String,Object> transformObject(Map<String,Object> mapz, List ls, Page page)
//    {
//        mapz.put("data",ls);
//        mapz.put("currentPage",page.getNumber());
//        mapz.put("totalItems",page.getTotalElements());
//        mapz.put("totalPages",page.getTotalPages());
//        mapz.put("sort",page.getSort());
//        mapz.put("numberOfElements",page.getNumberOfElements());
//
//        return mapz;
//    }
//
//    private Pageable filterPagination(Integer page, Integer size, String sorts , String sortsBy)
//    {
//        Pageable pageable;
//        String strSortBy = "";
//
//        if(sortsBy.equalsIgnoreCase("id"))
//        {
//            strSortBy = "idProvinsi";
//        }
//        else if(sortsBy.equalsIgnoreCase("nama"))
//        {
//            strSortBy = "namaProvinsi";
//        }
//        else if(sortsBy.equalsIgnoreCase("sing"))
//        {
//            strSortBy = "singkatan";
//        }
//        else if(sortsBy.equalsIgnoreCase("lat"))
//        {
//            strSortBy = "lat";
//        }
//        else if(sortsBy.equalsIgnoreCase("lon"))
//        {
//            strSortBy = "lon";
//        }
//        else if(sortsBy.equalsIgnoreCase("pemimpin"))
//        {
//            strSortBy = "namaPemimpin";
//        }
//        else
//        {
//            strSortBy = "idProvinsi";
//        }
//
//        if(sorts.equalsIgnoreCase("desc"))
//        {
//            pageable = PageRequest.of(page,size, Sort.by(strSortBy).descending());
//        }
//        else
//        {
//            pageable = PageRequest.of(page,size, Sort.by(strSortBy).ascending());
//        }
//
//        return pageable;
//    }
//
//    private Pageable filterPaginationV2(Integer page, Integer size, String sorts , String sortsBy)
//    {
//        Pageable pageable;
//        String strSortBy = "";
//
//
//        if(sortsBy.equalsIgnoreCase("id"))
//        {
//            strSortBy = "idProvinsi";
//        }
//        else if(sortsBy.equalsIgnoreCase("nama"))
//        {
//            strSortBy = "namaProvinsi";
//        }
//        else if(sortsBy.equalsIgnoreCase("sing"))
//        {
//            strSortBy = "singkatan";
//        }
//        else if(sortsBy.equalsIgnoreCase("lat"))
//        {
//            strSortBy = "lat";
//        }
//        else if(sortsBy.equalsIgnoreCase("lon"))
//        {
//            strSortBy = "lon";
//        }
//        else if(sortsBy.equalsIgnoreCase("pemimpin"))
//        {
//            strSortBy = "namaPemimpin";
//        }
//        else
//        {
//            strSortBy = "idProvinsi";
//        }
//
//        if(sorts.equalsIgnoreCase("desc"))
//        {
//            pageable = PageRequest.of(page,size, Sort.by(strSortBy).descending());
//        }
//        else
//        {
//            pageable = PageRequest.of(page,size, Sort.by(strSortBy).ascending());
//        }
//
//        return pageable;
//    }
//
//    public List<Provinsi> csvToProvinsi(InputStream inputStream) throws Exception {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//        CSVParser csvParser = new CSVParser(bufferedReader,
//                CSVFormat.DEFAULT.withFirstRecordAsHeader().
//                        withIgnoreHeaderCase().
//                        withDelimiter(',')
//        );
////        lsCPUpload = new ArrayList<Provinsi>();
//        lsCPUpload.clear();
//        int intCatchErrorRecord = 1;
//        try {
//            Iterable<CSVRecord> iterRecords = csvParser.getRecords();
//
//            for (CSVRecord record : iterRecords) {
//                Provinsi cProvinsi = new Provinsi();
//                cProvinsi.setNamaProvinsi(record.get("NamaProvinsi"));
//                cProvinsi.setSingkatan(record.get("Singkatan"));
//                cProvinsi.setLat(record.get("Latitude"));
//                cProvinsi.setLon(record.get("Longitude"));
//                cProvinsi.setNamaPemimpin(record.get("NamaPemimpin"));
//                cProvinsi.setCreatedBy(record.get("CreatedBy"));
//                lsCPUpload.add(cProvinsi);
//                intCatchErrorRecord++;
//            }
//
//        } catch (Exception ex) {
//            strExceptionArr[1]="csvToProvinsi(InputStream inputStream) --- LINE 530"+ex.getMessage()+" Error Record at Line "+intCatchErrorRecord;
//            LoggingFile.exceptionStringz(strExceptionArr,ex, OtherConfig.getFlagLogging());
//            throw new Exception(ex.getMessage()+" Error Record at Line "+intCatchErrorRecord);
//        }
//        finally {
//            if (!csvParser.isClosed()) {
//                csvParser.close();
//            }
//        }
//        return lsCPUpload;
//    }
//
//    public List<Provinsi> excelToProvinsi(InputStream inputStream) throws Exception {
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet sheet = workbook.getSheet("page-1");
////        Sheet sheetOne = workbook.getSheet("page-1");
////        Sheet sheetTwo = workbook.getSheet("page-2");
//        Iterator<Row> rows = sheet.iterator();
//
//        lsCPUpload.clear();
//        int intCatchErrorRecord = 0;
//        int intNextCell = 0;
//
//        try {
//
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//                if(intCatchErrorRecord!=0){//MENGABAIKAN HEADER
//                    intNextCell = 0;
//
//                    Provinsi cProvinsi = new Provinsi();
//
//                    while (cellsInRow.hasNext()) {
//                        Cell currentCell = cellsInRow.next();
//                        /*
//                            URUTAN nya harus dimapping sesuai dengan cell di file excel nya !! dimulai dari index 0
//                         */
//                        if(intNextCell==0)
//                        {
//                            cProvinsi.setNamaProvinsi(currentCell.getStringCellValue());
//                        }
//                        else if(intNextCell==1)
//                        {
//                            cProvinsi.setSingkatan(currentCell.getStringCellValue());
//                        }
//                        else if(intNextCell==2)
//                        {
//                            cProvinsi.setLat(currentCell.getStringCellValue());
//                        }
//                        else if(intNextCell==3)
//                        {
//                            cProvinsi.setLon(currentCell.getStringCellValue());
//                        }
//                        else if(intNextCell==4)
//                        {
//                            cProvinsi.setNamaPemimpin(currentCell.getStringCellValue());
//                        }
//                        else
//                        {
//                            cProvinsi.setCreatedBy(currentCell.getStringCellValue());
//                        }
//
//                        intNextCell++;
//                    }
//                    lsCPUpload.add(cProvinsi);
//                }
//                intCatchErrorRecord++;
//            }
//
//        } catch (Exception ex) {
//            strExceptionArr[1]="excelToProvinsi(InputStream inputStream) ---LINE 589";
////            System.out.println(OtherConfig.getFlagLogging());
//            LoggingFile.exceptionStringz(strExceptionArr,new ResourceNotFoundException(ex.getMessage()+" Error Record at Line "+intCatchErrorRecord), OtherConfig.getFlagLogging());
//            throw new Exception(ex.getMessage()+" Error Record at Line "+intCatchErrorRecord);
//        }
//        finally
//        {
//            workbook.close();
//        }
//        return lsCPUpload;
//    }
//}