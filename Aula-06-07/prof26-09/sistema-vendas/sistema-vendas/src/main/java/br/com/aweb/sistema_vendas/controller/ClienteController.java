package br.com.aweb.sistema_vendas.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.aweb.sistema_vendas.model.Cliente;
import br.com.aweb.sistema_vendas.service.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    //Listar clientes
    @GetMapping
    public ModelAndView listarClientes(){
        return new ModelAndView("cliente/list", Map.of("clientes", clienteService.listarClientes()));
    }

    @GetMapping("/novo")
    public ModelAndView novoCliente(){
        return new ModelAndView("cliente/form", Map.of("cliente", new Cliente()));
    }

    @PostMapping("/novo")
    public String novoCliente(@Valid Cliente cliente, BindingResult result){
        if(result.hasErrors())
            return "cliente/form";
        clienteService.salvar(cliente);
        
        return "redirect:/clientes";
    }   

}
