create table associado (
    id serial constraint pk_associado primary key,
    nome character varying(128),
    cpf character varying(11)
);

create table pauta (
    id serial constraint pk_pauta primary key,
    descricao text
);

create table votacao (
    id serial constraint pk_votacao primary key,
    id_pauta integer constraint fk_votacao_pauta references pauta (id),
    duracao integer,
    data_hora_inicio timestamp
);

create table voto_associado (
    id_votacao integer constraint fk_voto_associado_votacao references votacao (id),
    id_associado integer constraint fk_voto_associado_associado references associado (id),
    voto char(3)
);