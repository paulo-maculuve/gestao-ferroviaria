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

import com.estacao.ferroviaria.exception.PassageiroNotFundException;
import com.estacao.ferroviaria.model.Passageiro;
import com.estacao.ferroviaria.model.Reserva;
import com.estacao.ferroviaria.model.Rota;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.model.Users;
import com.estacao.ferroviaria.service.CustomUserDetailsService;
import com.estacao.ferroviaria.service.PassageiroService;
import com.estacao.ferroviaria.service.ReservaService;
import com.estacao.ferroviaria.service.RotaService;
import com.estacao.ferroviaria.service.TrainService;

@Controller
public class PassageiroController {

	@Autowired
	TrainService trainService;

	@Autowired
	private ReservaService reservaService;
	@GetMapping("/")
	public String showTrain(Model model) {
		List<Train> listTrain = trainService.listTrain();
		model.addAttribute("listTrain", listTrain);
				
		return "passageiros";
	}
	
	@GetMapping("/reserva/add")
	public String resersas(Model model) {
		List<Train> listTrain = trainService.listTrain();
		model.addAttribute("listTrain", listTrain);
		model.addAttribute("reserva", new Reserva());
		
		return "reserva-add";
		
		
	}
	@PostMapping("/reserva/save")
	public String saveUser(Reserva reserva) {
		reservaService.save(reserva);
		return "redirect:/";
	}


	

}
