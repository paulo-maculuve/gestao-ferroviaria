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
import org.springframework.web.bind.annotation.RestController;

import com.estacao.ferroviaria.exception.EstacaoNotFundException;
import com.estacao.ferroviaria.exception.FuncionarioNotFundException;
import com.estacao.ferroviaria.model.Estacao;
import com.estacao.ferroviaria.model.Funcionario;
import com.estacao.ferroviaria.service.CustomUserDetailsService;
import com.estacao.ferroviaria.service.EstacaoService;

@Controller
@RequestMapping("/admin")
public class EstacaoController {

	@Autowired
	private EstacaoService estacaoService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@GetMapping("/estacao")
	public String showEstacao(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nome;
		String email;

		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			email = ((UserDetails) principal).getUsername();
			nome = customUserDetailsService.findUsernameByEmail(email);

		} else {
			nome = principal.toString();
			email = principal.toString();
		}
		model.addAttribute("username", nome);
		model.addAttribute("email", email);
		List<Estacao> listEstacao = estacaoService.listEstacao();
		model.addAttribute("estacao", new Estacao());
		model.addAttribute("listEstacao", listEstacao);
		return "estacao";
	}

	@GetMapping("/estacao/add")
	public String showAddCampaign(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nome;
		String email;

		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			email = ((UserDetails) principal).getUsername();
			nome = customUserDetailsService.findUsernameByEmail(email);

		} else {
			nome = principal.toString();
			email = principal.toString();
		}
		model.addAttribute("username", nome);
		model.addAttribute("email", email);

		model.addAttribute("estacao", new Estacao());
		model.addAttribute("btnName", "Adicionar");
		return "estacao-add";
	}

	@PostMapping("/estacao/save")
	public String saveCampaign(Estacao estacao) {

		estacaoService.save(estacao);
		return "redirect:/admin/estacao";
	}

	@GetMapping("/estacao/edit/{id}")
	public String showEditTrain(@PathVariable("id") Long id, Model model) {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String nome;
			String email;

			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				email = ((UserDetails) principal).getUsername();
				nome = customUserDetailsService.findUsernameByEmail(email);

			} else {
				nome = principal.toString();
				email = principal.toString();
			}
			model.addAttribute("username", nome);
			model.addAttribute("email", email);

			Estacao estacao = estacaoService.getEstacao(id);
			model.addAttribute("estacao", estacao);
			model.addAttribute("btnName", "Update");
			return "estacao-edit";
		} catch (EstacaoNotFundException e) {
			e.printStackTrace();
			return "redirect:/admin/estacao";
		}

	}

	@GetMapping("/estacao/delete/{id}")
	public String deleteTrain(@PathVariable("id") Long id) {
		try {
			estacaoService.deleteEstacao(id);
		} catch (EstacaoNotFundException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/estacao";
	}
}
