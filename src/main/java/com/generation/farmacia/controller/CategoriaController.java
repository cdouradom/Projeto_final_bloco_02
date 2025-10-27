package com.generation.farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")   
public class CategoriaController {   

    @Autowired // injection
    private CategoriaRepository categoriaRepository;

    @GetMapping // equivalente ao SELECT * FROM tb_categorias;
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());       
        
    }

    @GetMapping("id/{id}") // equivalente ao SELECT * FROM tb_categorias WHERE id = ?;
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        
    }

    @GetMapping("/nome/{nome}") // equivalente ao SELECT * FROM tb_categorias WHERE nome LIKE "%nome%";
    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
        
    }
   
    @PostMapping // equivalente ao INSERT INTO tb_categorias (nome, descricao) VALUES ('nome', 'descricao');
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria) { // Extrai o corpo da requisição e define o método postCategoria que recebe uma Categoria válida e retorna uma Categoria dentro de um ResponseEntity
        categoria.setId(null); 
        return ResponseEntity.status(HttpStatus.CREATED) 
            .body(categoriaRepository.save(categoria));         
        
    }

    @PutMapping // equivalente ao UPDATE tb_categorias SET nome = 'nome' WHERE id = ?;
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) { //	 Define o método put que recebe uma Categoria no corpo da requisição e retorna uma Categoria dentro de um ResponseEntity
		return categoriaRepository.findById(categoria.getId()) 
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria))) 
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); 
		
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) 
    @DeleteMapping("id/{id}") // equivalente ao DELETE FROM tb_categorias WHERE id = ?;
    public void delete(@PathVariable Long id) { // Extrai o ID da URL e define o método delete que não retorna nada
        Optional<Categoria> categoria = categoriaRepository.findById(id); 
        if (categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // Se a categoria não existir, lança uma exceção com status 404 (Not Found)
            categoriaRepository.deleteById(id); // Se existir, deleta o categoria pelo ID

    }

}
