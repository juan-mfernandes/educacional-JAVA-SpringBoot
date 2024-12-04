package br.grupointegrado.educacional.controllers;

import br.grupointegrado.educacional.dto.NotaRequestDTO;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.Nota;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    NotaRepository notaRepository;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @PostMapping("/add/{idMatricula}/{idDisciplina}")
    public ResponseEntity<Nota> insertNota(@RequestBody NotaRequestDTO dto){
        Matricula matricula = this.matriculaRepository.findById(dto.getIdMatricula()).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O id da matrícula não foi encontrado!"));

        Disciplina disciplina = this.disciplinaRepository.findById(dto.getIdDisciplina()).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O id da disciplina não foi encontrado!") );

        Nota nota = new Nota();
        if(dto.getNota() == null || dto.getNota().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira uma nota válida!");
        }
        nota.setMatricula(matricula);
        nota.setDisciplina(disciplina);
        nota.setNota(dto.getNota());

        LocalDate currentDate = LocalDate.now();
        nota.setDataLancamento(currentDate);

        this.notaRepository.save(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nota);
    }

    @GetMapping("/{idMatricula}")
    public ResponseEntity<List<Map<String, Object>>> getNotas(@PathVariable Integer idMatricula) {
        Matricula matricula = this.matriculaRepository.findById(idMatricula).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O id da matricula não foi encontrado!"));

        List<Map<String, Object>> disciplinasNotas = new ArrayList<>();
        for (Disciplina disciplina : matricula.getTurma().getCurso().getDisciplina()) {
            // verifica se a disciplina possui nota associada
            Optional<Nota> optNota = disciplina.getNotas().stream().
                    filter(n -> n.getDisciplina().equals(disciplina)).findFirst();

            Map<String, Object> disciplinaNota = new HashMap<>();
            disciplinaNota.put("Disciplina", disciplina);
            disciplinaNota.put("Nota", optNota.map(Nota::getNota).orElse(null));

            disciplinasNotas.add(disciplinaNota);
        }

        return ResponseEntity.status(HttpStatus.OK).body(disciplinasNotas);
    }

}
