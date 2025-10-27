package com.generation.farmacia.controller; // Define o pacote controller onde a classe ProdutoController está localizada

import java.util.List;// Importa a classe List para definir listas de objetos

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired para injeção de dependência
import org.springframework.http.HttpStatus; // Importa a enumeração HttpStatus para definir códigos de status HTTP
import org.springframework.http.ResponseEntity; //  Importa a classe ResponseEntity para representar respostas HTTP
import org.springframework.web.bind.annotation.CrossOrigin; // Importa a anotação CrossOrigin para configurar CORS
import org.springframework.web.bind.annotation.DeleteMapping; // Importa a anotação DeleteMapping para mapear requisições DELETE
import org.springframework.web.bind.annotation.GetMapping; // Importa a anotação GetMapping para mapear requisições GET
import org.springframework.web.bind.annotation.PathVariable; // Importa a anotação PathVariable para extrair variáveis da URL 
import org.springframework.web.bind.annotation.PostMapping; // Importa a anotação PostMapping para mapear requisições POST  
import org.springframework.web.bind.annotation.PutMapping; // Importa a anotação PutMapping para mapear requisições PUT
import org.springframework.web.bind.annotation.RequestBody; // Importa a anotação RequestBody para extrair o corpo da requisição
import org.springframework.web.bind.annotation.RequestMapping; // Importa a anotação RequestMapping para mapear URLs
import org.springframework.web.bind.annotation.ResponseStatus;  // Importa a anotação ResponseStatus para definir o status de resposta HTTP
import org.springframework.web.bind.annotation.RestController; // Importa a anotação RestController para definir um controlador REST
import org.springframework.web.server.ResponseStatusException;  // Importa a classe ResponseStatusException para lançar exceções com status HTTP

import com.generation.farmacia.model.Produto; // Importa a classe Produto do pacote model
import com.generation.farmacia.repository.CategoriaRepository; // Importa a interface CategoriaRepository
import com.generation.farmacia.repository.ProdutoRepository; // Importa a interface ProdutoRepository

import jakarta.validation.Valid; // Importa a anotação Valid para validação de dados


// Define a classe ProdutoController como um controlador REST
@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

    // Retorna todos os produtos
    @GetMapping
	public ResponseEntity<List<Produto>> getAll() {
	    return ResponseEntity.ok(produtoRepository.findAll());
	}

    // Retorna um produto pelo ID
	@GetMapping("id/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) { 
		return produtoRepository.findById(id) 
				.map(resposta -> ResponseEntity.ok(resposta)) // Se encontrar, retorna com status 200
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Caso contrário, 404	
	}

    // Retorna produtos que contenham o nome informado
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

    // Retorna produtos com preço maior ou igual ao valor informado (ordem crescente)
    @GetMapping("/maiorouigual/{preco}")
    public ResponseEntity<List<Produto>> getByPrecoMaiorOuIgual(@PathVariable Float preco) {
        return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanEqualOrderByPrecoAsc(preco));
    }

    // Retorna produtos com preço menor ou igual ao valor informado (ordem decrescente)
    @GetMapping("/menorouigual/{preco}")
    public ResponseEntity<List<Produto>> getByPrecoMenorOuIgual(@PathVariable Float preco) {
        return ResponseEntity.ok(produtoRepository.findByPrecoLessThanEqualOrderByPrecoDesc(preco));
    }

    // Cria um novo produto
    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) { 
        if (produto.getCategoria() == null || !categoriaRepository.existsById(produto.getCategoria().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente");
        }
        produto.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    // Atualiza um produto existente
    @PutMapping 
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
        // Verifica se o produto existe
        if (!produtoRepository.existsById(produto.getId())) {
            return ResponseEntity.notFound().build();
        }

        // Verificações de integridade se categoria e tipo existem, se não, exibe mensagem
        if (produto.getCategoria() == null || !categoriaRepository.existsById(produto.getCategoria().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente", null);
        }

        // Salva e retorna estando td ok
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
	}

    // Deleta um produto pelo ID
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	@DeleteMapping("id/{id}") 
	public void delete(@PathVariable Long id) { 
		java.util.Optional<Produto> produto = produtoRepository.findById(id);
       // Se o produto não existir, lança exceção 404
        if (produto.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);  
		
            produtoRepository.deleteById(id); // Se existir, deleta a Produto pelo ID
	}
}