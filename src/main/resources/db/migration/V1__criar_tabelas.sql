create table associado (
    id serial constraint pk_associado primary key,
    nome character varying(128),
    cpf character varying(11)
);

create table pauta (
    id serial constraint pk_pauta primary key,
    descricao text,
    status character varying(32)
);

create table votacao (
    id serial constraint pk_votacao primary key,
    id_pauta integer constraint fk_votacao_pauta references pauta (id),
    duracao integer,
    data_hora_inicio timestamp,
    status character varying(32)
);

create table votacao_associado (
    id_votacao integer constraint fk_votacao_associado_votacao references votacao (id),
    id_associado integer constraint fk_votacao_associado_pauta references pauta (id),
    voto char(3)
);