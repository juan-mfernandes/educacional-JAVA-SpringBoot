package br.grupointegrado.educacional.controllers;

import br.grupointegrado.educacional.dto.MatriculaRequestDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.Turma;
import br.grupointegrado.educacional.repository.AlunoRepository;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {
        return ResponseEntity.ok(matriculaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id) {
        Matricula matricula = matriculaRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula inexistente!"));

        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Matricula> insert(@RequestBody @Valid MatriculaRequestDTO dto) {
        Aluno aluno = this.alunoRepository.findById(dto.getAlunoId())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno inexistente!"));

        Turma turma = this.turmaRepository.findById(dto.getTurmaId()).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma inexistente!"));

        boolean matriculaExists = this.matriculaRepository.existsByAlunoAndTurma(aluno, turma);
        if (matriculaExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A matricula ja existe!");
        }
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setTurma(turma);

        Matricula savedMatricula = this.matriculaRepository.save(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMatricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Integer id, @RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada!"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada!"));

        matricula.setTurma(turma);

        Matricula updatedMatricula = matriculaRepository.save(matricula);
        return ResponseEntity.ok(updatedMatricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Matricula> delete(@PathVariable Integer id) {
        Matricula matricula = this.matriculaRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula inexistente!"));

        this.matriculaRepository.delete(matricula);
        return ResponseEntity.status(HttpStatus.OK).body(matricula);
    }

}
