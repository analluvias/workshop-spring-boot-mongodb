package com.example.course.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.domain.User;

@RestController //informo que vai ser um recurso
@RequestMapping(value="/users") //caminho do endpoint do recurso de users
public class UserResource {
	
	@RequestMapping(method=RequestMethod.GET) //informando que ela implementa a requisição GET
	public ResponseEntity<List<User>> findAll(){
		
		/*ResponseEntity deve representar a resposta HTTP inteira. Você pode controlar
		 *  tudo o que for necessário: código de status, cabeçalhos e corpo.
		 *   @ResponseBody é um marcador para o corpo da resposta HTTP e @ResponseStatus
		 *    declara o código de status da resposta HTTP.*/
		
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		
		return ResponseEntity.ok().body(list); //mandamos mensagem de ok e o json de users
	}

}
