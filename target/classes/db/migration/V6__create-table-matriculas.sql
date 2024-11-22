create table matriculas (
    id int not null primary key auto_increment,
    aluno_id int not null,
    turma_id int not null,
    CONSTRAINT fk_matriculas_aluno_id FOREIGN KEY (aluno_id) REFERENCES alunos(id),
    CONSTRAINT fk_matriculas_turma_id FOREIGN KEY (turma_id) REFERENCES turmas(id)
);