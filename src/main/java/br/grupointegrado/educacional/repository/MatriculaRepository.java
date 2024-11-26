package br.grupointegrado.educacional.repository;

import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    boolean existsByAlunoAndTurma(Aluno aluno, Turma turma);
}
