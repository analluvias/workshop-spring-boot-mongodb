package com.example.course.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.course.domain.User;
import com.example.course.dto.UserDTO;
import com.example.course.services.UserService;

@RestController //informo que vai ser um recurso - ou seja: o que terá contato com a web
@RequestMapping(value="/users") //caminho do endpoint do recurso de users
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET) //informando que ela implementa a requisição GET
	public ResponseEntity<List<UserDTO>> findAll(){//Retorna uma lista UserDTO (padrão que só retorna o que tiver get dentro do DTO)
		
		/*ResponseEntity deve representar a resposta HTTP inteira. Você pode controlar
		 *  tudo o que for necessário: código de status, cabeçalhos e corpo.
		 *   @ResponseBody é um marcador para o corpo da resposta HTTP e @ResponseStatus
		 *    declara o código de status da resposta HTTP.*/
		
		//chamo o meu service, que vai chamar o resource para pegar no BD todos os meus users
		List<User> list = service.findAll();
		
		//Fazendo a transformação da minha lista de usuários para uma lista de UserDTO
		//Então o serviço só irá retornar os atributos que tiverem métodos get dentro de dto
		//.map(cada elemento x de list vai instanciar um novo UserDTO), criando assim nossa listDto
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto); //mandamos mensagem de ok e o json de users
	}
	
	@RequestMapping(value="/{id}") //informando que ela implementa a requisição GET e que tem um valor no caminho
	public ResponseEntity<UserDTO> findById(@PathVariable String id){//Retorna um UserDTO (só retorna o que tiver get dentro do DTO)
																	//@PathVariable: informa que o parametro virá do caminho no postman
		
		/*ResponseEntity deve representar a resposta HTTP inteira. Você pode controlar
		 *  tudo o que for necessário: código de status, cabeçalhos e corpo.
		 *   @ResponseBody é um marcador para o corpo da resposta HTTP e @ResponseStatus
		 *    declara o código de status da resposta HTTP.*/
		
		//chamo o meu service, que vai chamar o resource para pegar no BD esse user
		User user = service.findById(id);

		//retornando um UserDTO que é instanciado a partir do user que buscamos por id no bd
		return ResponseEntity.ok().body(new UserDTO(user)); //mandamos mensagem de ok e o json de users
	}
	
	@RequestMapping(method=RequestMethod.POST) //informando que ela implementa a requisição POST
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){//Retorna responseEntity sem corpo
																	//@RequestBody: informa que o parametro virá do body no postman
		
		/*ResponseEntity deve representar a resposta HTTP inteira. Você pode controlar
		 *  tudo o que for necessário: código de status, cabeçalhos e corpo.
		 *   @ResponseBody é um marcador para o corpo da resposta HTTP e @ResponseStatus
		 *    declara o código de status da resposta HTTP.*/
		
		//chamo o meu service, que vai transformar o DTO em USER
		User obj = service.fromDTO(objDto);
		
		//inserindo obj no db e pegando a resposta
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		//retornando mensagem de criado com o caminho criado(201) 
		return ResponseEntity.created(uri).build(); //mandamos mensagem de ok e o json de users
	}
	
	@DeleteMapping(value = "/{id}") //informando que é uma requisição do tipo Delete e que aceita um id dentro da url: localhost:8080/users/1
	public ResponseEntity<Void> delete(@PathVariable String id){ //@PathVariable informa que essa variável chega pela url
	
		/* ResponseEntity<Void> usamos para quando a nossa requisição dá um retorno vazio, ou seja, não vai retornar o user */
		
		service.delete(id); //chamando o service, que por sua vez vai chamar o repositório (que efetivamente fará a deleção)
		
		//esse retorna vai enviar a mensagem 204: que informa que não há mensagem de retorno
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}") //informando que é uma requisição do tipo PUT (update) e que aceita um id dentro da url: localhost:8080/users/1
	public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDTO objDTO){ //@PathVariable informa que essa variável chega pela url
																					 //@RequestBody informa que vai receber um json com os pedidos de update
		
		//de DTO para User
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		
		return ResponseEntity.noContent().build(); 
		
	}

}
