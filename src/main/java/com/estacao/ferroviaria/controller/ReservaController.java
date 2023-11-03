package com.estacao.ferroviaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estacao.ferroviaria.exception.ReservaNotFundException;
import com.estacao.ferroviaria.model.Funcionario;
import com.estacao.ferroviaria.model.Passageiro;
import com.estacao.ferroviaria.model.Reserva;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.service.PassageiroService;
import com.estacao.ferroviaria.service.ReservaService;
import com.estacao.ferroviaria.service.TrainService;

@Controller
@RequestMapping("/admin")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private PassageiroService passageiroService;
	
	@Autowired
	private TrainService trainService;
	
	@GetMapping("/reserva")
	public String showTrain(Model model) {
		model.addAttribute("reserva", new Reserva());
		List<Reserva> listRese= reservaService.listReserva();
		model.addAttribute("listRese", listRese);
		
		return "reserva";
	}

	@GetMapping("/reserva/add")
	public String showAddCampaign(Model model) {
		model.addAttribute("reserva", new Reserva());
		List<Passageiro> passageiros = passageiroService.listPassageiro();
		List<Train> train = trainService.listTrain();
		model.addAttribute("trains", train);
		model.addAttribute("passageiros", passageiros);
		model.addAttribute("btnName", "Adicionar");
		return "reserva-add";
	}

	@PostMapping("/reserva/save")
	public String saveReserva(Reserva reserva) {
		reservaService.save(reserva);
		return "redirect:/admin/reserva";
	}

	@GetMapping("/reserva/edit/{id}")
	public String showEditReserva(@PathVariable("id") Long id, Model model) {
		try {
			Reserva reserva = reservaService.getReserva(id);
			model.addAttribute("reserva", reserva);
			model.addAttribute("btnName", "Update");
            return "reserva-edit";
		} catch (ReservaNotFundException e) {
			e.printStackTrace();
			return "redirect:/admin/reserva";
		}
		
	}

	@GetMapping("/reserva/delete/{id}")
	public String deleteReserva(@PathVariable("id") Long id) {
		try {
			reservaService.deleteReserva(id);
		} catch (ReservaNotFundException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/reserva";
	}

}
