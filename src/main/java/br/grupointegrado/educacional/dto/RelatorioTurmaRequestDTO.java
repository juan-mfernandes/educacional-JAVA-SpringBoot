package br.grupointegrado.educacional.dto;

import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Matricula;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class RelatorioTurmaRequestDTO {
    @JsonIgnore
    private Integer turmaId;

    private Aluno aluno;

    @JsonIgnoreProperties({"idMatricula", "dataLancamento", "idDisciplina"})
    private List<NotaRequestDTO> notas;

    @JsonIgnore
    private List<Matricula> matriculas;

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public List<NotaRequestDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaRequestDTO> notas) {
        this.notas = notas;
    }

    public Integer getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Integer turmaId) {
        this.turmaId = turmaId;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
