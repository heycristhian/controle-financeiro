package br.com.controlefinanceiro.controller.handler;

import br.com.controlefinanceiro.controller.dto.response.ExceptionResponse;
import br.com.controlefinanceiro.controller.dto.response.FieldExceptionResponse;
import br.com.controlefinanceiro.exception.EntidadeNotFoundException;
import br.com.controlefinanceiro.exception.NegocioException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var response = ExceptionResponse.builder()
                .code(status.value())
                .status(status.getReasonPhrase())
                .message("There are fields with errors")
                .correlationId(getCorrelationId())
                .fields(getFieldsExceptionResponse(ex))
                .build();
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var response = handleExceptionResponse(httpStatus, "Internal server error");

        log.error("Internal Server Error: {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler({EntidadeNotFoundException.class, NegocioException.class})
    public ResponseEntity<ExceptionResponse> handleEntidadeNotFoundException(RuntimeException e) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var response = handleExceptionResponse(httpStatus, e.getLocalizedMessage());

        log.error("Bad Request: {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

    private List<FieldExceptionResponse> getFieldsExceptionResponse(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> FieldExceptionResponse.builder()
                        .message(error.getDefaultMessage())
                        .field(error.getField())
                        .parameter(error.getRejectedValue())
                        .build())
                .toList();
    }

    private ExceptionResponse handleExceptionResponse(HttpStatus httpStatus, String message) {
        return ExceptionResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .correlationId(getCorrelationId())
                .build();
    }

    private String getCorrelationId() {
        return MDC.get("correlationId");
    }
}
