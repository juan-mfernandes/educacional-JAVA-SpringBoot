package br.grupointegrado.educacional.controllers;


import br.grupointegrado.educacional.dto.ProfessorRequestDTO;
import br.grupointegrado.educacional.model.Professor;
import br.grupointegrado.educacional.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll(){
        return ResponseEntity.ok(this.professorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById( @PathVariable Integer id) {
        Professor professor = this.professorRepository.findById(id).
                orElseThrow( () -> new IllegalArgumentException("Professor não encontrado."));

        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<Professor> insert( @RequestBody @Valid ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setTelefone(dto.getTelefone());
        professor.setEspecialidade(dto.getEspecialidade());

        return ResponseEntity.ok(this.professorRepository.save(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update( @RequestBody @Valid ProfessorRequestDTO dto, @PathVariable Integer id) {
        Professor professor = this.professorRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Professor não cadastrado."));

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setTelefone(dto.getTelefone());
        professor.setEspecialidade(dto.getEspecialidade());

        return ResponseEntity.ok(this.professorRepository.save(professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Professor> delete( @PathVariable Integer id) {
        Professor professor = this.professorRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        this.professorRepository.delete(professor);
        return ResponseEntity.ok(professor);
    }
}
