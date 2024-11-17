package br.grupointegrado.educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("curso") // ignora o atributo "curso" para evitar looping na resposta http;
    private List<Turma> turma;

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
