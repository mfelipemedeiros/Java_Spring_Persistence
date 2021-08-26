package com.example.persistencia;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmpregoController {
    
    @Autowired//faciliata a interação com o banco de dados.
    EmpregoRepository empregoRepository;

    @RequestMapping("/")//Retornará todos o empregos que estão no banco de dados
    public String listaEmpregos(Model model){
        model.addAttribute("empregos", empregoRepository.findAll());
        return "lista";
    }

    @GetMapping("/add")//Vai criar um novo objeto e possibilita adicionar um novo emprego ao banco de dados
    public String empregoForm(Model model){
        model.addAttribute("emprego", new Emprego());
        return "empregoForm";
    }

    @PostMapping("/process")
    public String processForm(@Valid Emprego emprego, BindingResult result){
        if(result.hasErrors()){
            return "empregoForm";
        }
        empregoRepository.save(emprego);
        return "redirect:/";
    }
}
