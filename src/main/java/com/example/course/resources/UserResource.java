package com.example.course.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.domain.User;
import com.example.course.services.UserService;

@RestController //informo que vai ser um recurso - ou seja: o que terá contato com a web
@RequestMapping(value="/users") //caminho do endpoint do recurso de users
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET) //informando que ela implementa a requisição GET
	public ResponseEntity<List<User>> findAll(){
		
		/*ResponseEntity deve representar a resposta HTTP inteira. Você pode controlar
		 *  tudo o que for necessário: código de status, cabeçalhos e corpo.
		 *   @ResponseBody é um marcador para o corpo da resposta HTTP e @ResponseStatus
		 *    declara o código de status da resposta HTTP.*/
		
		//chamo o meu service, que vai chamar o resource para pegar no BD todos os meus users
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list); //mandamos mensagem de ok e o json de users
	}

}
