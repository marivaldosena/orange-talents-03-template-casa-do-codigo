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
    - [Alterações de implementação do cadastro de email único](#alterações-de-implementação-do-cadastro-de-email-único)
  - [Cadastro de categoria](#cadastro-de-categoria)
    - [Implementação de cadastro de categoria](#implementação-de-cadastro-de-categoria)
    - [Cadastro de validador genérico](#cadastro-de-validador-genérico)
  - [Criar um novo livro](#criar-um-novo-livro)
    - [Implementação de Criar um novo livro](#implementação-de-criar-um-novo-livro)
    - [Alterações de implementação de Criar um novo livro](#alterações-de-implementação-de-criar-um-novo-livro)
  - [Exibir lista de livros](#exibir-lista-de-livros)

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

# Atividades

Ao longo deste projeto, será necessário a realização de algumas atividades para atestar o conhecimento adquirido. As atividades devem ser resolvidas estritamente de acordo com o enunciado.

## Cadastro novo autor

É necessário cadastrar um novo autor no sistema. Todo autor tem um nome, email e uma descrição. Também queremos saber o instante exato que ele foi registrado.

### Restrições

- <span style="color: green;">&check;</span> O instante não pode ser nulo
- <span style="color: green;">&check;</span> O email é obrigatório
- <span style="color: green;">&check;</span> O email tem que ter formato válido
- <span style="color: green;">&check;</span> O nome é obrigatório
- <span style="color: green;">&check;</span> A descrição é obrigatória e não pode passar de 400 caracteres

### Resultado esperado

- <span style="color: green;">&check;</span> Um novo autor criado e status 200 retornado

[Voltar ao menu](#tópicos)

### Implementação do Cadastro novo autor

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

## Cadastro de email único

O sistema deve permitir apenas e-mails únicos.

### Restrições

- <span style="color: green;">&check;</span> O email do autor precisa ser único no sistema

### Resultado esperado

- <span style="color: green;">&check;</span> Erro de validação no caso de email duplicado

[Voltar ao menu](#tópicos)

### Implementação do Cadastro de email único

Para impedir a duplicação de e-mails, terei de incluir unique = true na anotação @Column do atributo email na classe Autor para fazer com que o Data Definition Language (DDL) da tabela seja alterado para refletir as últimas mudanças. Dessa forma, essa regra estará presente no banco de dados.

Além de alterar o DDL da entidade, é necessário incluir alguma forma de verificação de e-mails duplicados no CadastroDeAutorForm para validar na fronteira de entrada de dados. Sendo assim, poderíamos impedir que o fluxo de dados prosseguisse desnecessariamente.

Há algumas formas de fazer essa validação. A primeira pode ser utilizando o Repository no construtor do Form e verificando se existe um valor duplicado. Essa é a forma mais simples para resolver este problema. No entanto, pode aumentar o acoplamento entre camadas que, em teoria, deveriam se manter separadas.

Outra opção é criar uma anotação que faça esse tipo de tarefa. Dessa forma, haveria menor acoplamento e, se a anotação for genérica o suficiente, poderia ser reaproveitada para outras classes e campos.

Outra possível solução seria validar no próprio controlador e fazer deixar as outras validações para o Form.

Acho que, neste momento, para manter a solução simples e diminiuir a carga intrínseca, é melhor passar o Repository para o Form.

[Voltar ao menu](#tópicos)

### Alterações de implementação do cadastro de email único

A solução que havia pensado com a inclusão de AutorRepository como parâmetro do construtor de CadastroDeAutorForm não é elegante, já que aumenta o acoplamento entre as camadas. Além disso, o erro retornado seria IllegalArgumentException se quisesse manter o Form sem pacotes que lidem com HTTP. Por este motivo, tive que optar pela solução que havia proposto com anotações, já que é o que possui menor acoplamento.

Como mencionei, a implementação que fiz foi um pouco diferente da que o especialista utilizou, mas semanticamente equivalentes. No meu caso, usei um ConstraintValidator e uma anotação ambos provenientes do pacote javax.validation.

Usei a documentação do Spring e o seguinte link como referência: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation-beanvalidation-spring-constraints. 
Meu código permite que o Controller continue inalterado e a única adição ao CadastroDeAutorForm seria a anotação @EmailUnicoConstraint no atributo email. Fora isso, seriam criadas uma interface e uma implementação do ConstraintValidator.

A outra possibilidade, conforme mostrado pelo especialista, seria utilizar as validações do próprio Spring, ao invés do pacote javax.validation.

[Voltar ao menu](#tópicos)

## Cadastro de categoria

Toda categoria precisa de um nome

### Restrições

- O nome é obrigatório
- O nome não pode ser duplicado

### Resultado esperado

- Uma nova categoria cadastrada no sistema e status 200 de retorno
- Caso alguma restrição não seja atendida, retorne 400 e um JSON informando os problemas de validação

[Voltar ao menu](#tópicos)

### Implementação de cadastro de categoria

Acredito que antes de começar, eu faria uma mudança em relação a estrutura anterior, ao invés de utilizar pastas por stereotypes como tinha feito, mudarei os pacotes agrupados por funcionalidades, tais como: Autor, Categoria, etc. Dessa forma, ficará mais simples modificar um funcionalidade sem precisar procurar uma classe em outro pacote. Poderia ter feito isso inicialmente, mas preferi segregar de acordo com o que papel que desempenhavam para demonstrar minha linha de raciocínio e adotar uma arquitetura evolutiva nesta etapa por ser um cenário mais próximo ao encontrado no dia-a-dia de desenvolvimento.

Para cadastrar uma categoria, é necessário criar uma entidade Categoria com o atributo nome do tipo String e com a anotação @Column(nullable = false, unique = true). Dessa forma, o Hibernate irá gerar um Data Definition Language (DDL) para a tabela indicando ao banco de dados que o atributo nome é obrigatório e não pode ser repetido. Outro ponto importante é criar dois construtores: um sem parâmetros para o Hibernate e outro com nome para consumo da aplicação.

Após isso, criaria uma interface chamada CategoriaRepository que extende CrudRepository, assim conseguiria a capacidade de persistência em banco de dados.

Para o cadastro da categoria, é interessante usar um Form Value Object chamado CadastroDeCategoriaForm que possui um atributo nome e um construtor para recebê-lo como parâmetro. O uso desta classe é para a validação na fronteira de entrada de dados e para limitar a inferência da estrutura interna de persistência que usamos. Dessa forma, usuários mal-intencionados teriam maior dificuldade em tentativas de ataques baseados nos conhecimentos que expomos por meio das entidades que enviamos diretamente.

Apesar de não o enunciado não especificar o comprimento de caracteres do nome de categoria, acho interessante trabalhar com o intervalo de 2 a 120. Por este motivo, utilizaria as anotações @NotNull (para evitar que o campo seja nulo), @NotEmpty (para evitar que esteja vazio) e/ou @Size(min = 2, max = 120) para delimitar o comprimento.

Para a resposta do cadastro, também utilizaria outra classe ao invés da entidade Categoria. No entanto, neste caso seria um Data Transfer Object (DTO) para personalizar a resposta e esconder detalhes que não queremos enviar ao cliente, tais como: identificador, data de criação do recurso, entre outros.

Além desses elementos, criaria um controlador chamado CategoriaController para lidar com as requisições HTTP. Este controlador possuiria um atributo do tipo CategoriaRepository e um construtor para acomodar este parâmetro.

Além disso, é necessário criar um método para receber os dados. A assinatura do método seria: public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody @Valid CadastroDeCategoriaForm form, URIComponentsBuilder uriBuilder). Este método retornará um ResponseEntity com o tipo genérico CategoriaDto e aceitará um CadastroDeCategoriaForm para validação de dados e um URIComponentsBuilder para gerar URIs personalizados para serem inseridos no cabeçalho de resposta informando a URI do recurso recém-criado. Este método terá a anotação @PostMapping para receber requisições via POST.

É interessante anotar o controlador com @RequestMapping e informar a URL padrão para ele. Sugiro usar um atributo ou método estático para conter a String da URL. Dessa forma, se precisarmos mudar a URL, será realizada em apenas um ponto do controlador e será refletido automaticamente nos outros, vide cabeçalho Location com a URI do recurso.

[Voltar ao menu](#tópicos)

## Cadastro de validador genérico

Tanto para o cadastro do autor quanto para o cadastro da categoria, foi necessário realizar uma validação de valor único no sistema. Neste caso, só muda um detalhe da query que estamos executando para fazer a verificação. E agora, será que você consegue criar seu validador customizado para reutilizá-lo nas validações de email de autor e nome de categoria?

[Voltar ao menu](#tópicos)

### Implementação de validador genérico

Para criar o validador genérico, a única coisa que fiz foi criar uma anotação @interface e uma classe ConstraintValidator que implementa a interface anotada.

Por intermédio da anotação, passei dois argumentos: um para a entidade e o outro para o campo.

Comecei a implementar como um só validador ao invés de dois para evitar duplicidade de código. Portanto, utilizarei o link da solução anterior.

O link de referência é: https://github.com/marivaldosena/orange-talents-03-template-casa-do-codigo/compare/v0.0.3...v0.0.4.

[Voltar ao menu](#tópicos)

## Criar um novo livro

Nesta atividade, você será responsável pela criação de livros.

### Necessidades

- Um título
- Um resumo do que vai ser encontrado no livro
- Um sumário de tamanho livre. O texto deve entrar no formato markdown, que é uma string. - Dessa forma ele pode ser formatado depois da maneira apropriada.
- Preço do livro
- Número de páginas
- Isbn(identificador do livro)
- Data que ele deve entrar no ar(de publicação)
- Um livro pertence a uma categoria
- Um livro é de um autor

### Restrições

- Título é obrigatório
- Título é único
- Resumo é obrigatório e tem no máximo 500 caracteres
- Sumário é de tamanho livre.
- Preço é obrigatório e o mínimo é de 20
- Número de páginas é obrigatória e o mínimo é de 100
- Isbn é obrigatório, formato livre
- Isbn é único
- Data que vai entrar no ar precisa ser no futuro
- A categoria não pode ser nula
- O autor não pode ser nulo

### Resultado esperado

- Um novo livro precisa ser criado e status 200 retornado
- Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação

[Voltar ao menu](#tópicos)

### Implementação de Criar um novo livro

Para o recurso Livro, criaria uma entidade Livro para a persistência de dados que conteria os seguintes campos:

- titulo do tipo String com anotação @Column(nullable = false, unique = true) para que o título seja obrigatório e único.
- resumo do tipo String com anotação @Column(nullable = false, length = 500) para que o resumo seja obrigatório e possua comprimento máximo de 500 caracteres.
- sumario do tipo String e com anotação @Type("org.hibernate.type.Type.TextType") para que o sumário seja armazenado como um elemento do tipo SQL TEXT (ou equivalente), ou seja, virtualmente ilimitado.
- preco do tipo BigDecimal com anotação @Column(nullable = false).
- numeroDePaginas do tipo Integer com a anotação @Column(name = "numero_paginas", nullable = false) e @Min(100) para que o número de páginas seja obrigatório e com o valor mínimo de 100 páginas.
- isbn do tipo String com a anotação @Column(nullable = false, unique = true) para que o ISBN seja único e obrigatório.
- dataDePublicacao do tipo LocalDateTime. Como não foi especificado que a data é obrigatória, não colocarei anotação na entidade.
- categoria do tipo Categoria com anotação @ManyToOne para que sinalize ao banco de dados que a tabela Livros possui chave estrangeira que referencia à tabela Categorias, ou seja, um livro pertence a uma categoria e uma categoria possui diversos livros.
- autor do tipo Autor com anotação @ManyToOne, ou seja, é um relacionamento um-para-muitos como Categoria.

Após a entidade Autor, criaria um repositório para livro e um controlador. O repositório seria uma interface que herdaria de CrudRepository. O controlador terá um método para criar livros com a anotação @PostMapping. Os parâmetros do método de criação de autores seriam um Form Value Object para validação de dados e um URIComponentsBuilder para geração de URI para o recurso recém-criado e seria criado um cabeçalho Location com o valor.

O passo-a-passo deste recurso não difere muito dos recursos Autor e Categoria, ou seja, é necessário criar um DTO para exposição de dados e um Form Value Object (ou DTO) para validação na fronteira de entrada de dados.

[Voltar ao menu](#tópicos)

### Alterações de implementação de Criar um novo livro

Tive que inserir anotações de obrigatoriedade de dados em sumário e data de publicação já que eram obrigatórias de fato.

No caso de publicação, é necessário inserir anotação adicional. Neste caso, o JsonFormat com parâmetros pattern para formato personalizado e shape para String.

**Observação:** *o Jackson necessita de um método setter para desserializar uma data em formato personalizado e não pelo construtor.*

Há outra forma de incluir as entidades Autor e Categoria no Form Value Object, no entanto, é necessário a utilização de EntityManager ao invés de repositórios. Por um lado simplifica o número de dependências externas do controlador de Livros, mas aumenta a complexidade com Queries e/ou TypedQueries no método toEntity do Form Value Object. Optei pela abordagem com repositórios, já que possuem métodos de busca personalizada.

Durante o desenvolvimento, criei outra validação genérica (Constraint) para verificar a existência de autores e categorias.

Achei interessante utilizar uma classe para a criação de Livros usando o padrão Builder, já que há diversos atributos obrigatórios e isso me permitiu simplificar a geração de livros no método toEntity do Form Value Object.

[Voltar ao menu](#tópicos)

## Exibir lista de livros

Para que seja fácil pegar um id do livro, vamos exibir a lista de livros cadastrados.

### Resultado esperado

- um json com a lista de livros com id e nome do livro

[Voltar ao menu](#tópicos)