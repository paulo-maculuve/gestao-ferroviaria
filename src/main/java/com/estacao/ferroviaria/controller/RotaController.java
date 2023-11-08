package com.estacao.ferroviaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estacao.ferroviaria.exception.RotaNotFundException;
import com.estacao.ferroviaria.model.Rota;
import com.estacao.ferroviaria.service.CustomUserDetailsService;
import com.estacao.ferroviaria.service.RotaService;

@Controller
@RequestMapping("/admin")
public class RotaController {

	@Autowired
	RotaService rotaService;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@GetMapping("/rota")
	public String showTrain(Model model) {
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
		model.addAttribute("rota", new Rota());
		List<Rota> listRota = rotaService.listRota();
		model.addAttribute("listRota", listRota);
		
		return "rota";
	}

	@GetMapping("/rota/add")
	public String showAddCampaign(Model model) {
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
		model.addAttribute("rota", new Rota());
		model.addAttribute("btnName", "Adicionar");
		return "rota-add";
	}

	@PostMapping("/rota/save")
	public String saveCampaign(Rota rota) {
		rotaService.save(rota);
		return "redirect:/admin/rota";
	}

	@GetMapping("/rota/edit/{id}")
	public String showEditTrain(@PathVariable("id") Long id, Model model) {
		try {
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
			Rota rota = rotaService.getRota(id);
			model.addAttribute("rota", rota);
			model.addAttribute("btnName", "Update");
            return "rota-edit";
		} catch (RotaNotFundException e) {
			e.printStackTrace();
			return "redirect:/admin/rota";
		}
		
	}

	@GetMapping("/rota/delete/{id}")
	public String deleteRota(@PathVariable("id") Long id) {
		try {
			rotaService.deleteRota(id);
		} catch (RotaNotFundException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/rota";
	}
}
