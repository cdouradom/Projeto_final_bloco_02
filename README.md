
  <header class="center">
    <h1><strong>PG 2 — Backend com Spring Boot: Farmácia Bem Estar</strong></h1>
    <div class="center">
      <img src="https://i.imgur.com/w8tTOuT.png" alt="Farmácia Bem Estar" style="max-width:320px;">
    </div>
    <div class="badges center" aria-hidden="true">
      <img src="https://img.shields.io/github/languages/top/rafaelq80/aulas_java_t82?style=flat-square" alt="">
      <img src="https://img.shields.io/github/repo-size/rafaelq80/aulas_java_t82?style=flat-square" alt="">
      <img src="https://img.shields.io/github/last-commit/rafaelq80/aulas_java_t82?style=flat-square" alt="">
      <img src="https://img.shields.io/badge/status-Em%20Construção-yellow" alt="Status: Em Construção">
    </div>
  </header>

  <section>
    <h2><strong>📚 Descrição</strong></h2>
    <p>
      <strong>Farmácia Bem Estar</strong> é um sistema de <strong>e-commerce</strong> para produtos de farmácia e bem-estar, desenvolvido em <strong>Java com Spring Boot</strong>.
      A aplicação oferece cadastro, listagem, consulta, atualização e remoção de produtos, categorias e usuarios e gerencia relacionamentos entre as entidades:
    </p>
    <ul>
      <li><strong>Usuário → Produto</strong>: cada produto é vinculado a um usuário (quem cadastrou).</li>
      <li><strong>Categoria → Produto</strong>: cada produto pertence a uma categoria.</li>
    </ul>
    <p>
      A API inclui <strong>segurança via JWT</strong>, com métodos privados exigindo autenticação. Um usuário <strong>root</strong> genérico é fornecido para testes iniciais.
      O projeto tem fins educacionais e demonstra conceitos de POO, Spring Boot, JPA e autenticação em API REST.
    </p>
  </section>

  <section>
    <h2><strong>🚀 Funcionalidades</strong></h2>
    <ul>
      <li><strong>CRUD Produtos</strong> — criar, listar, consultar (por id/nome), atualizar e excluir produtos.</li>
      <li><strong>CRUD Categorias</strong> — criar, listar, atualizar e excluir categorias.</li>
      <li><strong>CRUD Usuários</strong> — gerenciar usuários (acesso de administração/root para operações sensíveis).</li>
      <li><strong>Segurança JWT</strong> — endpoints privados protegidos por token (Authorization: Bearer &lt;token&gt;).</li>
    </ul>
  </section>

  <section>
    <h2><strong>🛠️ Tecnologias</strong></h2>
    <ul>
      <li>Java 17</li>
      <li>Spring Boot 3.5</li>
      <li>Spring Security (JWT)</li>
      <li>Spring Data JPA / Hibernate</li>
      <li>Banco relacional (MySQL)</li>
      <li>Maven</li>
    </ul>
  </section>

  <section>
    <h2><strong>📂 Estrutura do Projeto</strong></h2>
    <pre><code>
src
 └─ main
     ├─ java
     │   └─ com.generation.farmacia
     │        ├─ controller
     │        ├─ model
     │        ├─ repository
     │        ├─ service
     │        └─ security
     └─ resources
         ├─ application.properties
    </code></pre>
  </section>

  <section>
    <h2><strong>📘 Diagramas</strong></h2>

<h3><strong>Diagrama de Classes</strong></h3>

<p>O bloco abaixo contém o diagrama de classes em formato Mermaid — ele pode ser renderizado por ferramentas que suportem Mermaid.</p>

```mermaid
classDiagram
class Usuario {
  - id: Long
  - nome: String
  - email: String
  - senha: String
  + Usuario()
  + getId(): Long
  + getNome(): String
  + setNome(String): void
  + getEmail(): String
  + setEmail(String): void
}

class Categoria {
  - id: Long
  - nome: String
  - descricao: String
  + Categoria()
  + getId(): Long
  + getNome(): String
  + setNome(String): void
  + getDescricao(): String
  + setDescricao(String): void
}

class Produto {
  - id: Long
  - data: Timestamp
  - nome: String
  - descricao: String
  - foto: String
  - preco: Double
  - quantidade: int
  - categoria: Categoria
  - usuario: Usuario
  + Produto()
  + getId(): Long
  + getNome(): String
  + setNome(String): void
  + getDescricao(): String
  + setDescricao(String): void
  + getPreco(): Double
  + setPreco(Double): void
  + getQuantidade(): int
  + setQuantidade(int): void
}

Usuario --> Produto
Categoria --> Produto
```

<h3><strong>Diagrama Entidade-Relacional (DER)</strong></h3>

<pre><code class="language-mermaid">
 
erDiagram
USUARIO ||--o{ PRODUTO : "cadastra"
CATEGORIA ||--o{ PRODUTO : "classifica"

USUARIO {
  Long id
  String nome
  String email
  String senha
}

CATEGORIA {
  Long id
  String nome
  String descricao
}

PRODUTO {
  Long id
  Timestamp data
  String nome
  String descricao
  String foto
  Double preco
  int quantidade
  Long categoria_id
  Long usuario_id
}

  <img width="363" height="554" alt="image" src="https://github.com/user-attachments/assets/c2c81788-bbeb-48f9-a21e-d6cf97115b58" />

    </code></pre>

</section>

  <section>
    <h2><strong>✨ Slogan</strong></h2>
    <p class="slogan"><strong>“Farmácia Bem Estar — Cuidando da sua saúde com confiança e praticidade!”</strong></p>
  </section>

  <section>
    <h2><strong>📌 Requisitos</strong></h2>
    <ul>
      <li>Java JDK 17+</li>
      <li>IDE: Eclipse / STS / IntelliJ IDEA</li>
      <li>Banco: MySQL</li>
      <li>Maven</li>
      <li>Ferramenta de testes: Insomnia / Postman</li>
    </ul>
  </section>

  <section>
    <h2><strong>❗Como Executar (local)</strong></h2>
    <ol>
      <li><strong>Clonar o repositório</strong>:
        <pre><code>git clone git@github.com:cdouradom/farmacia_bem_estar.git</code></pre>
      </li>
      <li><strong>Configurar banco</strong>: atualize o arquivo <code>src/main/resources/application.properties</code> com suas credenciais e URL do banco.</li>
      <li><strong>Importar na IDE</strong>: importe como projeto Maven/Gradle e aguarde a indexação.</li>
      <li><strong>Executar</strong>: rode a classe principal <code>FarmaciaApplication.java</code>.</li>
      <li><strong>Testar</strong>: use Insomnia/Postman para acessar os endpoints.</li>
    </ol>
  </section>

  <section>
    <h2><strong>💬  Contribuição</strong></h2>
    <p>
      Este repositório é educacional. Contribuições são bem-vindas:
    </p>
    <ul>
      <li>Criar <strong>issue</strong> descrevendo bug ou sugestão.</li>
      <li>Enviar <strong>pull request</strong> com melhorias.</li>
      <li>Adicionar testes, documentação ou scripts para dev/CI.</li>
    </ul>
  </section>

  <footer>
    <h3><strong> 👩‍💻 Autora</strong></h3>
    <p><strong>Cíntia Marques Dourado</strong></p>
    <p>Email: <a href="mailto:cdouradom@gmail.com">cdouradom@gmail.com</a></p>
    <p>GitHub: <a href="https://github.com/cdouradom" target="_blank" rel="noopener">cdouradom</a></p>
  </footer>

</body>
</html>
