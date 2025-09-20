package br.com.aweb.sistema_venda.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(nullable = false, length = 250)
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message =  "O valor deve ser maior que zero")
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message =  "O valor deve ser maior ou igual a zero")
    @Column(nullable = false)
    private Integer quantidadeEmEstoque;
    
}
