# Sistema de Tranferências Bancárias

## Casos de uso:

* Manipular dados de usuários
* Manipular dados de transferências

## Recursos
  
* Usuários
* Transferências 

## Endpoints Usuário:

* Cadastrar usuário (`POST`)
* Ler informações do usuário (`GET`)
* Alterar informações do usuário (`PUT`)
* Excluir informações do usuário (`DELETE`)

<br>

### Requisição: Cadastrar usuário

**Descrição:** Novo usuário realiza cadastro no sistema de transferências bancárias

**URI:** /usuarios

**Método HTTP:** POST

**Corpo da requisição esperado:** 

````json
{
    "nome": "Rafael",
    "cpf": "012.345.789-98",
    "dataNascimento": "15/10/1995",
    "email": "rafael@email.com",
    "tipoConta": "Pessoa Fisica",
    "senha": "123456"
}
````

**Resposta da requisição esperada:**

* Código de status: 201 (Created) - Usuário criado com sucesso

**Erros da requisição esperados:**

* Código de status: 400 (Bad Request) - A sintaxe da requisição ou as informações são inválidas
* Código de status: 409 (Conflict) - Já existe um usuário cadastrado com o mesmo CPF.

<br>

### Requisição: Recuperar informações do usuário

**Descrição:** Recuperar informações do usuário utilizando o ID como parametro.

**URI:** /usuarios/{userId}

**Método HTTP:** GET

**Resposta da requisição esperada:**

* Código de status: 200 (OK) -

````json
{
    "nome": "Rafael",
    "cpf": "012.345.789-98",
    "dataNascimento": "15/10/1995",
    "email": "rafael@email.com",
    "tipoConta": "Pessoa Fisica",
    "senha": "123456"
}
````

**Erros da requisição esperados:**

* Código de status: 404 (Not Found) - Usuário não encontrada.

<br>

### Requisição: Alterar informações do usuário

**Descrição:** O usuário deseja atualizar as informações do seu cadastro.

**URI:** /usuarios/{userId}

**Método HTTP:** PUT

**Corpo da requisição esperado:** 

````json
{
    "id": "{userId}",
    "nome": "Rafael Alterado",
    "dataNascimento": "15/10/1995",
    "email": "rafael@email.com",
    "tipoConta": "Pessoa Fisica",
    "senha": "123456"
}
````

**Resposta da requisição esperada:**

* Código de status: 200 (OK) - Usuário atualizado com sucesso

**Erros da requisição esperados:**

* Código de status: 400 (Bad Request) - A sintaxe da requisição ou as informações são inválidas
* Código de status: 404 (Not Found) - Usuário não encontrado.

<br>

### Requisição: Excluir informações usuário

**Descrição:** Exclui um usuário do sistema de transferência bancária

**URI:** /usuarios/{userId}

**Método HTTP:** DELETE

**Resposta da requisição esperada:**

* Código de status: 200 (OK) - Exclusão foi realizada (Mensagem de resposta: Usuário excluído com sucesso)

**Erros da requisição esperados:**

* Código de status: 401 (Unauthorized) - O token de acesso não é válido ou não foi informado
* Código de status: 404 (Not Found) - Usuário com o ID informado não foi encontrado

#### Observações:

A exclusão de um usuário deve ser uma operação irreversível.
Antes de excluir um usuário, o sistema deve verificar se ele possui alguma pendência no sistema, como pagamentos em aberto ou reembolsos a serem realizados.
Se o usuário tiver pendências, o sistema deve impedir a exclusão e informar ao usuário as pendências que precisam ser resolvidas antes da exclusão.
O sistema deve registrar a data e hora da exclusão do usuário.

<br>

## Endpoints Transferências:

* Realizar tranferências (`POST`)
* Ler informações do tranferências (`GET`)
* Alterar informações do tranferências (`PUT`)
* Excluir informações do tranferências (`DELETE`)

<br>
  
### Requisição: Realizar tranferências

**Descrição:** Nova tranferência é realizada no sistema de transferências bancárias

**URI:** /tranferencias

**Método HTTP:** POST

**Corpo da requisição esperado:**

````json
{
    "valor": 123.45,
    "dataTransferencia": "08-05-2024",
    "contaOrigem": "012345-1",
    "nomeBeneficiario": "João de Almeida",
    "cpfBeneficiario": "123.587.968-87",
    "Banco": "Caixa",
    "Agencia": "123",
    "contaDestino": "541230-2"
}
````
  
**Resposta da requisição esperada:**

* Código de status: 201 (Created) - Tranferência realizada com sucesso


**Erros da requisição esperados:**

* Código de status: 400 (Bad Request) - A sintaxe da requisição ou as informações são inválidas
* Código de status: 409 (Conflict) - Já existe um pagamento com o mesmo valor e conta de destino para esta data.

<br>

### Requisição: Recuperar informações da transferência

**Descrição:** Recuperar informações da transferência utilizando o ID como parametro.

**URI:** /transferencias/{transferenciaId}

**Método HTTP:** GET

**Resposta da requisição esperada:**

* Código de status: 200 (OK) -

````json
{
    "valor": 123.45,
    "dataTransferencia": "08-05-2024",
    "contaOrigem": "012345-1",
    "nomeBeneficiario": "João de Almeida",
    "cpfBeneficiario": "123.587.968-87",
    "Banco": "Caixa",
    "Agencia": "123",
    "contaDestino": "541230-2"
}
````

**Erros da requisição esperados:**

* Código de status: 404 (Not Found) - Transferência não encontrada.
  
<br>

### Requisição: Alterar informações da transferência

**Descrição:** O usuário deseja alterar as informações da transferência antes que a transferência tenha sido efetuada.

**URI:** /transferencias/{transferenciaId}

**Método HTTP:** PUT

**Corpo da requisição esperado:** 

````json
{
    "valor": 354.45,
    "dataTransferencia": "10-05-2024",
    "contaOrigem": "012345-1",
    "nomeBeneficiario": "João de Almeida",
    "cpfBeneficiario": "123.587.968-87",
    "Banco": "Caixa",
    "Agencia": "123",
    "contaDestino": "541230-2"
}
````

**Resposta da requisição esperada:**

* Código de status: 200 (OK) - Transferência atualizada com sucesso

**Erros da requisição esperados:**

* Código de status: 400 (Bad Request) - A sintaxe da requisição ou as informações são inválidas
* Código de status: 404 (Not Found) - Transferência não encontrada.

<br>

### Requisição: Excluir informações da transferência

**Descrição:** Exclui uma transferência do sistema de transferência bancário, antes que a transferência tenha sido efetuada.

**URI:** /transferencias/{transferenciaId}

**Método HTTP:** DELETE

**Resposta da requisição esperada:**

* Código de status: 200 (OK) - Transferência excluída com sucesso. (Mensagem de resposta: Transferência excluída com sucesso)

**Erros da requisição esperados:**

* Código de status: 404 (Not Found) - Transferência com o ID informado não foi encontrado
* Código de status: 403 (Forbidden) - O usuário não tem permissão para excluir transferências.


#### Observações:

A exclusão de uma transferência deve ser uma operação irreversível.
Antes de excluir uma transferência, o sistema deve verificar se a mesma está em um estado que permite a exclusão.
Se a transferência estiver em um estado que não permite a exclusão, o sistema deve impedir a exclusão e informar ao usuário o motivo.
O sistema deve registrar a data e hora da exclusão da transferência
