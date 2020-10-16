package br.com.earg.votacao.api.shared;

public interface Constantes {

    Integer UM_MINUTO = 1;

    interface Mensagem {
        String MSG_PAUTA_NAO_ENCONTRADA = "Pauta não encontrada.";
        String MSG_VOTACAO_NAO_ENCONTRADA = "Votação não encontrada.";
        String MSG_VOTACAO_ENCERRADA = "A votação já foi encerrada.";
        String MSG_VOTACAO_EM_ANDAMENTO = "A votação já está em andamento.";
        String MSG_ASSOCIADO_NAO_ENCONTRADO = "Associado não encontrado.";
        String MSG_ASSOCIADO_JA_VOTOU = "O associado de cpf %s já votou.";
    }

    interface Profile {
        String DEV = "dev";
        String SWAGGER = "swagger";
    }

    interface Env {
        String PROPRIEDADE_SPRING_PROFILES_DEFAULT = "spring.profiles.default";
        String PROPRIEDADE_SERVER_SSL_KEY_STORE = "server.ssl.key-store";
        String PROPRIEDADE_SERVER_PORT = "server.port";
        String PROPRIEDADE_SERVER_SERVLET_CONTEXT_PATH = "server.servlet.context-path";
        String PROPRIEDADE_SPRING_APPLICATION_NAME = "spring.application.name";
    }
}
