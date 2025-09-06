package br.com.aweb.crud_aluno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aweb.crud_aluno.model.Aluno;
import br.com.aweb.crud_aluno.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    // Buscar todos os alunos
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    // Buscar aluno por ID
    public Aluno buscarPorId(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            return aluno.get();
        }
        throw new RuntimeException("Aluno não existe");
    }

    // Criar ou atualizar aluno
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    // Excluir aluno
    public void excluir(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("Erro ao excluir: não foi possivel encontrar o aluno");
        }
        alunoRepository.deleteById(id);
    }
}
