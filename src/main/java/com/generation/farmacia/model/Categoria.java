package com.generation.farmacia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // inidica ao spring que essa classe é uma entidade (tabela)
@Table(name = "tb_categorias") // Define o nome da tabela que sera criada no banco de dados
public class Categoria { //indica que a classe é uma entidade do JPA

    // Variáveis de instância (atributos) e validações dos dados / Atributos e métodos que serão implementados aqui
	@Id  // PRIMARY KEY(id) // indica que o atributo id é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT, quem cuida disso é o DB
	private Long id; // Define o atributo id

    @Column(length = 50) // Define o tamanho máximo do campo no banco de dados
	@NotBlank(message = "O atributo Nome é obrigatório!") // Impedir que a descricao seja em branco
	@Size(min = 3, max = 50, message = "O atributo Nome deve conter no mínimo 3 e no máximo 50 caracteres") // Define o tamanho mínimo e máximo do campo
	private String nome; // Define o atributo descricao 

    @Column(length = 200) // Define o tamanho máximo do campo no banco de dados
	@NotBlank(message = "O atributo Descricao é obrigatório!") // Impedir que a descricao seja em branco
	@Size(min = 10, max = 200, message = "O atributo Descricao deve conter no mínimo 10 e no máximo 200 caracteres") // Define o tamanho mínimo e máximo do campo
	private String descricao; // Define o atributo descricao 

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE) // Define o relacionamento um-para-muitos com Produto ( uma categoria para muitos produtos)   
	@JsonIgnoreProperties(value = "categoria", allowSetters = true) // Ignora a propriedade produto para evitar recursão infinita durante a serialização JSON
	private List<Produto> produto; // Define o atributo produto como uma lista de produto

    // Getters e Setters dos atributos
    public Long getId() { 
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    
}

