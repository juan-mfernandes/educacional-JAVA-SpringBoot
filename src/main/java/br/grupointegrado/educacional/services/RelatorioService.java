package br.grupointegrado.educacional.services;

import br.grupointegrado.educacional.dto.NotaRequestDTO;
import br.grupointegrado.educacional.dto.RelatorioDisciplinaRequestDTO;
import br.grupointegrado.educacional.dto.RelatorioTurmaRequestDTO;
import br.grupointegrado.educacional.model.*;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    TurmaRepository turmaRepository;

    public List<RelatorioDisciplinaRequestDTO> relatorioDisciplina(Integer matriculaId, Integer disciplinaId) {
        Matricula matricula =  matriculaRepository.findById(matriculaId).
                orElseThrow(() -> new RuntimeException("Matricula não encontrada!"));

        List<Disciplina> disciplinas = new ArrayList<>(matricula.getTurma().getCurso().getDisciplina());
        Disciplina disciplinaById = disciplinas.stream().
                filter( (d) -> d.getId().equals(disciplinaId) ).findFirst().
                orElseThrow( () -> new IllegalArgumentException("Disciplina não encontrada!"));

        List<Nota> notas = disciplinaById.getNotas();

        return notas.stream().
                map( nota -> {
                    RelatorioDisciplinaRequestDTO dto = new RelatorioDisciplinaRequestDTO();
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

    public List<RelatorioTurmaRequestDTO> relatorioByTurma(Integer turmaId) {
        // Filtra as matrículas da turma no repositório
        List<Matricula> matriculas = matriculaRepository.findByTurma_Id(turmaId);

        if (matriculas.isEmpty()) {
            throw new RuntimeException("Nenhuma matrícula encontrada para a turma especificada.");
        }

        // Mapeia as matrículas para o DTO
        return matriculas.stream().map(matricula -> {
            RelatorioTurmaRequestDTO dto = new RelatorioTurmaRequestDTO();

            // Configurações básicas do DTO
            dto.setTurmaId(turmaId);
            dto.setAluno(matricula.getAluno());
            dto.setMatriculas(List.of(matricula));

            // Mapeia as disciplinas e notas do aluno
            List<Disciplina> disciplinas = new ArrayList<>(matricula.getTurma().getCurso().getDisciplina());

            // Cria uma lista de NotaRequestDTO
            List<NotaRequestDTO> notaRequestDTOs = disciplinas.stream()
                    .flatMap(d -> d.getNotas().stream()
                            .filter(n -> n.getMatricula().getId().equals(matricula.getId()))
                            .map(nota -> {
                                NotaRequestDTO notaDTO = new NotaRequestDTO();
                                notaDTO.setNomeDisciplina(d.getNome());
                                notaDTO.setNota(nota.getNota());
                                return notaDTO;
                            })
                    )
                    .toList();

            dto.setNotas(notaRequestDTOs);

            return dto;
        }).toList();
    }
}
