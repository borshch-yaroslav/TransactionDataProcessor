package com.slavko.processor.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.slavko.processor.model.Transaction;

public class XLSXParser {

	public static StringBuilder resultInfo;
	public static StringBuilder ignoredInfo;

	public static List<Transaction> getTransactionsList(File transactionsFile) {

		List<Transaction> result = new ArrayList<>();
		resultInfo = new StringBuilder("RESULT\r\n\r\n");
		ignoredInfo = new StringBuilder("");
		int successCounter = 0;

		try {
			@SuppressWarnings("resource")
			XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(transactionsFile));
			// Get first sheet from the workbook
			XSSFSheet sheet = wBook.getSheetAt(0);
			Row row;
			Cell cell;
			Transaction transaction = null;

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			// Skip first row
			if (rowIterator.hasNext()) {
				row = rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				
				if(row.getFirstCellNum() > 0 ){
					ignoredInfo.append("In row №" + row.getRowNum() + " first column is empty.\r\n");
					row = rowIterator.next();
				}
				
				int counter = 0;

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();

				transaction = new Transaction();

				while (cellIterator.hasNext()) {

					counter++;
					cell = cellIterator.next();

					switch (counter) {

					case 1:
						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								&& FieldValidator.validateAccount(cell.getStringCellValue())) {
							transaction.setAccount(cell.getStringCellValue());
						} else {
							ignoredInfo.append("In row №" + row.getRowNum() + " column 'account' is invalid. Must be a text.\r\n");
						}
						break;

					case 2:
						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								&& FieldValidator.validateDescription(cell.getStringCellValue())) {
							transaction.setDescription(cell.getStringCellValue());
						} else {
							ignoredInfo.append("In row №" + row.getRowNum() + " column 'description' is invalid. Must be a text.\r\n");
						}
						break;

					case 3:
						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								&& FieldValidator.validateCurrencyCode(cell.getStringCellValue())) {
							transaction.setCurrencyCode(cell.getStringCellValue());
						} else {
							ignoredInfo
									.append("In row №" + row.getRowNum() + " column 'currency code' is invalid. Must be a valid ISO 4217\r\n");
						}
						break;
					case 4:
						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
								&& FieldValidator.validateAmount(cell.getNumericCellValue())) {

							transaction.setAmount(cell.getNumericCellValue());

							if (transaction != null && transaction.isFormed()) {

								result.add(transaction);
								successCounter++;
							}

						} else {
							ignoredInfo.append("In row №" + row.getRowNum() + " column 'amount' is invalid. Must be anumber.\r\n");
						}
						break;
					default:
						break;
					}
				}
				if (transaction != null && !transaction.isFormed())
					ignoredInfo.append("Transaction in row №" + row.getRowNum() + " hasn't all fields.\r\n");
				transaction = null;
			}
		} catch (IOException e) {
			return result;
		}
		resultInfo.append("Number of successful transactions: " + successCounter + "\r\n\r\n");

		return result;
	}

}
