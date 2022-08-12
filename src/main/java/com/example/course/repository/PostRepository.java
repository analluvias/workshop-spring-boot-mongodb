package com.example.course.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.course.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ //<Classe que o repositório gerencia, tipo do id>

	//para que essa assinatura funcione, eh importante que o nosso bd (ou seja: a classe Post)
	//tenha um campo chamado "title".
	List<Post> findByTitleContainingIgnoringCase(String text);
	
}
