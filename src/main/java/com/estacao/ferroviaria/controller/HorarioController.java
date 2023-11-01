package com.estacao.ferroviaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estacao.ferroviaria.model.Horario;
import com.estacao.ferroviaria.service.HorarioService;
@Controller
@RequestMapping("/admin")
public class HorarioController {
	
	@Autowired
	private HorarioService horarioService;

	@GetMapping("/horario")
	public String showTrain(Model model) {
		model.addAttribute("horario", new Horario());
		List<Horario> listHorario = horarioService.listHorario();
		model.addAttribute("listHorario", listHorario);
		
		return "horario";
	}

	@GetMapping("/horario/add")
	public String showAddCampaign(Model model) {
		model.addAttribute("horario", new Horario());
		model.addAttribute("btnName", "Adicionar");
		return "horario-add";
	}
	

}
