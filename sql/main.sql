CREATE TABLE pessoa(
    id int not null PRIMARY KEY AUTO_INCREMENT,
    nome varchar(70),
    telefone varchar(14),
    endereco varchar(255),
    email varchar(50)
);

CREATE TABLE contatos (
    id int not null PRIMARY key AUTO_INCREMENT,
    parentesco varchar(30),
    id_pessoa int not null,
    foreign key (id_pessoa) references pessoa (id)
);
    
CREATE TABLE cliente(
    id int not null PRIMARY key AUTO_INCREMENT, 
    id_pessoa int not null,
    rg varchar(10),
    cpf varchar(11),
    foreign key (id_pessoa) references pessoa(id)
);
    
CREATE TABLE especialidade (
    id int not null PRIMARY key AUTO_INCREMENT,
    descricao varchar(40) not null
);

CREATE TABLE medico (
    id int not null PRIMARY key AUTO_INCREMENT,
    id_pessoa int not null,
    cpf varchar(11),
    crm varchar(20),
    id_especialidade int not null,
    horario_inicial varchar(8),
    horario_final varchar(8),
    rg varchar(10),
    foreign key (id_pessoa) references pessoa(id),
    foreign key (id_especialidade) references especialidade (id)
);

CREATE TABLE agendamento(
    id int not null PRIMARY key AUTO_INCREMENT,
    id_cliente int not null,
	id_medico int not null,
	data_consulta datetime not null,
    foreign key (id_cliente) references cliente (id),
    foreign key (id_medico) references medico (id)
);