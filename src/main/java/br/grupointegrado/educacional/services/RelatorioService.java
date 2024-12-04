package br.grupointegrado.educacional.services;

import br.grupointegrado.educacional.dto.NotaRequestDTO;
import br.grupointegrado.educacional.dto.RelatorioRequestDTO;
import br.grupointegrado.educacional.model.*;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    MatriculaRepository matriculaRepository;

    public List<RelatorioRequestDTO> relatorioDisciplina(Integer matriculaId, Integer disciplinaId) {
        Matricula matricula =  matriculaRepository.findById(matriculaId).
                orElseThrow(() -> new RuntimeException("Matricula não encontrada!"));

        List<Disciplina> disciplinas = new ArrayList<>(matricula.getTurma().getCurso().getDisciplina());
        Disciplina disciplinaById = disciplinas.stream().
                filter( (d) -> d.getId().equals(disciplinaId) ).findFirst().
                orElseThrow( () -> new IllegalArgumentException("Disciplina não encontrada!"));

        List<Nota> notas = disciplinaById.getNotas();

        return notas.stream().
                map( nota -> {
                    RelatorioRequestDTO dto = new RelatorioRequestDTO();
                    NotaRequestDTO notaRequest = new NotaRequestDTO();
                    ArrayList<NotaRequestDTO> notasRequest = new ArrayList<>();
                    dto.setMatriculaId(matriculaId);
                    notaRequest.setIdDisciplina(nota.getDisciplina().getId());
                    notaRequest.setNomeDisciplina(nota.getDisciplina().getNome());
                    notaRequest.setNota(nota.getNota());
                    notasRequest.add(notaRequest);
                    dto.setNotas(notasRequest);
                    dto.setAluno(matricula.getAluno());
                    return dto;
                }).toList();
    }


}
