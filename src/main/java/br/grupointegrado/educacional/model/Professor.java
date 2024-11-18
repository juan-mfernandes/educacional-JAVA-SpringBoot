package br.grupointegrado.educacional.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professores") // referencia a tabela de professores do banco de dados
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(precision = 100) // usado para lidar com os tipos especificados no banco de dados
    private String nome;

    @Column(precision = 100)
    private String email;

    @Column(precision = 15)
    private String telefone;

    @Column(precision = 100)
    private String especialidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        this.especialidade = especialidade;
    }
}
