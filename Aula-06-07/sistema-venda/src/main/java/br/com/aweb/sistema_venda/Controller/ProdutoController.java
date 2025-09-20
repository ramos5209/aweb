package br.com.aweb.sistema_venda.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.aweb.sistema_venda.Service.ProdutoService;
import br.com.aweb.sistema_venda.model.Produto;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    // Formulario de cadastro
    @GetMapping("/novo")
    public ModelAndView create() {
        return new ModelAndView("produto/form", Map.of("produto", new Produto()));
    }

    // Salvar produto
    @PostMapping("/novo")
    public String create(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors())
            return "produto/form";

        produtoService.salvar(produto);

        return "redirect:/produtos";
    }
}
