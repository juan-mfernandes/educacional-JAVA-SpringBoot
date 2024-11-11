package br.grupointegrado.educacional.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Column
    private int id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String matricula;
    @Column
    private Date data_nascimento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
