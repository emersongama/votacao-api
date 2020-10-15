package br.com.earg.votacao.api.configuration;

import br.com.earg.votacao.api.service.exception.NaoEncontradoException;
import br.com.earg.votacao.api.service.exception.NegocioException;
import br.com.earg.votacao.api.shared.dto.ApiErrorDTO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ApiErrorDTO> handleValidacao(MethodArgumentNotValidException exception) {
        List<ApiErrorDTO> dtos = new ArrayList<>();
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            ApiErrorDTO erro = new ApiErrorDTO(e.getField(), messageSource.getMessage(e, LocaleContextHolder.getLocale()));
            dtos.add(erro);
        });
        return dtos;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NaoEncontradoException.class})
    public ApiErrorDTO handleNaoEncontradoException(NaoEncontradoException ex) {
        return obterErro(ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NegocioException.class})
    public ApiErrorDTO handleNegocioException(NegocioException ex) {
        return obterErro(ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, NullPointerException.class, RuntimeException.class})
    public ApiErrorDTO handleGeral(Exception ex) {
        return obterErro(ex);
    }

    private ApiErrorDTO obterErro(Exception ex) {
        return new ApiErrorDTO()
                .campo(ex.getClass().getSimpleName())
                .mensagem(ex.getMessage())
                .stacktrace(ExceptionUtils.getRootCauseMessage(ex));
    }
}
