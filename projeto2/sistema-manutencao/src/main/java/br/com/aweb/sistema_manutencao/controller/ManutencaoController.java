package br.com.aweb.sistema_manutencao.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.aweb.sistema_manutencao.model.Manutencao;
import br.com.aweb.sistema_manutencao.repository.ManutencaoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sistema-manutencao")
public class ManutencaoController {
    
    @Autowired
    ManutencaoRepository manutencaoRepository;

    @GetMapping
    public ModelAndView list(){
        return new ModelAndView("list", Map.of("manutencoes", manutencaoRepository.findAll()));
    }

    @GetMapping("/nova-manutencao")
    public ModelAndView nova(){
        return new ModelAndView("form", Map.of("manutencao", new Manutencao()));
    }

    @PostMapping("/nova-manutencao")
    public String nova(@Valid Manutencao manutencao, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors())
            return "form";
        
        manutencaoRepository.save(manutencao);
        
        return "Redirect:/sistema-manutencao";

    }
}
