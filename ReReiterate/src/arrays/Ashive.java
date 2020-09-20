package arrays;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//import jxl.Sheet;
//import jxl.Workbook;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.RichTextString;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.eventusermodel.XSSFReader;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFComment;
//import org.apache.poi.xssf.usermodel.XSSFRichTextString;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Ashive {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub

		 int rowCount;
         int colCount;
         int rowNum;
         int colNum;
		try
        {
			File inputFile=new File("C:\\Users\\ashive-tyagi\\Desktop\\Master Control File.xlsx");
  