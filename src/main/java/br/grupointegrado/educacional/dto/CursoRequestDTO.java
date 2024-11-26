package br.grupointegrado.educacional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CursoRequestDTO {

    @NotBlank
    @Size(max = 100)
    @Pattern(
            regexp = "\"^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\\\h+[A-Z][A-Za-z]*)*$\"",
            message = "O nome deve começar com uma letra maiúscula e conter apenas letras e espaços."
    )
    private String nome;

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotBlank
    private Integer cargaHoraria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
