package br.com.aweb.sistema_vendas.model;

import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.context.properties.bind.Name;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O E-mail é obrigatorio")
    @Column(nullable = false, unique = true)
    @Email(message = "E-mail invalido!")
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    @CPF(message = "CPF invalido!")
    private String cpf;

    @NotBlank
    @Size(min = 10, max = 11, message = "O numero deve ter entre 10 e 11 digitos (DDD+numero)")
    @Column(nullable = false)
    private String telefone;

    @NotBlank
    @Column(nullable = false)
    private String rua;

    @Column(nullable = true)
    private Integer numero;

    @Column(nullable = true)
    private String Complemento;

    @NotBlank
    @Column(nullable = false)
    private String bairro;
    
    @NotBlank
    @Column(nullable = false)
    private String cidade;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 2, message = "Digite um estado valido!" )
    private String estado;

    @Size(min = 12, max = 12, message = "Digite o CPF completo")
    private String CEP;


}
