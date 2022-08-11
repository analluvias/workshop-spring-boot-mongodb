package com.example.course.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.course.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{ //<Classe que o repositório gerencia, tipo do id>

	
	
}
