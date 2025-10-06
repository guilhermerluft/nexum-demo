CREATE EXTENSION IF NOT EXISTS citext;

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email CITEXT UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    excluido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NULL,
    atualizado_por INTEGER NULL REFERENCES usuario(id),
    desativado_em TIMESTAMP NULL
);

CREATE TABLE permissao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL,
    descricao VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE,
    excluido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    atualizado_em TIMESTAMP NULL,
    atualizado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL
);

CREATE TABLE usuario_permissao (
    id              SERIAL PRIMARY KEY,
    usuario_id      INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    permissao_id    INTEGER NOT NULL REFERENCES permissao(id) ON DELETE CASCADE,
    ativo BOOLEAN DEFAULT TRUE,
    excluido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    atualizado_em TIMESTAMP NULL,
    atualizado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    UNIQUE (usuario_id, permissao_id)
);


CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao VARCHAR(500),
    categoria VARCHAR(50),
    por_assinatura BOOLEAN DEFAULT FALSE,
    instrutor_id INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    ativo BOOLEAN DEFAULT TRUE,
    excluido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    atualizado_em TIMESTAMP NULL,
    atualizado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL
);

CREATE TABLE aula (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    video_url VARCHAR(500),
    descricao TEXT,
    curso_id INTEGER REFERENCES curso(id) ON DELETE CASCADE,
    ordem_index INTEGER,
    UNIQUE (curso_id, ordem_index),
    ativo BOOLEAN DEFAULT TRUE,
    excluido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    atualizado_em TIMESTAMP NULL,
    atualizado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL
);

CREATE TABLE matricula (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    curso_id INTEGER NOT NULL REFERENCES curso(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL DEFAULT 'ativo' CHECK (status IN ('ativo', 'concluido', 'cancelado')),
    data_matricula TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_conclusao TIMESTAMP NULL,
    ativo BOOLEAN DEFAULT TRUE,
    excluido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    atualizado_em TIMESTAMP NULL,
    atualizado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    UNIQUE (usuario_id, curso_id)
);

CREATE TABLE progresso (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER REFERENCES usuario(id) ON DELETE CASCADE,
    aula_id INTEGER REFERENCES aula(id) ON DELETE CASCADE,
    completado BOOLEAN DEFAULT FALSE,
    completado_em TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE,
    excluido BOOLEAN DEFAULT FALSE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    criado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    atualizado_em TIMESTAMP NULL,
    atualizado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    UNIQUE (usuario_id, aula_id)
);
