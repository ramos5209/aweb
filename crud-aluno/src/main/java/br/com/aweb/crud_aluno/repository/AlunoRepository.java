package br.com.aweb.crud_aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aweb.crud_aluno.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
