package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.domain.Post;
import com.example.course.repository.PostRepository;
import com.example.course.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired //injeta o recurso automaticamente e já instancia (nao precisa dar new
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	
	//essa eh a função que vai chamar o query method que implementamos na interface repository
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoringCase(text);
	}
	
	
}
