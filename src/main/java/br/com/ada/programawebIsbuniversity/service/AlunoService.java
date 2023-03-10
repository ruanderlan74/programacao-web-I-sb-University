package br.com.ada.programawebIsbuniversity.service;


import br.com.ada.programawebIsbuniversity.model.Aluno;
import br.com.ada.programawebIsbuniversity.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public void createAluno(Aluno aluno){
        this.alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return this.alunoRepository.findAll();
    }
    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return this.alunoRepository.findById(id);
    }

    public Optional<Aluno> buscarAlunoPelaMatricula(String matricula) {
        return this.alunoRepository.findByMatricula(matricula);
    }

    public void removerAlunoPorId(Long id) {
        this.alunoRepository.deleteById(id);
    }
}