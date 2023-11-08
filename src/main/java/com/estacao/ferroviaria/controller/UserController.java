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

import com.estacao.ferroviaria.exception.UserNotFundException;
import com.estacao.ferroviaria.model.Users;
import com.estacao.ferroviaria.service.UserService;



@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/usuario")
    public String showUserList(Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String nome;    

    	if (principal instanceof UserDetails) {
    	    nome = ((UserDetails)principal).getUsername();
    	} else {
    	    nome = principal.toString();
    	}
    	model.addAttribute("username", nome);
        List<Users> userList = userService.userList();
        model.addAttribute("listUsers", userList);
        return "usuario";
    }

    @GetMapping("/usuario/add")
    public String showAddUser(Model model){
        model.addAttribute("user", new Users());
        model.addAttribute("btnName", "Adicionar");
        return "usuario-add";
    }

    @PostMapping("/usuario/save")
    public String saveUser(Users user){
        userService.save(user);
        return "redirect:/admin/usuario";
    }

    @GetMapping("/usuario/edit/{id}")
    public String showEditUser(@PathVariable("id") Long id, Model model){
        try {
           Users user = userService.getUser(id);
           model.addAttribute("user", user);
            model.addAttribute("btnName", "Update");
            return "usuario-edit";
        }catch (UserNotFundException e){
            e.printStackTrace();
            return "redirect:/admin/usuario";
        }
    }

    @GetMapping("/usuario/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        try {
            userService.deleteUser(id);
        }catch (UserNotFundException e){
            e.printStackTrace();
        }
        return "redirect:/admin/usuario";
    }

}