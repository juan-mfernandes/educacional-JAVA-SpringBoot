create table disciplinas (
    id int not null primary key auto_increment,
    nome varchar(100) not null,
    codigo varchar(20) not null,
    curso_id int not null,
    professor_id int not null,
    CONSTRAINT fk_disciplina_curso_id FOREIGN KEY (curso_id) REFERENCES cursos(id),
    CONSTRAINT fk_disciplina_professor_id FOREIGN KEY (professor_id) REFERENCES professores(id)
);