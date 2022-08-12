package com.example.course.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.course.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ //<Classe que o repositório gerencia, tipo do id>

	
	
}
