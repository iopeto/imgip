package org.iopeto.imgip.web.controller;

import org.iopeto.imgip.web.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	private final StorageService storageService;

	@Autowired
	public MainController(StorageService storageService) {
		this.storageService = storageService;
	}

	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("images", storageService.getAllImgs());
		model.addAttribute("dumps", storageService.getAllDumps());
		return "index";
	}
}