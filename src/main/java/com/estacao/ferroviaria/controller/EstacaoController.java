package com.estacao.ferroviaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacao.ferroviaria.model.Estacao;
import com.estacao.ferroviaria.service.EstacaoService;

@Controller
@RestController("/admin")
public class EstacaoController {

	@Autowired
	private EstacaoService estacaoService;

	@GetMapping("/estacao")
	public String showTrain(Model model) {
		model.addAttribute("estacao", new Estacao());
		List<Estacao> listEstacao = estacaoService.listEstacao();
		model.addAttribute("listEstacao", listEstacao);
		
		return "estacao";
	}

	@GetMapping("/estacao/add")
	public String showAddCampaign(Model model) {
		model.addAttribute("estacao", new Estacao());
		model.addAttribute("btnName", "Adicionar");
		return "estacao-add";
	}
}
