package br.com.aweb.sistema_vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aweb.sistema_vendas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // validações úteis para não duplicar
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    
}
