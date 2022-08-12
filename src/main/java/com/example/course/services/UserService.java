package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.domain.User;
import com.example.course.dto.UserDTO;
import com.example.course.repository.UserRepository;
import com.example.course.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired //injeta o recurso automaticamente e já instancia (nao precisa dar new
	private UserRepository repo;

	//método que vai chamar o resource para retornar todos os users do bd
	public List<User> findAll(){
		
		//chamamos o repositório de users que vai retornar todos os usuários
		return repo.findAll();
		
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		
		return repo.insert(obj);
		
	}
	
	public void delete(String id) {
		//verificando primeiro se o id já existe
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		//rastreando o objeto a ser atualizado
		User newObj = findById(obj.getId());
		//atualizando o obj
		updateData(newObj, obj);
		
		//chamando o repositório para salvas as alterações
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
}
