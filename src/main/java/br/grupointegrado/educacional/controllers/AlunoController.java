package br.grupointegrado.educacional.controllers;

import br.grupointegrado.educacional.dto.AlunoRequestDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Integer id) {
        return this.repository.findById(id).
                orElseThrow( () -> new IllegalArgumentException("Aluno não encontrado."));
    }

    @PostMapping
    public Aluno save(@RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setData_nascimento(dto.getDataNascimento());
        aluno.setMatricula(dto.getMatricula());

        return this.repository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id, @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id).
                orElseThrow( () -> new IllegalArgumentException("Aluno não encontrado."));

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setData_nascimento(dto.getDataNascimento());
        aluno.setMatricula(dto.getMatricula());

        return this.repository.save(aluno);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id).
                orElseThrow( () -> new IllegalArgumentException("Aluno não encontrado."));

        this.repository.delete(aluno);
    }
}

