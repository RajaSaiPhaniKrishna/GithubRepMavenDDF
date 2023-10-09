package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_File_Util {
	XSSFWorkbook wb;
	//Constructor for reading excel path
	public Excel_File_Util (String Excelpath) throws   Throwable
	{
		FileInputStream fi = new FileInputStream(Excelpath);
		wb = new XSSFWorkbook(fi);
	}
	//method for counting no. of rows in a sheet
	public int rowcount (String Sheetname)
	{
	return wb.getSheet(Sheetname).getLastRowNum();
	}
	//method for reading cell data
	public String getcelldata (String Sheetname,int row,int column)
	{
		String data = "  ";
		if(wb.getSheet(Sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
		{
			int celldata = (int) wb.getSheet(Sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb.getSheet(Sheetname).getRow(row).getCell(column).getStringCellValue();
			}
		return data;
		}
//method for writing results
	public void setcelldata (String Sheetname,int row, int column, String status,String writeExcel) throws Throwable
	{
		//get Sheet from wb
		XSSFSheet ws = wb.getSheet(Sheetname);
		//get row from sheet
		XSSFRow rownum = ws.getRow(row);
		//create cell in a row 
		XSSFCell cell = rownum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
		}
		
	}

