package afengar.blockchain.counter.handlers;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.Contract;

import afengar.blockchain.counter.Counter;
import reactor.core.publisher.Mono;

@Component
public class CounterHandler {

	@Autowired
	private Web3j web3;

	@Value("${network.wallet.key}")
	private String walletKey;

	@Value("${network.contract.address}")
	private String contractAddress;

	private Counter contract;

	public CounterHandler() {

	}

	@PostConstruct
	public void init() {
		try {
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println(clientVersion);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public Mono<ServerResponse> counts(ServerRequest request) {
		BigInteger counts = new BigInteger("-1");
		CompletableFuture<BigInteger> result = this.contract.counts().sendAsync();
		try {
			counts = result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return ServerResponse.status(HttpStatus.BAD_REQUEST).build();
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).syncBody(counts);
	}

	public Mono<ServerResponse> increment(ServerRequest request) {
		BigInteger blockNumber = new BigInteger("-1");
		CompletableFuture<TransactionReceipt> result = this.contract.increment().sendAsync();
		try {
			TransactionReceipt reciept = result.get();
			blockNumber = reciept.getBlockNumber();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return ServerResponse.status(HttpStatus.BAD_REQUEST).build();
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).syncBody(blockNumber);
	}

	public Mono<ServerResponse> decrement(ServerRequest request) {
		BigInteger blockNumber = new BigInteger("-1");
		CompletableFuture<TransactionReceipt> result = this.contract.decrement().sendAsync();
		try {
			TransactionReceipt reciept = result.get();
			blockNumber = reciept.getBlockNumber();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return ServerResponse.status(HttpStatus.BAD_REQUEST).build();
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).syncBody(blockNumber);
	}

	public Mono<ServerResponse> deployContract(ServerRequest request) {
		try {
			Credentials credentials = Credentials.create(this.walletKey);

			CompletableFuture<Counter> result = Counter
					.deploy(this.web3, credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT, BigInteger.ZERO)
					.sendAsync();
			this.contract = result.get();
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse.status(HttpStatus.BAD_REQUEST).build();
		}
		return ServerResponse.ok().syncBody(this.contract.getContractAddress());
	}

	public Mono<ServerResponse> loadContract(ServerRequest request) {
		try {
			// String contractAddress = request.queryParam("contractAddress").get();

			Credentials credentials = Credentials.create(this.walletKey);

			this.contract = Counter.load(this.contractAddress, this.web3, credentials, Contract.GAS_PRICE,
					Contract.GAS_LIMIT);
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse.status(HttpStatus.BAD_REQUEST).build();
		}
		return ServerResponse.ok().build();
	}
}
