create table turmas (
    id int not null primary key auto_increment,
    ano int not null,
    semestre int not null,
    curso_id int not null,
    CONSTRAINT fk_turmas_cursos_id FOREIGN KEY (curso_id) REFERENCES cursos(id)
);