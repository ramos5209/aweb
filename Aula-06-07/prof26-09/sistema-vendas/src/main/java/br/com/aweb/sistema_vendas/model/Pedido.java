package br.com.aweb.sistema_vendas.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @NotNull(message = "Cliente é obrigatorio")
    @ManyToOne
    @JoinColumn(name="cliente_id", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private StatusPedido status = StatusPedido.ATIVO;

    @PositiveOrZero
    @Column(nullable =  false, precision = 10, scale = 2)
    private BigDecimal precoTotal = BigDecimal.ZERO;

    public enum StatusPedido {
        ATIVO,
        CANCELADO

    }

    //o papel do cascade (propaga operações de salvar/atualizar/excluir) e do orphanRemoval (remove automaticamente itens “órfãos”).
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime dataPedido = LocalDateTime.now();

    @Version
    private Long version;

    //Construtor personalizado
    public Pedido(Cliente cliente){
        this.cliente = cliente;
    }
    



}
