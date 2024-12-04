package br.grupointegrado.educacional.dto;

import br.grupointegrado.educacional.model.Aluno;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class RelatorioDisciplinaRequestDTO {

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
