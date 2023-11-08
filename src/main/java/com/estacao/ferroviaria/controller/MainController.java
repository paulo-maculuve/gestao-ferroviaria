package com.estacao.ferroviaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estacao.ferroviaria.service.CustomUserDetailsService;

@Controller
public class MainController {
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	@GetMapping("/admin")
	public String showHomePage(Model model) {
    	
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String nome; 
    	String email;

    	if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            email = ((UserDetails)principal).getUsername();
            nome = customUserDetailsService.findUsernameByEmail(email);
            
        } else {
            nome = principal.toString();
            email = principal.toString();
        }
    	model.addAttribute("username", nome);
    	model.addAttribute("email", email);
		return "index";
	}

	@GetMapping({"/login", "/logout"})
    public String showLoginPage(Model model){
            return "auth-signin";


    }
    
    @GetMapping({"/logout"})
    public String showLogoutPage(Model model){
            return "auth-signin";


    }
}
