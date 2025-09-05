package br.com.aweb.to_do_list.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor //Cria construtores sem argumentos
@AllArgsConstructor // cria construtores com todos os argumentos
//@Data
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDateTime createdAt= LocalDateTime.now();
    @Column(nullable = false)
    private LocalDate deadline;
    @Column(nullable = true)
    private LocalDate finishedAt;
}
