package br.grupointegrado.educacional.controllers;

import br.grupointegrado.educacional.dto.CursoRequestDTO;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        List<Curso> cursos = cursoRepository.findAll();

        return ResponseEntity.ok().body(cursos);
    }

    @PostMapping
    public ResponseEntity<Curso> insertCurso(@RequestBody @Valid CursoRequestDTO dto){
        if(cursoRepository.existsByNome((dto.getNome()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um curso com o mesmo nome já existe!");
        }
        Curso curso = new Curso();
        curso.setCarga_horaria(dto.getCargaHoraria());
        curso.setCodigo(dto.getCodigo());
        curso.setNome(dto.getNome());
        Curso savedCurso = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@RequestBody @Valid CursoRequestDTO dto, @PathVariable Integer id){
        Curso curso = this.cursoRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O id buscado não existe."));

        if(cursoRepository.existsByNome((dto.getNome()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um curso com o mesmo nome ja existe!");
        }

        curso.setCarga_horaria(dto.getCargaHoraria());
        Curso updatedCurso = cursoRepository.save(curso);

        return ResponseEntity.ok().body(updatedCurso);
    }

}
