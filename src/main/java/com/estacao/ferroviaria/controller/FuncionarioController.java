package com.estacao.ferroviaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estacao.ferroviaria.exception.FuncionarioNotFundException;
import com.estacao.ferroviaria.exception.TrainNotFundException;
import com.estacao.ferroviaria.model.Estacao;
import com.estacao.ferroviaria.model.Funcionario;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.service.EstacaoService;
import com.estacao.ferroviaria.service.FuncionarioService;
import com.estacao.ferroviaria.service.TrainService;

@Controller
@RequestMapping("/admin")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private EstacaoService estacaoService;
	
	@Autowired
	private TrainService trainService;

	@GetMapping("/funcionario")
	public String showTrain(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		List<Funcionario> listFuc = funcionarioService.listFunc();
		model.addAttribute("listFuc", listFuc);
		
		return "funcionario";
	}

	@GetMapping("/funcionario/add")
	public String showAddCampaign(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		List<Train> listTrain = trainService.listTrain();
		List<Estacao> listEstacao = estacaoService.listEstacao();
		model.addAttribute("listEstacao", listEstacao);
		model.addAttribute("listTrain", listTrain);
		model.addAttribute("btnName", "Adicionar");
		return "funcionario-add";
	}

	@PostMapping("/funcionario/save")
	public String saveCampaign(Funcionario funcionario) {
		funcionarioService.save(funcionario);
		return "redirect:/admin/funcionario";
	}

	@GetMapping("/funcionario/edit/{id}")
	public String showEditFuncionario(@PathVariable("id") Long id, Model model) {
		try {
			Funcionario funcionario = funcionarioService.getFunc(id);
			List<Train> listTrain = trainService.listTrain();
			List<Estacao> listEstacao = estacaoService.listEstacao();
			model.addAttribute("listEstacao", listEstacao);
			model.addAttribute("listTrain", listTrain);
			model.addAttribute("funcionario", funcionario);
			model.addAttribute("btnName", "Update");
            return "funcionario-edit";
		} catch (FuncionarioNotFundException e) {
			e.printStackTrace();
			return "redirect:/admin/funcionario";
		}
		
	}

	@GetMapping("/funcionario/delete/{id}")
	public String deleteFuncionario(@PathVariable("id") Long id) {
		try {
			funcionarioService.deleteFunc(id);
		} catch (FuncionarioNotFundException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/funcionario";
	}

}
