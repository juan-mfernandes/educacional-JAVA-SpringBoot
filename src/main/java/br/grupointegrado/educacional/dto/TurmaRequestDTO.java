package br.grupointegrado.educacional.dto;

import jakarta.validation.constraints.NotNull;

public class TurmaRequestDTO {

    @NotNull
    private Integer anoLetivo;

    @NotNull
    private Integer semestre;

    @NotNull
    private Integer cursoId;

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Integer getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Integer anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

}
