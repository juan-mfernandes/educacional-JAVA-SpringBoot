package br.grupointegrado.educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(precision = 100)
    private String nome;

    @Column(precision = 20)
    private String codigo;

    @Column
    private Integer carga_horaria;

    @OneToMany(mappedBy = "curso") // especifica como o atributo "curso" foi mapeado na tabela que possui o relacionamento forte, neste caso, a tabela/classe "Turma";
    @JsonManagedReference // ignora o atributo "curso" para evitar looping na resposta http;
    @JsonIgnore
    private List<Turma> turmas;

    @OneToMany(mappedBy = "curso")
    @JsonManagedReference
    @JsonIgnore
    private List<Disciplina> disciplinas;

    public Integer getId() {
        return id;
    }

    public List<Turma> getTurma() {
        return turmas;
    }

    public void setTurma(List<Turma> turma) {
        this.turmas = turma;
    }

    public List<Disciplina> getDisciplina() {
        return disciplinas;
    }

    public void setDisciplina(List<Disciplina> disciplina) {
        this.disciplinas = disciplina;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(Integer carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
}
