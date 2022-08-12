package com.example.course.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.course.services.exception.ObjectNotFoundException;

@ControllerAdvice //indica que a classe vai ser reponsável por tratar possíveis erros nas minhas requisições
public class ResourceExceptionHandler {
	
	//Quando a exceção do tipo ObjectNotFoundException for estourada, eu vou gerar um objeto StandardError e 
	//retornar esse objeto pra o postman
	@ExceptionHandler(ObjectNotFoundException.class) // informando que eh o tratador dessa exceção
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		//gerando o status http que vai receber o valor 404 - objeto não encontrado
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado",
				e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}

}
