package br.com.aweb.sistema_vendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aweb.sistema_vendas.model.Cliente;
import br.com.aweb.sistema_vendas.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    //Create
    @Transactional
    public Cliente salvar(Cliente cliente){
        if(clienteRepository.existsByEmail(cliente.getEmail()))
            throw new IllegalArgumentException("E-mail já cadastrado");

        if(clienteRepository.existsBycpf(cliente.getCpf()))
            throw new IllegalArgumentException("CPF já cadastrado");

        return clienteRepository.save(cliente);
    }

    //Read
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }
}
