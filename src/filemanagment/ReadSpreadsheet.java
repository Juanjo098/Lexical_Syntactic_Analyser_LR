package filemanagment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadSpreadsheet {

    // Dependencies: POI | HSSF Workbook/Sheet/Row/Cell
    // This method will read and return Excel data into a double array
    public static String[][] SpreadsheetTo2dArray() {
        String[][] dataTable = null;
        String[][] transform = null;
        File file = new File("src\\table\\table2.xls");
        try {
            // Create a file input stream to read Excel workbook and worksheet
            FileInputStream xlfile = new FileInputStream(file);
            HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
            HSSFSheet xlSheet = xlwb.getSheetAt(0);

            ArrayList<String[]> array = new ArrayList<>();

            String cad = null;

            // Get the number of rows and columns
            int numRows = xlSheet.getLastRowNum() + 1;
            int numCols = xlSheet.getRow(0).getLastCellNum();

            // Create double array data table - rows x cols
            // We will return this data table
            dataTable = new String[numRows][numCols];

            // For each row, create a HSSFRow, then iterate through the "columns"
            // For each "column" create an HSSFCell to grab the value at the specified cell (i,j)
            for (int i = 0; i < numRows; i++) {
                HSSFRow xlRow = xlSheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    HSSFCell xlCell = xlRow.getCell(j);

                    cad = xlCell.toString();

                    if (cad.indexOf(".") != -1) {
                        cad = cad.substring(0, xlCell.toString().indexOf("."));
                    }

                    dataTable[i][j] = cad;
                }
            }
            
            for (int i = 1; i < dataTable.length; i++) {
                array.add(dataTable[i]);
            }
            
            transform = new String[array.size()][array.get(0).length];
            
            for (int i = 0; i < array.size(); i++) {
                transform[i] = array.get(i);
            }
            
        } catch (IOException e) {
            System.out.println("ERROR FILE HANDLING " + e.toString());
        }
        return transform;
    }

    public static String[] ReadTable(){
        String[][] dataTable = null;
	File file = new File("src\\table\\table2.xls");
	try {
		// Create a file input stream to read Excel workbook and worksheet
		FileInputStream xlfile = new FileInputStream(file);
		HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
		HSSFSheet xlSheet = xlwb.getSheetAt(1);

		// Get the number of rows and columns
		int numRows = xlSheet.getLastRowNum() + 1;
		int numCols = xlSheet.getRow(0).getLastCellNum();

		// Create double array data table - rows x cols
		// We will return this data table
		dataTable = new String[numRows][numCols];

		// For each row, create a HSSFRow, then iterate through the "columns"
		// For each "column" create an HSSFCell to grab the value at the specified cell (i,j)
		for (int i = 0; i < numRows; i++) {
			HSSFRow xlRow = xlSheet.getRow(i);
			for (int j = 0; j < numCols; j++) {
				HSSFCell xlCell = xlRow.getCell(j);
				dataTable[i][j] = xlCell.toString();
			}
		}
	} catch (IOException e) {
		System.out.println("ERROR FILE HANDLING " + e.toString());
	}
	return dataTable[0];
    }
    
    public static String[] Productions(){
        String[][] dataTable = null;
	File file = new File("src\\table\\table2.xls");
	try {
		// Create a file input stream to read Excel workbook and worksheet
		FileInputStream xlfile = new FileInputStream(file);
		HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
		HSSFSheet xlSheet = xlwb.getSheetAt(2);

		// Get the number of rows and columns
		int numRows = xlSheet.getLastRowNum() + 1;
		int numCols = xlSheet.getRow(0).getLastCellNum();

		// Create double array data table - rows x cols
		// We will return this data table
		dataTable = new String[numRows][numCols];

		// For each row, create a HSSFRow, then iterate through the "columns"
		// For each "column" create an HSSFCell to grab the value at the specified cell (i,j)
		for (int i = 0; i < numRows; i++) {
			HSSFRow xlRow = xlSheet.getRow(i);
			for (int j = 0; j < numCols; j++) {
				HSSFCell xlCell = xlRow.getCell(j);
                                if (xlCell.toString().equals("#"))
                                    dataTable[i][j] = "";
                                else
                                    dataTable[i][j] = xlCell.toString();
			}
		}
	} catch (IOException e) {
		System.out.println("ERROR FILE HANDLING " + e.toString());
	}
	return dataTable[0];
    }
    
    public static String[] NoTerminals(){
        String[][] dataTable = null;
	File file = new File("src\\table\\table2.xls");
	try {
		// Create a file input stream to read Excel workbook and worksheet
		FileInputStream xlfile = new FileInputStream(file);
		HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
		HSSFSheet xlSheet = xlwb.getSheetAt(3);

		// Get the number of rows and columns
		int numRows = xlSheet.getLastRowNum() + 1;
		int numCols = xlSheet.getRow(0).getLastCellNum();

		// Create double array data table - rows x cols
		// We will return this data table
		dataTable = new String[numRows][numCols];

		// For each row, create a HSSFRow, then iterate through the "columns"
		// For each "column" create an HSSFCell to grab the value at the specified cell (i,j)
		for (int i = 0; i < numRows; i++) {
			HSSFRow xlRow = xlSheet.getRow(i);
			for (int j = 0; j < numCols; j++) {
				HSSFCell xlCell = xlRow.getCell(j);
                                if (xlCell.toString().equals("#"))
                                    dataTable[i][j] = "";
                                else
                                    dataTable[i][j] = xlCell.toString();
			}
		}
	} catch (IOException e) {
		System.out.println("ERROR FILE HANDLING " + e.toString());
	}
	return dataTable[0];
    }
    
}
