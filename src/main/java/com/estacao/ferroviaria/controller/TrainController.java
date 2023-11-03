package com.estacao.ferroviaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estacao.ferroviaria.exception.TrainNotFundException;
import com.estacao.ferroviaria.model.Rota;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.service.RotaService;
import com.estacao.ferroviaria.service.TrainService;

@Controller
@RequestMapping("/admin")
public class TrainController {

	@Autowired
	private TrainService trainService;
	@Autowired
	RotaService rotaService;

	@GetMapping("/train")
	public String showTrain(Model model) {
		model.addAttribute("train", new Train());
		List<Train> listTrain = trainService.listTrain();
		model.addAttribute("listTrain", listTrain);
		
		return "train";
	}

	@GetMapping("/train/add")
	public String showAddTrain(Model model) {
	    model.addAttribute("train", new Train());
	    List<Rota> list = rotaService.listRota();
	    model.addAttribute("btnName", "Adicionar");
	    model.addAttribute("listRota", list);
	    return "train-add";
	}

	@PostMapping("/train/save")
	public String saveCampaign(Train train) {
		trainService.save(train);
		return "redirect:/admin/train";
	}

	@GetMapping("/train/edit/{id}")
	public String showEditTrain(@PathVariable("id") Long id, Model model) {
		try {
			Train train = trainService.getTrain(id);
			model.addAttribute("train", train);
			model.addAttribute("btnName", "Update");
            return "train-edit";
		} catch (TrainNotFundException e) {
			e.printStackTrace();
			return "redirect:/admin/train";
		}
		
	}

	@GetMapping("/train/delete/{id}")
	public String deleteTrain(@PathVariable("id") Long id) {
		try {
			trainService.deleteTrain(id);
		} catch (TrainNotFundException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/train";
	}

}
