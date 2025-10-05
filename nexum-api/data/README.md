# 🛠 API RESTful — Plataforma de Cursos

Documento completo com **endpoints RESTful** da plataforma de cursos online, aplicando as melhorias de nomenclatura e padronização recomendadas.

---

## Índice

* [Usuário](#usuário)
* [Permissão](#permissão)
* [Usuário ↔ Permissão](#usuário--permissão)
* [Curso](#curso)
* [Aula](#aula)
* [Matrícula](#matrícula)
* [Progresso](#progresso)

---

## Usuário

| Método | Endpoint                 | Descrição                                                 |
| ------ | ------------------------ | --------------------------------------------------------- |
| GET    | /usuarios                | Listar todos os usuários (com filtros, paginação opcional) |
| GET    | /usuarios/{id}           | Obter detalhes de um usuário                              |
| POST   | /usuarios                | Criar um novo usuário                                     |
| PUT    | /usuarios/{id}           | Editar um usuário existente                               |
| PATCH  | /usuarios/{id}/desativar | Desativar um usuário (soft delete)                        |
| DELETE | /usuarios/{id}           | Excluir um usuário                             |

**Exemplo JSON de POST /usuarios**

```json
{
  "nome": "João Silva",
  "email": "joao@example.com",
  "senha": "senha_segura"
}
```

---

## Permissão

| Método | Endpoint         | Descrição                       |
| ------ | ---------------- | ------------------------------- |
| GET    | /permissoes      | Listar todas as permissões      |
| GET    | /permissoes/{id} | Obter detalhes de uma permissão |
| POST   | /permissoes      | Criar nova permissão            |
| PUT    | /permissoes/{id} | Editar permissão existente      |
| DELETE | /permissoes/{id} | Excluir permissão               |

---

## Usuário ↔ Permissão

| Método | Endpoint                                 | Descrição                        |
| ------ | ---------------------------------------- | -------------------------------- |
| GET    | /usuarios/{id}/permissoes                | Listar permissões de um usuário  |
| POST   | /usuarios/{id}/permissoes                | Adicionar permissão a um usuário |
| DELETE | /usuarios/{id}/permissoes/{permissao_id} | Remover permissão de um usuário  |

**Exemplo JSON de POST /usuarios/{id}/permissoes**

```json
{
  "permissao_id": 2
}
```

---

## Curso

| Método | Endpoint               | Descrição                                            |
| ------ | ---------------------- | ---------------------------------------------------- |
| GET    | /cursos                | Listar cursos (com filtros por categoria, ativo etc.) |
| GET    | /cursos/{id}           | Obter detalhes de um curso                           |
| POST   | /cursos                | Criar novo curso                                     |
| PUT    | /cursos/{id}           | Editar um curso                                      |
| PATCH  | /cursos/{id}/desativar | Desativar curso (soft delete)                        |
| DELETE | /cursos/{id}           | Excluir um curso                          |

---

## Aula

| Método | Endpoint              | Descrição                                        |
| ------ | --------------------- |--------------------------------------------------|
| GET    | /aulas                | Listar aulas (com filtros por curso e paginação) |
| GET    | /aulas/{id}           | Obter detalhes de uma aula                       |
| POST   | /aulas                | Criar nova aula                                  |
| PUT    | /aulas/{id}           | Editar aula existente                            |
| PATCH  | /aulas/{id}/desativar | Desativar aula (soft delete)                     |
| DELETE | /aulas/{id}           | Excluir uma aula                                 |

---

## Matrícula

| Método | Endpoint                                     | Descrição                                |
| ------ | -------------------------------------------- | ---------------------------------------- |
| GET    | /matriculas                                  | Listar todas as matrículas (com filtros) |
| GET    | /matriculas/{usuario_id}/{curso_id}          | Obter detalhes de uma matrícula          |
| POST   | /matriculas                                  | Criar matrícula                          |
| PUT    | /matriculas/{usuario_id}/{curso_id}          | Editar matrícula (status, conclusão)     |
| PATCH  | /matriculas/{usuario_id}/{curso_id}/cancelar | Cancelar matrícula                       |
| DELETE | /matriculas/{usuario_id}/{curso_id}          | Excluir matrícula                        |

**Exemplo JSON de POST /matriculas**

```json
{
  "usuario_id": 5,
  "curso_id": 10
}
```

---

## Progresso

| Método | Endpoint        | Descrição                                           |
| ------ | --------------- | --------------------------------------------------- |
| GET    | /progresso      | Listar progresso (com filtros por usuário ou curso) |
| GET    | /progresso/{id} | Obter detalhes de um progresso específico           |
| POST   | /progresso      | Criar registro de progresso                         |
| PUT    | /progresso/{id} | Atualizar progresso (completado, data)              |
| DELETE | /progresso/{id} | Excluir registro de progresso                       |

**Exemplo JSON de POST /progresso**

```json
{
  "usuario_id": 5,
  "aula_id": 12,
  "completado": false
}
```

---

✨ **Fim do documento — API padronizada e RESTful.**
