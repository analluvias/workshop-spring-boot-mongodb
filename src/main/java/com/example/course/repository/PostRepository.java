package com.example.course.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.course.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ //<Classe que o repositório gerencia, tipo do id>

	//para que essa assinatura funcione, eh importante que o nosso bd (ou seja: a classe Post)
	//tenha um campo chamado "title".
	List<Post> findByTitleContainingIgnoringCase(String text);
	
	
	// vou efetuar a busca por "title" que cada post tem como parametro
	//para informar que vou usar o peimeiro parâmetro do método, uso ?0
	//$options: nesse caso, informo que quero ignorar maiusculas e minusculas
	@Query("{ 'title':  { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
}
