package br.com.aweb.sistema_venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aweb.sistema_venda.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
