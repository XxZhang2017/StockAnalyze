/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockinterface;
import java.io.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.regex.*;
/**
 *
 * @author d
 */
public class ReadExcel {
    public String[][] str=new String[100][100];
    public ArrayList<ArrayList<String>> strList=new ArrayList<ArrayList<String>>();
    public void read()throws FileNotFoundException,IOException{
    Workbook workbook=new XSSFWorkbook(new FileInputStream("D:\\stock\\StockInterface\\src\\test.xlsx"));
    Sheet sheet=workbook.getSheetAt(0);
    Iterator rowIter=sheet.rowIterator();
    Row r=(Row)rowIter.next();
    int loopRow,loopCol;
    loopRow=0;
    loopCol=0;
    
     while(rowIter.hasNext()) {
            Iterator cellIter = ((Row)rowIter.next()).cellIterator();
            while(cellIter.hasNext()) {
                Cell cell = (Cell)cellIter.next();
//                System.out.print(loopCol);
   //             str[loopRow][loopCol]=new String();
                  str[loopRow][loopCol]=cell.toString();
 //                 System.out.println(str[loopRow][loopCol]);
                loopCol++;
                
                
            }
 //           System.out.println();
                loopCol=0;
 //               str[++loopRow][++loopCol]="\n";
 //               System.out.print("what I append is"+str[loopRow][loopCol]);
            loopRow++;
//            System.out.println();
     }
        loopRow=0;
        loopCol=0;
        String regex="^[+-]?[0-9]+\\.[0-9]+$";
        Pattern p=Pattern.compile(regex);
               while(str[loopRow][loopCol]!=null){
 //                  System.out.println("in the loop");
                   while(str[loopRow][loopCol]!=null){
  //                     System.out.println(str[loopRow][loopCol]);
                       Matcher m=p.matcher(str[loopRow][loopCol]);
                       if(m.matches()){
 //                          System.out.println(str[loopRow][loopCol]);
                       }                      
                       loopCol++;
                   }
                    loopCol=0;
                    loopRow++;
               }
                   
}
    public void printStr(){
        int loopRow,loopCol;
        loopRow=0;
        loopCol=0;
        String regex="^[+-]?[0-9]+\\.[0-9]+$";
        Pattern p=Pattern.compile(regex);
        int count=0;
        boolean headFlag=true;
               while(str[loopRow][loopCol]!=null){
                   if(headFlag){
                       System.out.print(++count);  
                      
                   }
                   
                   while(str[loopRow][loopCol]!=null){
  //                     System.out.println(str[loopRow][loopCol]);
                       Matcher m=p.matcher(str[loopRow][loopCol]);
                       if(m.matches()){
 //                          System.out.print(loopRow+1);
    
                           System.out.print(str[loopRow][loopCol]);
                           System.out.print(" ");
                       }                      
                       loopCol++;
                   }
                   System.out.println();
                    loopCol=0;
                    loopRow++;
               }
    }

    public void writeToSheet()throws FileNotFoundException,IOException {
        int loopRow, loopCol;
        loopRow=0;
        loopCol=0;
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("test");
         XSSFRow row;
         String regex="^[+-]?[0-9]+\\.[0-9]+$";
        Pattern p=Pattern.compile(regex);
         while(str[loopRow][loopCol]!=null){
                   row = spreadsheet.createRow(loopRow);
                   System.out.println("in the loop");
                   while(str[loopRow][loopCol]!=null){
                        Matcher m=p.matcher(str[loopRow][loopCol]);
                       if(m.matches()){
                      Cell cell = row.createCell(loopCol);
 //                    System.out.println(loopRow);
 //                    System.out.println(loopCol);
                     System.out.println(str[loopRow][loopCol]);
                     cell.setCellValue(Double.parseDouble(str[loopRow][loopCol]));                                     
                       loopCol++;
                       }                    
                    
                   }
                    loopCol=0;
                    loopRow++;
               }
    }
    public static void main(String[] args) throws FileNotFoundException,IOException{
        ReadExcel re=new ReadExcel();
        re.read();
        re.printStr();
//        re.write();
//        re.writeToSheet();
        
    }
}
