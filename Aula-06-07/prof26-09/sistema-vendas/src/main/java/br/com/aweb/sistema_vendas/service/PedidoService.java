package br.com.aweb.sistema_vendas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aweb.sistema_vendas.model.Cliente;
import br.com.aweb.sistema_vendas.model.ItemPedido;
import br.com.aweb.sistema_vendas.model.Pedido;
import br.com.aweb.sistema_vendas.model.Produto;
import br.com.aweb.sistema_vendas.model.Pedido.StatusPedido;
import br.com.aweb.sistema_vendas.repository.ClienteRepository;
import br.com.aweb.sistema_vendas.repository.PedidoRepository;
import br.com.aweb.sistema_vendas.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.experimental.var;

public class PedidoService {
    //Falta Terminar de copiar no slide do professor
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    
    //CREATE - Criar novo pedido
    @Transactional
    public Pedido criarPedido(Cliente cliente){
        return pedidoRepository.save(new Pedido(cliente));
    }

    //CALCULAR VALOR TOTAL do pedido
    private void calcularValorTotal(Pedido pedido){
        BigDecimal total = BigDecimal.ZERO;

        for(ItemPedido item : pedido.getItens()){
            BigDecimal valorItem = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
            total = total.add(valorItem);
        }
    }

    public Pedido adicionarItem(Long pedidoId, Long produtoId, Integer quantidade){
        var optionalPedido = pedidoRepository.findById(pedidoId);
        var optionalProduto = produtoRepository.findById(produtoId);

        if (! optionalPedido.isPresent()) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        if (! optionalProduto.isPresent()) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

    }

    //FALTA TERMINAR
    public Pedido removerItem(Long pedidoId, Long itemId){
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
        
        if(!optionalPedido.isPresent() ||  optionalPedido.get().getStatus() == StatusPedido.CANCELADO){
            throw new IllegalArgumentException("Pedido inexistente ou cancelado!");
        }

        Pedido pedido =  optionalPedido.get();
        for(ItemPedido item : pedido.getItens()){
            if (item.getId() == itemId) {
                Produto produto = item.getProduto();
                Integer qntd = produto.getQuantidadeEmEstoque();
                 
            }
        }
    }

}
