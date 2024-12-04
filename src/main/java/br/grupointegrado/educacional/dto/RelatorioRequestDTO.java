package br.grupointegrado.educacional.dto;

import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Nota;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

public class RelatorioRequestDTO {

    @JsonIgnore
    private Integer matriculaId;

    private Aluno aluno;

    @JsonIgnoreProperties({"idMatricula", "dataLancamento"})
    private List<NotaRequestDTO> notas;

    public List<NotaRequestDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaRequestDTO> notas) {
        this.notas = notas;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

}
