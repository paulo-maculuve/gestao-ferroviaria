package com.estacao.ferroviaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estacao.ferroviaria.exception.HorarioNotFundException;
import com.estacao.ferroviaria.exception.RotaNotFundException;
import com.estacao.ferroviaria.model.Horario;
import com.estacao.ferroviaria.model.Rota;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.service.HorarioService;
import com.estacao.ferroviaria.service.RotaService;
import com.estacao.ferroviaria.service.TrainService;

@Controller
@RequestMapping("/admin")
public class HorarioController {

	@Autowired
	private HorarioService horarioService;
	
	@Autowired 
	private TrainService trainService;
	
	

	@GetMapping("/horario")
	public String showHorario(Model model) {
		model.addAttribute("horario", new Horario());
		List<Horario> listHorario = horarioService.listHorario();
		model.addAttribute("listHorario", listHorario);

		return "horario";
	}

	@GetMapping("/horario/add")
	public String showAddHorario(Model model) {
		model.addAttribute("horario", new Horario());
		List<Train> listTrain = trainService.listTrain();
		model.addAttribute("listTrain", listTrain);
		model.addAttribute("btnName", "Adicionar");
		return "horario-add";
	}

	@PostMapping("/horario/save")
	public String saveCampaign(Horario horario) {
		horarioService.save(horario);
		return "redirect:/admin/horario";
	}

	@GetMapping("/horario/edit/{id}")
	public String showEditHorario(@PathVariable("id") Long id, Model model) {
		try {
			Horario horario = horarioService.getHorario(id);
			model.addAttribute("horario", horario);
			model.addAttribute("btnName", "Update");
			return "horario-edit";
		} catch (HorarioNotFundException e) {
			e.printStackTrace();
			return "redirect:/admin/horario";
		}

	}

	@GetMapping("/horario/delete/{id}")
	public String deleteHorario(@PathVariable("id") Long id) {
		try {
			horarioService.deleteHorario(id);
		} catch (HorarioNotFundException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/horario";
	}

}
