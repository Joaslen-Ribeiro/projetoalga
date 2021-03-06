package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.domain.exception.NegocioException;



@ControllerAdvice               // é um compontente do spring a classe, mas pra tratar excesçao
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;  // instanciamos para jogar a mensagem de erro pro consumidor da api 
	
	public ApiExceptionHandler(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		List<Problema.Campo> campos = new ArrayList<>();
	
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			
			campos.add(new Problema.Campo(nome,mensagem));
		}
		
	Problema problema = new Problema();   // criaçao do objeto pra mostrar uma mensagem ao consumidor da API
	problema.setStatus(status.value());
	problema.setDatahora(OffsetDateTime.now());
	problema.setTitulo("um ou mais campos estão invalidos. Faça o preenchimento correto! ");
	problema.setCampos(campos);
	
	//	return super.handleMethodArgumentNotValid(ex, headers, status, request);
		return handleExceptionInternal(ex, problema, headers, status, request); // responde um corpo na requisiçao que nao é possivel
	}
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		
		Problema problema = new Problema();   // criaçao do objeto pra mostrar uma mensagem ao consumidor da API
		problema.setStatus(status.value());
		problema.setDatahora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		
		return handleExceptionInternal(ex, problema,new HttpHeaders(), status, request );
	}
	
	
	
}
