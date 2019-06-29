package com.shafique;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class XLSUtil {

    private static Map<String, String> permissionMap = new HashMap<String, String>();
    static{
        permissionMap.put("DO_NOT_EMAIL", "OK_TO_EMAIL");
        permissionMap.put("DO_NOT_CALL", "OK_TO_CALL");
        permissionMap.put("DO_NOT_MAIL", "OK_TO_MAIL");
        permissionMap.put("DO_NOT_CONTACT", "OK_TO_CONTACT");
    }



    public static List<String> readChannels(String suppType){
        List<String> channels = null;
        try {
            FileInputStream file = new FileInputStream(new File("supperssion-channels-mapping.xlsx"));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            Row header = rowIterator.next();
            channels = new ArrayList<String>();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
               if(suppType.equals(row.getCell(0).getStringCellValue())) {
                   Iterator<Cell> cellIterator = row.cellIterator();

                   while (cellIterator.hasNext()) {
                       Cell cell = cellIterator.next();
                       if("Y".equals(cell.getStringCellValue())) {
                           channels.add(header.getCell(cell.getColumnIndex()).getStringCellValue());
                       }
                   }

               }
            }
            file.close();

        }catch (Exception e){
           //TODO required exception handling
        }

        return channels;
    }

    public static Permission evaluatePermission(String suppType, Permission permission){
        try{

            FileInputStream file = new FileInputStream(new File("permissionRules.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            Row header = rowIterator.next();

            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                if(row.getRowNum() > 0 && permissionMap.get(suppType).equals(row.getCell(0).getStringCellValue())) {
                    if("DO_NOT_CALL".equals(suppType)) {
                        permission.setOK_TO_CALL(BooleanToString(
                         stringToBoolean(row.getCell(1).getStringCellValue()) &&
                                stringToBoolean(row.getCell(3).getStringCellValue()) &&
                                (
                                   stringToBoolean(row.getCell(9).getStringCellValue())
                                   ||stringToBoolean(row.getCell(11).getStringCellValue( ))
                                   ||stringToBoolean(row.getCell(13).getStringCellValue( ))
                                )));
                    }else if("DO_NOT_EMAIL".equals(suppType)) {
                        permission.setOK_TO_EMAIL(BooleanToString(
                        stringToBoolean(row.getCell(1).getStringCellValue()) &&
                                stringToBoolean(row.getCell(7).getStringCellValue()) &&
                                (
                                        stringToBoolean(row.getCell(9).getStringCellValue())
                                                ||stringToBoolean(row.getCell(11).getStringCellValue( ))
                                                ||stringToBoolean(row.getCell(13).getStringCellValue( ))
                                )));
                    }else if("DO_NOT_MAIL".equals(suppType)) {
                        permission.setOK_TO_MAIL(BooleanToString(
                         stringToBoolean(row.getCell(1).getStringCellValue()) &&
                                stringToBoolean(row.getCell(5).getStringCellValue()) &&
                                (
                                        stringToBoolean(row.getCell(9).getStringCellValue())
                                                ||stringToBoolean(row.getCell(11).getStringCellValue( ))
                                                ||stringToBoolean(row.getCell(13).getStringCellValue( ))
                                )));
                    }else if("DO_NOT_CONTACT".equals(suppType)) {
                        permission.setOK_TO_CONTACT(BooleanToString(
                                  stringToBoolean(row.getCell(1).getStringCellValue()) &&
                                        stringToBoolean(row.getCell(3).getStringCellValue()) &&
                                        stringToBoolean(row.getCell(5).getStringCellValue()) &&
                                        stringToBoolean(row.getCell(7).getStringCellValue()) &&
                                        stringToBoolean(row.getCell(9).getStringCellValue()) &&
                                          stringToBoolean(row.getCell(11).getStringCellValue()) &&
                                          stringToBoolean(row.getCell(13).getStringCellValue())
                                        ));
                    }

                    System.out.println("");
                }
            }
            file.close();

        }catch (Exception e){
            //TODO required exception handling
        }

        return permission;
    }


    public static List<Object> getAllCPImpacted(String suppType, String input){
        //TODO input could be phone or email or address. based on the given input read
        //TODO CP and INVDL data from pref/supp table.
        return new ArrayList<Object>();
    }

    private static boolean stringToBoolean(String input){
        return "N".equals(input) ? true : false;
    }

    private static String BooleanToString(boolean input){
        return (true == input) ? "Y" : "N";
    }
}
