package com.estacao.ferroviaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estacao.ferroviaria.exception.PassageiroNotFundException;
import com.estacao.ferroviaria.model.Passageiro;
import com.estacao.ferroviaria.model.Rota;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.service.PassageiroService;
import com.estacao.ferroviaria.service.RotaService;
import com.estacao.ferroviaria.service.TrainService;

@Controller
@RequestMapping("/admin")
public class PassageiroController {

	@Autowired
	private PassageiroService passageiroService;
	@Autowired
	TrainService trainService;

	@GetMapping("/passageiro")
	public String showTrain(Model model) {
//		List<Train> trains = trainService.listTrain();
//		model.addAttribute("train", trains);
		List<Passageiro> listPass = passageiroService.listPassageiro();
		model.addAttribute("listPass", listPass);
		
		return "passageiro";
	}

	@GetMapping("/passageiro/add")
	public String showAddPassageiro(Model model) {
		model.addAttribute("passageiro", new Passageiro());
		List<Train> list = trainService.listTrain();
		model.addAttribute("btnName", "Adicionar");
		model.addAttribute("listTrain", list);
		return "passageiro-add";
	}

	@PostMapping("/passageiro/save")
	public String savePassageiro(Passageiro passageiro) {

		passageiroService.save(passageiro);
		return "redirect:/passageiro";
	}

	@GetMapping("/passageiro/edit/{id}")
	public String showEditPassageiro(@PathVariable("id") Long id, Model model) {
		try {
			Passageiro passageiro = passageiroService.getPassageiro(id);
			model.addAttribute("passageiro", passageiro);
			model.addAttribute("btnName", "Update");
            return "passageiro-add";
		} catch (PassageiroNotFundException e) {
			e.printStackTrace();
			return "redirect:/passageiro";
		}
		
	}

	@GetMapping("/passageiro/delete/{id}")
	public String deletePassageiro(@PathVariable("id") Long id) {
		try {
			passageiroService.deletePassageiro(id);
		} catch (PassageiroNotFundException e) {
			e.printStackTrace();
		}
		return "redirect:/passageiro";
	}

}
