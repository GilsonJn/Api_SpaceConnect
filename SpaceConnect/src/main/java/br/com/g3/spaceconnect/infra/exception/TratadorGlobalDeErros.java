package br.com.g3.spaceconnect.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorGlobalDeErros {

    // Tratamento para quando o ID do Dispositivo não existir no banco (Erro 404)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    // Tratar regras de negócio (Erro 400 amigável com a mensagem da regra)
    @ExceptionHandler(br.com.g3.spaceconnect.domain.ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(br.com.g3.spaceconnect.domain.ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Record interno apenas para formatar a mensagem de erro de forma limpa
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}