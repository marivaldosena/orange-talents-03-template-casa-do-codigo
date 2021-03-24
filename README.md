<!-- Badges -->
[orange-talents-badge]: https://img.shields.io/static/v1?label=Zup&message=Orange%20Talents&color=orange
[java-badge]: https://img.shields.io/static/v1?label=Java&message=11&color=orange
[spring-boot-badge]:  https://img.shields.io/static/v1?label=Spring%20Boot&message=2.4&color=orange
[kotlin-badge]:  https://img.shields.io/static/v1?label=Kotlin&message=1.4&color=orange
[micronaut-badge]:  https://img.shields.io/static/v1?label=Micronaut&message=2.3&color=orange

<!-- Imagens -->

<!-- Links -->
[orange-talents-url]: https://www.zup.com.br/orange-talents
[java-url]: https://www.oracle.com/java/
[spring-url]: https://spring.io/
[kotlin-url]: https://kotlinlang.org/
[micronaut-url]: https://micronaut.io/

<!-- Conteúdo -->
# Zup Orange Talents

[![Zup Orange Talents][orange-talents-badge]][orange-talents-url]
[![Java][java-badge]][java-url]
[![Spring][spring-boot-badge]][spring-url]
[![Kotlin][kotlin-badge]][kotlin-url]
[![Micronaut][micronaut-badge]][micronaut-url]

O Zup Orange Talents é um programa da Zup para suprir a escassez de profissionais do ramo de Tecnologia da Informação.

# Tópicos

- [Tópicos](#tópicos)
- [Grade Curricular](#grade-curricular)
- [Desafio Casa do Código](#desafio-casa-do-código)
  - [Atividades](#atividades)
    - [Cadastro novo autor](#cadastro-novo-autor)
      - [Implementação do Cadastro novo autor](#implementação-do-cadastro-novo-autor)
    - [Cadastro de email único](#cadastro-de-email-único)
      - [Implementação do Cadastro de email único](#implementação-do-cadastro-de-email-único)

# Grade Curricular

Serão abordados os seguintes temas:

- Java
- Spring Boot
- Kotlin
- Micronaut
- Microsserviços
- Testes Unitários
- DevOps
- Entre outros

[Voltar ao menu](#tópicos)

# Desafio Casa do Código

Este primeiro desafio é uma forma de pôr em prática os conceitos 
ensinados ao longo das primeiras semanas.

## Atividades

Ao longo deste projeto, será necessário a realização de algumas atividades para atestar o conhecimento adquirido. As atividades devem ser resolvidas estritamente de acordo com o enunciado.

### Cadastro novo autor

É necessário cadastrar um novo autor no sistema. Todo autor tem um nome, email e uma descrição. Também queremos saber o instante exato que ele foi registrado.

#### Restrições

- <span style="color: green;">&check;</span> O instante não pode ser nulo
- <span style="color: green;">&check;</span> O email é obrigatório
- <span style="color: green;">&check;</span> O email tem que ter formato válido
- <span style="color: green;">&check;</span> O nome é obrigatório
- <span style="color: green;">&check;</span> A descrição é obrigatória e não pode passar de 400 caracteres

#### Resultado esperado

- <span style="color: green;">&check;</span> Um novo autor criado e status 200 retornado

[Voltar ao menu](#tópicos)

#### Implementação do Cadastro novo autor

Para concluir esta tarefa, eu dividiria em etapas. A primeira etapa, seria a criação de entidade. A entidade Autor teria os seguintes campos:

- nome do tipo String com as anotações @NotNull e, levando em conta que não foi definido um comprimento máximo de caracteres, definiria @Size(max = 120) para limitar a 120 caracteres, já que, acredito eu, a probabilidade de um nome exceder este limite é pouco.
- email do tipo String e com anotação @NotNull. Aqui há outra opção para o e-mail: poderia utilizar um Value Object chamado e-mail com validações personalizada e, dessa forma, evitaríamos sobrecarregar a entidade Autor com várias anotações. Além disso, é possível reaproveitar e-mail em outras classes em caso de necessidade. Nesta primeira etapa, tentarei manter a implementação simples e, por este motivo, não utilizarei o Value Object Email, mas um String.
- descricao do tipo String com a anotação @NotNull e com @Size(max = 400) para limitar a quantidade de caracteres a 400.
- instance do tipo LocalDateTime e com anotação @NotNull.

Criaria dois construtores, sendo um sem parâmetro de entrada (construtor com estilo padrão) para o Hibernate e o outro para consumo da aplicação. O segundo construtor teria a assinatura com três parâmetros: nome, email, descricao. O atributo instance não precisa ser passado pelo cliente, mas instanciado no construtor de forma interna.

Após a criação da entidade, criaria um Form Value Object ou um Data Transfer Object (DTO) para conter as validações na borda do sistema, isto é, na fronteira de entrada de dados da aplicação. Dessa forma, se os dados forem inválidos a aplicação não precisaria seguir o fluxo de dados e fazer chamadas desnecessárias ao banco de dados.

O Form Value Object (ou DTO) possuiria os seguintes campos:

- nome do tipo String com as anotações @NotNull para informar que este campo é obrigatório, @NotEmpty para dizer que o campo não pode ser vazio ou equivalente a isso, @Size(min = 2, max = 120) para definir um intervalo de comprimento de caracteres.
- email do tipo String com as anotações @NotNull e @Email para informar que o campo somente aceita o formato de e-mail.
- descricao do tipo String. As anotações seriam: @NotNull para não-nulo e @Size(min = 3, max = 400) para informar um intervalo aceitável.

Neste caso, não utilizaria um atributo instante, já que o enunciado informa que isso ocorre no momento em que é registrado, ou seja, persistido no banco de dados.

Este Form Value Object deve ter um construtor aceitando os três campos: nome, email e descrição. Além do construtor, é interessante possuir um método para a conversão de DTO para a entidade.

Como havia mencionado, acredito que, neste momento, não seja necessário um Value Object para Email, já que isso pode ser excesso de Engenharia (over-engineering) e, assim como simplicidade ingênua, pode ser um fator negativo para a aplicação. Lembrando que há conceitos como KISS (Keep it simple, stupid), YAGNI (You ain't gonna need it) e Carga Cognitiva que recomendam exatamente isso.

Para fazer a interface (ponte de comunicação) com o banco de dados, utilizaria o padrão Repositório (Repository Pattern) e criaria uma interface chamada AutorRepository que extenderia JpaRepository. Dessa forma, já teria o CRUD (Create-Read-Update-Delete) do banco de dados.

Acho muito importante criar um DTO para retorna os dados do recurso. Usando um DTO como resposta, é possível restringir os dados que desejamos informar ao cliente. Dessa forma, podemos garantir que a fronteira do sistema esteja salvaguardada de uma melhor forma.

Além desses, é necessário criar um Controlador para atender às requisições HTTP do sistema. Dessa forma, é necessário criar um AutorController. Este controlador deve ser simples. Dessa forma, ele possuiria apenas um atributo chamado do tipo AutorRepository e um construtor com o parâmetro do tipo AutorRepository. Com isso conseguimos aumentar a flexibilidade do controlador e facilitar os testes automatizados. 

Este controlador deve possuir um método chamado cadastrarAutor para o cadastro de autores. O parâmetro deve ser um Form Value Object que citei anteriormente e ele deve possuir duas anotações: @RequestBody para fazer o tratamento de dados (parsing) enviados pelo lado cliente e @Valid para validação de dados. Além desse parâmetro, é interessante ter outro do tipo UriComponentsBuilder para criação de URIs. Dessa forma, podemos retornar o endereço do recurso recém-criado no cabeçalho da resposta.

Acredito que o tipo de dados de retorno deva ser um ResponseEntity com o tipo genérico do DTO de resposta.

[Voltar ao menu](#tópicos)

### Cadastro de email único

O sistema deve permitir apenas e-mails únicos.

#### Restrições

- <span style="color: red;">&cross;</span> O email do autor precisa ser único no sistema

#### Resultado esperado

- <span style="color: red;">&cross;</span> Erro de validação no caso de email duplicado

[Voltar ao menu](#tópicos)
