# Sistema de Transações Bancárias com Concorrência de Saldo

## Descrição

Este projeto consiste em uma API de um sistema bancário que suporta múltiplas transações concorrentes, garantindo a integridade do saldo das contas. O sistema permite o cadastro de contas bancárias, além de funcionalidades para depósitos, saques e transferências entre contas.

## Funcionalidades

- **Cadastro de Conta Bancária:** Permite a criação de novas contas bancárias com saldo inicial. Cada conta possui um número único e um saldo associado.
  
- **Transações:**
  - **Depósito:** Adiciona um valor ao saldo da conta especificada.
  - **Saque:** Retira um valor do saldo da conta, garantindo que haja saldo suficiente.
  - **Transferência:** Permite transferir um valor de uma conta para outra.

- **Concorrência de Transações:** O sistema é capaz de lidar com múltiplas transações simultâneas, garantindo que a concorrência não corrompa o saldo das contas.

## Estrutura do Código

O projeto foi implementado utilizando Kotlin e Ktor, com as seguintes estruturas principais:

- **Modelos:** Classes para representar as contas e as requisições de transação.
- **Repositório:** Um repositório que gerencia as contas e as operações de transação, utilizando bloqueios para garantir a segurança em cenários de concorrência.
- **API:** A API foi implementada utilizando rotas que permitem a criação de contas, depósitos, saques e transferências, respondendo em formato JSON.

## Endpoints da API

Abaixo estão os endpoints disponíveis na API:

1. **Criar Conta**
   - **Método:** POST
   - **Endpoint:** `/criarconta`
   - **Corpo da Requisição:** 
     ```json
     {
       "tipo": "criar_conta",
       "numero": 123,
       "saldoInicial": 500
     }
     ```
   - **Resposta:** 
     ```json
     {
       "tipo": "criar_conta",
       "message": "CONTA CRIADA COM SUCESSO."
     }
     ```

2. **Consultar Conta**
   - **Método:** GET
   - **Endpoint:** `/consultarconta/{numero}`
   - **Resposta:** 
     ```json
     {
       "numero": 123,
       "saldo": 500
     }
     ```

3. **Depositar**
   - **Método:** POST
   - **Endpoint:** `/depositar`
   - **Corpo da Requisição:**
     ```json
     {
       "tipo": "depositar",
       "numero": 123,
       "valor": 100
     }
     ```
   - **Resposta:**
     ```json
     {
       "tipo": "depositar",
       "message": "DEPÓSITO REALIZADO."
     }
     ```

4. **Sacar**
   - **Método:** POST
   - **Endpoint:** `/sacar`
   - **Corpo da Requisição:**
     ```json
     {
       "tipo": "sacar",
       "numero": 123,
       "valor": 50
     }
     ```
   - **Resposta:**
     ```json
     {
       "tipo": "sacar",
       "message": "SAQUE REALIZADO."
     }
     ```

5. **Transferir**
   - **Método:** POST
   - **Endpoint:** `/transferir`
   - **Corpo da Requisição:**
     ```json
     {
       "tipo": "transferir",
       "contaOrigem": 123,
       "contaDestino": 456,
       "valor": 30
     }
     ```
   - **Resposta:**
     ```json
     {
       "tipo": "transferir",
       "message": "TRANSFERÊNCIA REALIZADA."
     }
     ```

