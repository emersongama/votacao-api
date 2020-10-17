package br.com.earg.votacao.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static br.com.earg.votacao.api.shared.Constantes.Env.*;
import static br.com.earg.votacao.api.shared.Constantes.Profile.DEV;

@EnableFeignClients
@SpringBootApplication
public class VotacaoApiApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(VotacaoApiApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(VotacaoApiApplication.class);
		adicionarProfileDefault(app);
		Environment env = app.run(args).getEnvironment();
		logInicializacao(env);
	}

	private static void adicionarProfileDefault(SpringApplication app) {
		Map<String, Object> defProperties = new HashMap<>();
		defProperties.put(PROPRIEDADE_SPRING_PROFILES_DEFAULT, DEV);
		app.setDefaultProperties(defProperties);
	}

	private static void logInicializacao(Environment env) {
		String nomeSistema = env.getProperty(PROPRIEDADE_SPRING_APPLICATION_NAME);
		String protocolo = env.getProperty(PROPRIEDADE_SERVER_SSL_KEY_STORE) != null ? "https" : "http";
		String host = obterHost();
		String porta = env.getProperty(PROPRIEDADE_SERVER_PORT);
		String contexto = obterContexto(env.getProperty(PROPRIEDADE_SERVER_SERVLET_CONTEXT_PATH));

		LOGGER.info(obterTextoInformacaoApp(), nomeSistema, protocolo, porta, contexto,
				protocolo, host, porta, contexto, env.getActiveProfiles());
	}

	private static String obterHost() {
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			LOGGER.warn("O host não pode ser determinado, localhost será usado por padrão.");
		}
		return hostAddress;
	}

	private static String obterContexto(String contexto) {
		return StringUtils.isBlank(contexto) ? "/" : contexto;
	}

	private static String obterTextoInformacaoApp() {
		return "\n----------------------------------------------------------\n\t" +
				"A aplicação '{}' está no ar! URLs de acesso:\n\t" +
				"Local: \t\t{}://localhost:{}{}\n\t" +
				"Externo: \t{}://{}:{}{}\n\t" +
				"Profile(s): \t{}\n----------------------------------------------------------";
	}
}
