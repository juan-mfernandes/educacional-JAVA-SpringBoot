package br.grupointegrado.educacional.controllers;

import br.grupointegrado.educacional.dto.DisciplinaRequestDTO;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Professor;
import br.grupointegrado.educacional.repository.CursoRepository;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import br.grupointegrado.educacional.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok().body(this.disciplinaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id){
        Disciplina disciplina = this.disciplinaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada!")
        );
        return ResponseEntity.ok().body(disciplina);
    }

    @PostMapping
    public ResponseEntity<Disciplina> insert(@RequestBody @Valid DisciplinaRequestDTO dto) {

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.getNome());
        disciplina.setCodigo(dto.getCodigo());

        // Vincula curso se cursoId for enviado
        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado!"));
            disciplina.setCurso(curso);
        }

        // Vincula professor se professorId for enviado
        if (dto.getProfessorId() != null) {
            Professor professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado!"));
            disciplina.setProfessor(professor);
        }

        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @RequestBody @Valid DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.disciplinaRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Disciplina inexistente!"));

        disciplina.setNome(dto.getNome());
        disciplina.setCodigo(dto.getCodigo());

        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado!"));
            disciplina.setCurso(curso);
        } else {
            disciplina.setCurso(null);
        }

        if (dto.getProfessorId() != null) {
            Professor professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado!"));
            disciplina.setProfessor(professor);
        } else {
            disciplina.setProfessor(null);
        }

        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);
        return ResponseEntity.status(HttpStatus.OK).body(savedDisciplina);
    }

    // Adicionar curso para a disciplina
    @PutMapping("/{id}/add-curso/{cursoId}")
    public ResponseEntity<Disciplina> addCurso(@PathVariable Integer id, @PathVariable Integer cursoId) {
        Disciplina disciplina = this.disciplinaRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id da disciplina não encontrado!"));

        Curso curso = this.cursoRepository.findById(cursoId).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do curso não encontrado!"));

        if(disciplina.getCurso().getId().equals(curso.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O curso ja está cadastrado na disciplina!");
        }

        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedDisciplina);
    }

    // Adicionar professor na disciplina
    @PostMapping("/{id}/add-professor/{professorId}")
    public ResponseEntity<Disciplina> addProfessor(@PathVariable Integer id, @PathVariable Integer professorId) {
        Disciplina disciplina = this.disciplinaRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id da disciplina não encontrado!"));

        Professor professor = this.professorRepository.findById(professorId).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do professor não foi encontrado!"));

        if(disciplina.getProfessor().getId().equals(professor.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O professor ja está cadastrado na disciplina!");
        }

        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedDisciplina);
    }
}
