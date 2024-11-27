package br.grupointegrado.educacional.controllers;

import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.model.Turma;
import br.grupointegrado.educacional.repository.CursoRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok().body(this.turmaRepository.findAll());
    }

    public ResponseEntity<Turma> findById(@PathVariable Integer id){
        Turma turma = this.turmaRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada!"));

        return ResponseEntity.ok().body(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> insert(@RequestBody Turma data){
        Curso curso = this.cursoRepository.findById(data.getCurso().getId()).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do curso não encontrado!"));

        yearAndSemesterValidator(data);

        Turma turma = new Turma();
        turma.setAno(data.getAno());
        turma.setSemestre(data.getSemestre());
        turma.setCurso(curso);
        Turma savedTurma= turmaRepository.save(turma);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTurma);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @RequestBody Turma data){
        Turma turma = this.turmaRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id da turma não encontrado!"));

        yearAndSemesterValidator(data);

        turma.setAno(data.getAno());
        turma.setSemestre(data.getSemestre());
        Turma savedTurma= turmaRepository.save(turma);

        return ResponseEntity.status(HttpStatus.OK).body(savedTurma);
    }

    private void yearAndSemesterValidator(Turma data) {
        Integer currentYear = LocalDate.now().getYear();

        if (data.getAno() < currentYear) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira um ano válido!");
        }

        if (data.getSemestre() != 1 && data.getSemestre() != 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira um semestre válido!");
        }
    }

}
