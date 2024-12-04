package br.grupointegrado.educacional.controllers;

import br.grupointegrado.educacional.dto.RelatorioDisciplinaRequestDTO;
import br.grupointegrado.educacional.dto.RelatorioTurmaRequestDTO;
import br.grupointegrado.educacional.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/desempenho")
public class MatriculaRelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/matricula/{idMatricula}/disciplinas/{idDisciplina}")
    public ResponseEntity<List<RelatorioDisciplinaRequestDTO>> relatorioDisciplina(@PathVariable Integer idMatricula, @PathVariable Integer idDisciplina) {
        return ResponseEntity.ok(relatorioService.relatorioDisciplina(idMatricula, idDisciplina));
    }

    @GetMapping("/turma/{id}")
    public ResponseEntity<List<RelatorioTurmaRequestDTO>> relatorioTurma(@PathVariable Integer id) {
        return ResponseEntity.ok(relatorioService.relatorioByTurma(id));
    }

}
