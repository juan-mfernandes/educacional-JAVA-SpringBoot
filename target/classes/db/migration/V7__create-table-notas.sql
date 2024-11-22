create table notas (
    id int not null primary key auto_increment,
    matricula_id int not null,
    disciplina_id int not null,
    nota decimal(5,2) not null,
    data_lancamento date not null,
    CONSTRAINT fk_notas_matriculas_id FOREIGN KEY (matricula_id) REFERENCES matriculas(id),
    CONSTRAINT fk_notas_disciplinas_id FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id)
);