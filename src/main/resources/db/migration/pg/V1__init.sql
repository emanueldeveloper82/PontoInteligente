-- CRIAÇÃO DAS TABELAS INICIAIS

CREATE TABLE empresa (
	id_empresa serial8 NOT NULL,
	num_cnpj varchar NOT NULL,
	data_criacao timestamp NOT NULL,
	data_atualizacao timestamp NOT NULL,
	razao_social varchar NOT NULL,
	CONSTRAINT empresa_pk PRIMARY KEY (id_empresa),
	CONSTRAINT empresa_un UNIQUE (num_cnpj)
);

CREATE TABLE funcionario (
	id_funcionario serial8 NOT NULL,
	num_cpf varchar NOT NULL,
	email varchar NOT NULL,
    nome varchar NOT NULL,
	data_criacao timestamp NOT NULL,
	data_atualizacao timestamp NOT NULL,
	perfil varchar NOT NULL,
    senha varchar NOT NULL,
    qtd_horas_almoco numeric DEFAULT NULL,
    qtd_horas_trabalho_dia numeric DEFAULT NULL,
    valor_hora numeric(19,2) DEFAULT NULL,
    id_empresa_fk int4 NOT NULL,
	CONSTRAINT funcionario_pk PRIMARY KEY (id_funcionario),
	CONSTRAINT funcionario_un UNIQUE (num_cpf),
	CONSTRAINT funcionario_empresa_fk FOREIGN KEY (id_empresa_fk) REFERENCES ponto_eletronico.empresa(id_empresa)
);

CREATE TABLE lancamento (
	id_lancamento serial8 NOT NULL,
	data_lancamento timestamp NOT NULL,
	data_criacao timestamp NOT NULL,
	data_atualizacao timestamp NOT NULL,	
	descricao varchar DEFAULT NULL,
    localizacao varchar DEFAULT NULL,
    tipo_lancamento varchar(255) NOT NULL,
    id_funcionario_fk int4 NOT NULL,
	CONSTRAINT lancamento_pk PRIMARY KEY (id_lancamento),
	CONSTRAINT lancamento_funcionario_fk FOREIGN KEY (id_funcionario_fk) REFERENCES ponto_eletronico.funcionario(id_funcionario)
);
