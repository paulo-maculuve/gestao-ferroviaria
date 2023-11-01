package com.estacao.ferroviaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/admin")
	public String showHomePage(Model model) {
		return "index";
	}

	@GetMapping({"/login", "/logout"})
    public String showLoginPage(Model model){
            return "login";


    }
    
    @GetMapping({"/logout"})
    public String showLogoutPage(Model model){
            return "login";


    }
}
