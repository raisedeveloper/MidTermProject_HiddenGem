package com.example.HiddenGem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/css")
public class cssController {
	@GetMapping("/cTest")
	public String css() {
		return "fragments/index";
	}
}
