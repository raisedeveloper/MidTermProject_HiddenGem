package com.example.HiddenGem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@GetMapping("/map")
	public String map(HttpSession session, Model model) {
		model.addAttribute("address", "경기도 수원시 팔달구 효원로292번길 67");
		model.addAttribute("title", "유치회관");
		return "boardC/api";
	}
}

