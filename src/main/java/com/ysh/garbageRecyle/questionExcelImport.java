package com.ysh.garbageRecyle;

import org.apache.http.entity.ContentType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class questionExcelImport {


    public static void checkFile(MultipartFile file) throws Exception {
        // 判断文件是否存在
        if (null == file) {
            throw new FileNotFoundException("文件不存在！");
        }
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 判断文件是否是excel文件
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            throw new IOException(fileName + "不是excel文件");
        }
    }

    /**
     * 获取workbook
     *
     * @param file
     * @return
     */
    public static Workbook getWorkBook(MultipartFile file) {
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            // 获取excel文件的io流
            InputStream is = file.getInputStream();
            // 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith("xls")) {
                // 2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                // 2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {

        }
        return workbook;
    }

    /**
     * 获取解析后的文件内容
     *
     * @return
     */
    public static void readExcel(MultipartFile file) throws Exception {
//        List<User> list = new ArrayList<User>();
        try {
            checkFile(file);
            // 获得Workbook工作薄对象
            Workbook workbook = getWorkBook(file);
            int sheetSize = workbook.getNumberOfSheets();
                // 获取第一个张表
                Sheet sheet = workbook.getSheetAt(0);
                // 获取每行中的字段
                for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                    Row row = sheet.getRow(j); // 获取行
                    if (row == null) {//略过空行
                        continue;
                    }else{
                        // 获取单元格中的值并存到对象中
//                        User user = new User();
//                        user.setFullName(row.getCell(0).getStringCellValue());
//                        user.setPassword(row.getCell(1).getStringCellValue());
//                        user.setUsername(row.getCell(2).getStringCellValue());
//                        list.add(user);
                        System.out.println(row.getCell(0).getStringCellValue());
                        System.out.println(row.getCell(1).getStringCellValue());
                        System.out.println(row.getCell(2).getStringCellValue());
                        System.out.println((int)(row.getCell(3).getNumericCellValue()));
                        System.out.println((int)(row.getCell(4).getNumericCellValue()));
                        System.out.println((int)(row.getCell(5).getNumericCellValue()));
                    }
                }

        } catch (Exception e) {
            throw new Exception("Excel导入失败！");
        }
    }

    public static void main(String[] args) throws Exception {
        File file=new File("C:/Users/shuhao/Desktop/questionData.xlsx");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        readExcel(multipartFile);
    }
}
