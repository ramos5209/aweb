package br.com.aweb.crud_aluno.controller;

import br.com.aweb.crud_aluno.model.Aluno;
import br.com.aweb.crud_aluno.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("alunos", alunoService.findAll());
        return "list";
    }

    @GetMapping("/novo")
    public String create(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "form";
    }

    @GetMapping("/editar/{id}")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        try {
            Aluno aluno = alunoService.buscarPorId(id);
            model.addAttribute("aluno", aluno);
            return "form";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/alunos";
        }
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("aluno") Aluno aluno,
                       BindingResult result,
                       RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "form";
        }

        alunoService.salvar(aluno);
        attributes.addFlashAttribute("message", "Aluno salvo com sucesso!");
        return "redirect:/alunos";
    }

    @GetMapping("/excluir/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            alunoService.excluir(id);
            attributes.addFlashAttribute("message", "Aluno excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/alunos";
    }
}
