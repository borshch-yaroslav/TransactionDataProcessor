package com.slavko.processor.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.slavko.processor.model.Transaction;

public class CSVParser {

	public static StringBuilder resultInfo;
	public static StringBuilder ignoredInfo;

	public static List<Transaction> getTransactionsList(File transactionsFile) {

		List<Transaction> result = new ArrayList<>();
		resultInfo = new StringBuilder("RESULT\r\n\r\n");
		ignoredInfo = new StringBuilder("");
		int successCounter = 0;
		int rowCounter = 0;

		Transaction transaction = null;

		String line = null;
		try (BufferedReader br = new BufferedReader(new FileReader(transactionsFile))) {

			// Skip first row
			line = br.readLine();
			line = br.readLine();

			while ((line = br.readLine()) != null) {

				transaction = null;
				
				transaction = new Transaction();
				rowCounter++;

				String[] fields = line.split(",");
				if (fields != null) {
					
					if (fields[0] != null && FieldValidator.validateAccount(fields[0])) {
						transaction.setAccount(fields[0]);
					} else {
						ignoredInfo
								.append("In row №" + rowCounter + " column 'account' is invalid. Must be a text.\r\n");
					}

					if (fields[1] != null && FieldValidator.validateDescription(fields[1])) {
						transaction.setDescription(fields[1]);
					} else {
						ignoredInfo.append(
								"In row №" + rowCounter + " column 'description' is invalid. Must be a text.\r\n");
					}

					if (fields[2] != null && fields[2].length() == 5 && FieldValidator.validateCurrencyCode(fields[2].substring(1, 4))) {
						transaction.setCurrencyCode(fields[2].substring(1, 3));
					} else {
						ignoredInfo.append("In row №" + rowCounter
								+ " column 'currency code' is invalid. Must be a valid ISO 4217.\r\n");
					}

					try {
						if (fields[3] != null && FieldValidator.validateAmount(Double.parseDouble(fields[3]))) {
							transaction.setAmount(Double.parseDouble(fields[3]));
							
							if (transaction != null && transaction.isFormed()) {

								result.add(transaction);
								successCounter++;
							}
						} else {
							ignoredInfo.append(
									"In row №" + rowCounter + " column 'amount' is invalid. Must be a number.\r\n");
						}
					} catch (Exception e) {
						ignoredInfo
								.append("In row №" + rowCounter + " column 'amount' is invalid. Must be a number.\r\n");
					}

				} else {
					ignoredInfo.append("Transaction in row №" + rowCounter + " hasn't all fields.\r\n");
				}
			}
		} catch (IOException e) {
			return result;
		}

		resultInfo.append("Number of successful transactions: " + successCounter + "\r\n\r\n");

		return result;
	}

}
