package com.slavko.processor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.slavko.processor.service.TransactionService;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping( method = RequestMethod.GET)
	public String getIndex(Model model) {
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String goToIndex(Model model) {
		return "index";
	}
}