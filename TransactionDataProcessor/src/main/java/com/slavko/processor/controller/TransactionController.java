package com.slavko.processor.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.slavko.processor.model.Transaction;
import com.slavko.processor.service.TransactionService;
import com.slavko.processor.tools.FileHandler;
import com.slavko.processor.tools.XLSXParser;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	private String message;
	
	@RequestMapping(value = "/addTransaction", method = RequestMethod.POST)
	public String addTransaction(@ModelAttribute Transaction transaction) {
		transactionService.add(transaction);
		return "redirect:/";
	}

	@RequestMapping(value = "/addTransactionsFromFile", method = RequestMethod.POST)
	public String addTransactionsFromFile(
			@RequestParam("transactionsFile") MultipartFile transactionsFile) {

		if (!transactionsFile.isEmpty()) {

			File uploadedFile = FileHandler.multipartFileToFile(transactionsFile);

			switch (FileHandler.getFileExtention(uploadedFile.getName())) {

			case "xlsx":
				transactionService.addTransactionsFromXLSX(uploadedFile);
				break;

			case "csv":
				transactionService.addTransactionsFromCSV(uploadedFile);
				break;
			}

			uploadedFile.delete();
			
			message = XLSXParser.resultInfo.toString();
			
			
			return "redirect:/finish";
		} else {
			message = "You failed to upload " + transactionsFile.getName() + " because the file was empty.";
			return "redirect:/finish";
		}
	}

	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public String finishTransaction(Model model) {
		model.addAttribute("transactions", transactionService.getAll());
	    model.addAttribute("message", message);
	    return "index";
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String lookTransactions(Model model) {
		model.addAttribute("transactions", transactionService.getAll());
	    return "transactions";
	}
	
	@RequestMapping(value = "/removeTransaction", method = RequestMethod.POST)
	public String removePerson(@ModelAttribute Transaction transaction) {
		transactionService.remove(transaction);
		return "redirect:/";
	}
}