package br.grupointegrado.educacional.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class ProfessorRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Pattern(
            regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$",
            message = "O nome deve começar com uma letra maiúscula e conter apenas letras e espaços."
    )
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    @Size(min = 15, max = 15, message = "O telefone deve ter exatamente 15 caracteres (formato).")
    private String telefone;

    @NotBlank(message = "A especialidade é obrigatória.")
    @jakarta.validation.constraints.Pattern(
            regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$",
            message = "A especialidade deve começar com uma letra maiúscula e conter apenas letras e espaços."
    )
    private String especialidade;

    public void setNome(String nome) {
//        String regex = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(nome);
//        if ( !matcher.matches()) {
//            throw new IllegalArgumentException("Insira um formato válido");
//        }

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
//        String regex = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(especialidade);
//        if ( !matcher.matches()) {
//            throw new IllegalArgumentException("Insira um formato válido.");
//        }
        this.especialidade = especialidade;
    }
}
