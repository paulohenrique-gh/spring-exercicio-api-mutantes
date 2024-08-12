# API Mutantes (D.E.V.S)

- Para tentar diminuir o desaparecimento constante de mutantes, foi criada uma
  engenharia de proteção: D.E.V.S. (Dispositivo Eletrônico de Vigilância e
  Segurança)

- O D.E.V.S. é representado por um sistema de entrada que permite o usuário
  entrar com uma senha: “A senha é o resultado do enigma Jaobsob”
- O mutante vai informar: A senha, seu nome, seu poder, sua idade e o número
  de quantos inimigos ele derrotou na semana.
- Sabe-se que em média um mutante derrota:
- 26.8% do total informado são alienígenas por semana
- 43,2% do total informado são demônios
- Informe para o usuário quantos Alienígenas, e demônios ele matou na semana
  baseado na porcentagem acima.
- Se o usuário derrotou mais de 20 alienígenas, deve retornar que ele foi
  convocado para a organização E.S.P.A.D.A. (Equipe de Supervisão, Pesquisa,
  Avaliação e Defesa Armada)
- A API deve ter uma requisição para retornar se o mutante deve ou não se alistar
  a ESPADA
- A API deve ter um endpoint que informe ao banco quando um mutante entrar
  e sair das propriedades da escola (use o id)
- A API deve ter um endpoint que retorne quantos mutantes estão na escola.
- A API deve ter um endpoint que informe quais mutantes estão na escola, dica:
  pode utilizar um atributo booleano.

## Endpoints
Para testar os endpoints, acessar http://localhost:8080/swagger-ui/index.html

```bash
POST /mutants
```
Descrição: cadastra mutante

---
```bash
GET /mutants
```
Descrição: retorna lista de mutantes

---
```bash
GET /mutants/checked-in
```
Descrição: retorna lista de mutantes atualmente na escola

---
```bash
GET /mutants/checked-in-count
```
Descrição: retorna número de mutantes atualmente na escola

---
```bash
GET /mutants/{id}
```
Descrição: retorna mutante de acordo com id informado

---
```bash
PATCH /mutants/{id}/check-in
```
Descrição: registra entrada do mutante na escola

---
```bash
PATCH /mutants/{id}/check-out
```
Descrição: registra saída do mutante da escola

---
```bash
GET /mutants/{id}/enemies-defeated-summary
```
Descrição: resumo de inimigos derrotados de acordo com o id do mutante informado. 
Se o mutante derrotou mais de 20 alienígenas, o corpo da resposta tem uma mensagem informando que ele foi convocado
para a organização E.S.P.A.D.A

---
```bash
GET /mutants/{id}/espada-eligibility
```
Descrição: informa se mutante é elegível para a E.S.P.A.D.A
