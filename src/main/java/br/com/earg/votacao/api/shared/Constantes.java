package br.com.earg.votacao.api.shared;

public interface Constantes {

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
