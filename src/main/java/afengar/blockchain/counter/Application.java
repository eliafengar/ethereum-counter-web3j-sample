package afengar.blockchain.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import afengar.blockchain.counter.handlers.CounterHandler;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> routerFunction(CounterHandler counterHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/counter/increment"), counterHandler::increment)
				.andRoute(RequestPredicates.GET("/counter/decrement"), counterHandler::decrement)
				.andRoute(RequestPredicates.GET("/counter/counts"), counterHandler::counts)
				.andRoute(RequestPredicates.GET("/network/deployContract"), counterHandler::deployContract)
				.andRoute(RequestPredicates.GET("/network/loadContract"), counterHandler::loadContract);
	}

	@Bean
	public Web3j web3() {
		return Web3j.build(new HttpService("http://<Ethereum Transaction Node Host Name or IP Address>:8545"));
	}
}
