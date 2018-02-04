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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
FileInputStream() work with bytes,typically used for media; FileStreamReader() work with characters
 */
public class StockInterface {

    /**
     * @param args the command line arguments
     */
    public int row;
    public int col;
    public String fileName;  //?final
    //public InputStream input;
    float[][] num;
    public int ReadStartRow;
    public int ReadStartCol;
    
    public StockInterface() {
        BufferedReader bf = null; 
        int loopRow,loopCol;
        try{
                 System.out.println("Please enter the total number of rows: ");
                 bf = new BufferedReader(new InputStreamReader(System.in));
                 String line = bf.readLine();
                 row=Integer.parseInt(line);
//                System.out.println(row);
                 System.out.println("Please enter the total number of columns: ");
                 bf = new BufferedReader(new InputStreamReader(System.in));
                 line = bf.readLine();
                 col=Integer.parseInt(line);
//                System.out.println(col);
                 
                 num=new float[row][col];
                 System.out.println("Please enter your data,separate by comma:");
                 for(loopRow=0;loopRow<row;loopRow++){
                     System.out.println("Please enter the "+(loopRow+1)+" row: ");
     //                bf = new BufferedReader(new InputStreamReader(System.in));
                     String[] lineNum=bf.readLine().split(","); 
                     if(lineNum.length>col){
                         System.out.println("the numbers of data you entered are more than "+col
                         +"you will lose the data beyond the column number");
                     }
                     for(loopCol=0;loopCol<col;loopCol++){
                         num[loopRow][loopCol]=Float.parseFloat(lineNum[loopCol]);
                     }
                 }
            
            }catch(Exception e){
                   System.out.println("In StockInterface default constructor:"+e);
            }finally{
                       try{   
                            if(bf!=null){
                            bf.close();
                           }
                     }catch(Exception e){
                           System.out.println("In StockInterface default constructor, finaaly block"+e);
                     }  
}
            
               
    }
    public StockInterface(String fileName) throws IOException{
        
        this.fileName=fileName;
        FileInputStream input=new FileInputStream(this.fileName);
 //       SetColRowNumber();
        System.out.println("the row number is "+this.row+" "+" the col number is "+this.col);
        
    }
    //set startRow number and startCol number; 
    //count total row number;
    public int getRowNumber()throws IOException{
        int countRow=0;
        boolean rowFlag=false;
        try{
            Workbook workbook = WorkbookFactory.create(new File(this.fileName));
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator=row.cellIterator();
                    while (cellIterator.hasNext()) {
                        if(!rowFlag){    
                            break;
                        }
                            Cell cell = cellIterator.next();
                            if((cell.getCachedFormulaResultTypeEnum()==CellType.NUMERIC)&&(!rowFlag)){
                                ReadStartRow=cell.getRowIndex();
                                ReadStartCol=cell.getColumnIndex();
                                rowFlag=true;
                                  break;
                            } 
                    }
                    rowIterator.next();            
            }
           workbook.close();
            
        }catch(Exception e){
            System.out.println("Open file in getRowNumber function: "+e);
        }
        return this.row=countRow;
    }
 /*   public void getColRowNumber()throws IOException{
        int countCol=0;
       
        try{
            Workbook workbook = WorkbookFactory.create(new File(this.fileName));
            
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                  Row row = rowIterator.next();
                  // Now let's iterate over the columns of the current row
                  Iterator<Cell> cellIterator = row.cellIterator();                
                  while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if(cell)
                            cell.get
//                  String cellValue = cell.getStringCellValue();
//                  System.out.print(cellValue + "\t");
                        countCol++;
            }
            countRow++;
            System.out.println();
            }
           workbook.close();
          
             
        }catch(Exception e){
            System.out.println("Open file in getRowNumber function: "+e);
        }
        this.col=countCol;
        this.row=countRow;
    }*/

    public void printNum(){
        try{
              if(num!=null){
              int loopRow,loopCol;        
              for(loopRow=0;loopRow<row;loopRow++){
                     for(loopCol=0;loopCol<col;loopCol++){
                         System.out.print(num[loopRow][loopCol]+" ");
                     }
                     System.out.println();
               }         
            }else{
                System.out.println("float Array has not been initiated");
            }
        }catch(Exception e){
            System.out.println("In printNum function: "+e);
        }
    }
     public void writeToSheet()throws FileNotFoundException,IOException {
        int loopRow, loopCol;
        loopRow=0;
        loopCol=0;
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("test");
        for(loopRow=0;loopRow<4;loopRow++){
            Row row=spreadsheet.createRow((short)loopRow);
            for(loopCol=0;loopCol<2;loopCol++){
                Cell cell=row.createCell((short)loopCol);
                cell.setCellValue(num[loopRow][loopCol]);
            }
        }
        FileOutputStream fileOut=new FileOutputStream("./current.xlsx");
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public static void main(String[] args) throws IOException {
          StockInterface st=new StockInterface();
          st.writeToSheet();
          
    }
    
}
