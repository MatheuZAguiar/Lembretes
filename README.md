# Projeto de Lembretes

Este projeto é um exemplo de aplicativo que permite cadastrar pessoas e associar lembretes a elas.

## Endpoints

Aqui estão os endpoints disponíveis:

### Buscar todas as pessoas

Método: GET

http://localhost:8080/api/pessoas

### Buscar pessoa pelo nome

Método: GET

http://localhost:8080/api/pessoas/{nome}

### Cadastrar pessoa

Método: POST

http://localhost:8080/api/pessoas

Corpo da solicitação:
{
"nome": "matheus"
}

### Atribuir lembrete a uma pessoa

Método: POST

http://localhost:8080/api/pessoas/{pessoaId}/lembretes

Corpo da solicitação:
{
"texto": "Comprar Pizza"
}


## Executando o Projeto

1. Clone o repositório para a sua máquina local.
2. Abra o projeto em sua IDE favorita.
3. Certifique-se de ter as dependências configuradas (utilizando o Maven ou Gradle).
4. Execute a aplicação.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Spring Web
- Banco de Dados (H2, MySQL, etc. - dependendo das configurações)
- Postman (ou ferramenta similar) para testar os endpoints.

## Contribuindo

Este projeto é apenas um exemplo e está aberto a contribuições. Se você tiver sugestões, melhorias ou correções, sinta-se à vontade para abrir uma "issue" ou um "pull request".
