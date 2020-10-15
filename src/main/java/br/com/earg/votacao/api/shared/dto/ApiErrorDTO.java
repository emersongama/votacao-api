package br.com.earg.votacao.api.shared.dto;

import java.io.Serializable;

public class ApiErrorDTO implements Serializable {

    private String campo;
    private String mensagem;
    private String stacktrace;

    public ApiErrorDTO() {}

    public ApiErrorDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public ApiErrorDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public ApiErrorDTO campo(String campo) {
        this.campo = campo;
        return this;
    }

    public ApiErrorDTO mensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public ApiErrorDTO stacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
        return this;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    @Override
    public String toString() {
        return "ApiErrorDTO{" +
                "campo='" + campo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", stacktrace='" + stacktrace + '\'' +
                '}';
    }
}
