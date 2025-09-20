package br.com.aweb.sistema_venda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aweb.sistema_venda.model.Produto;
import br.com.aweb.sistema_venda.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;
    
    //CREATE
    @Transactional
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }
}
