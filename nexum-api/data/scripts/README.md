# 🧩 Estrutura do Banco de Dados — Plataforma Nexum

Documento contendo o modelo de dados (DDL) para uma **plataforma de cursos online**: usuários, permissões, cursos, aulas, matrículas e progresso — com auditoria e soft-delete.

---

## 📋 Índice

* [Pré-requisitos](#pré-requisitos)
* [Script SQL (completo)](#script-sql-completo)

    * [Extensão](#extensão)
    * [Tabelas](#tabelas)
    * [Seed (exemplos)](#seed-exemplos)
* [Explicações por tabela](#explicações-por-tabela)
* [Diagrama conceitual simplificado](#diagrama-simplificado)

---

## Pré-requisitos

* **PostgreSQL** (versão compatível com `CITEXT` e `plpgsql`);
* Permissão para criar extensão `citext`;
* Acesso a um usuário com privilégios de `CREATE EXTENSION` e `CREATE TABLE`.

---

## Script SQL (completo)

> **Observação:** este script foi revisado para ser sintaticamente válido incluindo `ON DELETE` apropriados quando aplicável.

---

### Extensão

```sql
-- Extensão para CITEXT (emails case-insensitive)
CREATE EXTENSION IF NOT EXISTS citext;
```

---

### Tabelas

```sql
-- ==========================
-- TABELA: usuario
-- ==========================
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

-- ==========================
-- TABELA: permissao
-- ==========================
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

-- ==========================
-- TABELA: usuario_permissao (relação N:N com ID simples + Unicidade)
-- ==========================
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

-- ==========================
-- TABELA: curso
-- ==========================
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

-- ==========================
-- TABELA: aula
-- ==========================
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

-- ==========================
-- TABELA: matricula
-- ==========================
CREATE TABLE matricula (
  usuario_id INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
  curso_id INTEGER NOT NULL REFERENCES curso(id) ON DELETE CASCADE,
  PRIMARY KEY (usuario_id, curso_id),
  status VARCHAR(20) NOT NULL DEFAULT 'ativo' CHECK (status IN ('ativo', 'concluido', 'cancelado')),
  data_matricula TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_conclusao TIMESTAMP NULL,
  ativo BOOLEAN DEFAULT TRUE,
  excluido BOOLEAN DEFAULT FALSE,
  criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  criado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
  atualizado_em TIMESTAMP NULL,
  atualizado_por INTEGER REFERENCES usuario(id) ON DELETE SET NULL
);

-- ==========================
-- TABELA: progresso
-- ==========================
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
```

---

### Seed (exemplos)

```sql
-- Usuário administrador inicial
INSERT INTO usuario (id, nome, email, senha, ativo, excluido, criado_em)
VALUES (1, 'Administrador', 'admin@example.com', '<bcrypt-hash>', TRUE, FALSE, CURRENT_TIMESTAMP);

-- Permissões básicas
INSERT INTO permissao (nome, descricao, criado_por)
VALUES
 ('ADMIN', 'Acesso total ao sistema', 1),
 ('INSTRUTOR', 'Gerencia cursos e aulas', 1),
 ('ALUNO', 'Acesso a cursos matriculados', 1);

-- Associar permissões ao admin
INSERT INTO usuario_permissao (usuario_id, permissao_id, criado_por)
VALUES (1, 1, 1), (1, 2, 1);

-- Curso e aulas de exemplo
INSERT INTO curso (titulo, descricao, categoria, por_assinatura, instrutor_id, criado_por)
VALUES ('Introdução ao PostgreSQL', 'Aprenda SQL do zero', 'Banco de Dados', FALSE, 1, 1);

INSERT INTO aula (titulo, descricao, curso_id, ordem_index, criado_por)
VALUES
 ('Boas-vindas', 'Introdução ao curso', 1, 1, 1),
 ('Criando tabelas', 'Como criar tabelas no PostgreSQL', 1, 2, 1);

-- Matricula e progresso de exemplo
INSERT INTO matricula (usuario_id, curso_id, criado_por)
VALUES (1, 1, 1);

INSERT INTO progresso (usuario_id, aula_id, completado, completado_em)
VALUES (1, 1, TRUE, CURRENT_TIMESTAMP);
```

---

## Explicações por tabela

| Tabela                | Função                           | Chaves e Regras                                                                |
| --------------------- | -------------------------------- | ------------------------------------------------------------------------------ |
| **usuario**           | Armazena contas de usuários.     | `email` usa `CITEXT` (case-insensitive); tem auditoria completa e soft delete. |
| **permissao**         | Define papéis e permissões.      | FKs para `usuario(id)` com `ON DELETE SET NULL`.                               |
| **usuario_permissao** | Relaciona usuários a permissões. | `ON DELETE CASCADE` garante limpeza automática.                                |
| **curso**             | Armazena informações dos cursos. | Cada curso pertence a um instrutor (`usuario`).                                |
| **aula**              | Aulas pertencem a cursos.        | `UNIQUE(curso_id, ordem_index)` garante ordem única.                           |
| **matricula**         | Matrícula de usuários em cursos. | `status` validado por `CHECK`.                                                 |
| **progresso**         | Controle de progresso por aula.  | `UNIQUE(usuario_id, aula_id)` evita duplicidade.                               |

---


## Diagrama simplificado

```
[usuario] 1---N [curso] 1---N [aula]
    |  \             \           \
    |   \             \           N
    |    \             \         [progresso]
    |     \             \
    |      N             N
    |     [matricula]   [usuario_permissao] N---1 [permissao]
    |
    └── (campos de auditoria em todas as tabelas)
```

---
