package br.com.aweb.to_do_list.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @GetMapping("/home")
    public ModelAndView home(){
        var modelAndView = new ModelAndView("home");
        modelAndView.addObject("professor", "Andre Roberto da Silva");
        var alunos = List.of(
            "Isaac Newton", 
            "Albert Eistein",
            "Marie Curie");
        modelAndView.addObject("alunos", alunos);
        modelAndView.addObject("ehVerdade", false);
        return modelAndView;
    }

    
}
