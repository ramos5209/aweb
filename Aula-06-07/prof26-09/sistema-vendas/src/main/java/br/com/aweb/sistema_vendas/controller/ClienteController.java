package br.com.aweb.sistema_vendas.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import br.com.aweb.sistema_vendas.model.Cliente;
import br.com.aweb.sistema_vendas.service.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar clientes
    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("cliente/list", Map.of("clientes", clienteService.listarTodos()));
    }

    // Formulário de cadastro
    @GetMapping("/novo")
    public ModelAndView create() {
        return new ModelAndView("cliente/form", Map.of("cliente", new Cliente()));
    }

    // Salvar cliente
    @PostMapping("/novo")
    public String create(@Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cliente/form";
        }

        try {
            clienteService.salvar(cliente);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("CPF")) {
                result.rejectValue("cpf", "error.cliente", e.getMessage());
            } else if (e.getMessage().contains("E-mail")) {
                result.rejectValue("email", "error.cliente", e.getMessage());
            }
            return "cliente/form";
        }

        return "redirect:/clientes";
    }

    // Formulário de edição
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var optionalCliente = clienteService.buscarPorId(id);
        if (optionalCliente.isPresent()) {
            return new ModelAndView("cliente/form", Map.of("cliente", optionalCliente.get()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Atualizar cliente
    @PostMapping("/edit/{id}")
    public String edit(@Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cliente/form";
        }

        try {
            clienteService.atualizar(cliente.getId(), cliente);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("CPF")) {
                result.rejectValue("cpf", "error.cliente", e.getMessage());
            } else if (e.getMessage().contains("E-mail")) {
                result.rejectValue("email", "error.cliente", e.getMessage());
            }
            return "cliente/form";
        }

        return "redirect:/clientes";
    }

    // Excluir cliente
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        var optionalCliente = clienteService.buscarPorId(id);
        if (optionalCliente.isPresent()) {
            return new ModelAndView("cliente/delete", Map.of("cliente", optionalCliente.get()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete/{id}")
    public String delete(Cliente cliente) {
        clienteService.excluir(cliente.getId());
        return "redirect:/clientes";
    }

}
