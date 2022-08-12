package com.example.course.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.domain.Post;
import com.example.course.services.PostService;

@RestController //informo que vai ser um recurso - ou seja: o que terá contato com a web
@RequestMapping(value="/posts") //caminho do endpoint do recurso de users
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}") //informando que ela implementa a requisição GET e que tem um valor no caminho
	public ResponseEntity<Post> findById(@PathVariable String id){//Retorna um UserDTO (só retorna o que tiver get dentro do DTO)
																	//@PathVariable: informa que o parametro virá do caminho no postman
		
		/*ResponseEntity deve representar a resposta HTTP inteira. Você pode controlar
		 *  tudo o que for necessário: código de status, cabeçalhos e corpo.
		 *   @ResponseBody é um marcador para o corpo da resposta HTTP e @ResponseStatus
		 *    declara o código de status da resposta HTTP.*/
		
		//chamo o meu service, que vai chamar o resource para pegar no BD esse post
		Post post = service.findById(id);

		//retornando o único post que buscamos por id no bd
		return ResponseEntity.ok().body(post); //mandamos mensagem de ok e o json com o post
	}
	

}
