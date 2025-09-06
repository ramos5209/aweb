package br.com.aweb.to_do_list.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min = 3, max = 100)
    @NotBlank
    @Column(length = 100,nullable = false)
    private String title;
    
    @Column(nullable = false)
    private LocalDateTime createdAt= LocalDateTime.now();
    
    @NotNull
    @FutureOrPresent
    @DateTimeFormat(iso = ISO.DATE)
    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = true)
    private LocalDate finishedAt;
}
