package com.example.course.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.domain.Post;
import com.example.course.resources.util.URL;
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
	
	
	@RequestMapping(value="/titlesearch") //informando que ela implementa a requisição GET e que tem um valor no caminho
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue="") String text){
																	//@RequestParam: informa que vamos ter uma variavel, nesse caso, seu nome eh texto e tem valor default vazio
		
		/*ResponseEntity deve representar a resposta HTTP inteira. Você pode controlar
		 *  tudo o que for necessário: código de status, cabeçalhos e corpo.
		 *   @ResponseBody é um marcador para o corpo da resposta HTTP e @ResponseStatus
		 *    declara o código de status da resposta HTTP.*/
		
		
		//fazendo a decodificaçao do texto que recebemos como parametro
		text = URL.decodeParam(text);
		
		//procurando posts que tenham no título o parametro que recebermos
		List<Post> list = service.findByTitle(text);

		//retornando o único post que buscamos por id no bd
		return ResponseEntity.ok().body(list); //mandamos mensagem de ok e o json com o post
	}
	
	@RequestMapping(value = "/fullsearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue="") String text,
			@RequestParam(value = "minDate", defaultValue="") String minDate,
			@RequestParam(value = "maxDate", defaultValue="") String maxDate){
		
		//fazendo a decodificaçao do texto que recebemos como parametro
		text = URL.decodeParam(text);
		
		//convertendo de string para data na nossa classe util URL
		// new Date(0L) siginifica a data mínima aceita pelo java, é uma constante tipo: 1/1/1979
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		
		List<Post> list = service.fullSearch(text, min, max);
		
		return ResponseEntity.ok().body(list);
		
	}
	
	
}
