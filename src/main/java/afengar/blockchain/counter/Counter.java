package afengar.blockchain.counter;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>
 * Auto generated code.
 * <p>
 * <strong>Do not modify!</strong>
 * <p>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j
 * command line tools</a>, or the
 * org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen
 * module</a> to update.
 *
 * <p>
 * Generated with web3j version 3.5.0.
 */
public class Counter extends Contract {
    private static final String BINARY = "60806040526101a9806100136000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632baeceb714610067578063817cc1ea1461007e578063a5373899146100af578063d09de08a146100e0575b600080fd5b34801561007357600080fd5b5061007c6100f7565b005b34801561008a57600080fd5b50610093610126565b604051808260ff1660ff16815260200191505060405180910390f35b3480156100bb57600080fd5b506100c4610138565b604051808260ff1660ff16815260200191505060405180910390f35b3480156100ec57600080fd5b506100f561014e565b005b60016000808282829054906101000a900460ff160392506101000a81548160ff021916908360ff160217905550565b6000809054906101000a900460ff1681565b60008060009054906101000a900460ff16905090565b60016000808282829054906101000a900460ff160192506101000a81548160ff021916908360ff1602179055505600a165627a7a723058206fbff4a4d384f7106083c2c2056e3f3fa335ce14ef1b1d5f3f002a9e7d715f910029";

    public static final String FUNC_DECREMENT = "decrement";

    public static final String FUNC_COUNTS = "counts";

    public static final String FUNC_GETCOUNTS = "getCounts";

    public static final String FUNC_INCREMENT = "increment";

    protected Counter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
            BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Counter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice,
            BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> decrement() {
        final Function function = new Function(FUNC_DECREMENT, Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> counts() {
        final Function function = new Function(FUNC_COUNTS, Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getCounts() {
        final Function function = new Function(FUNC_GETCOUNTS, Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> increment() {
        final Function function = new Function(FUNC_INCREMENT, Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Counter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
            BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(Counter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<Counter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice,
            BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(Counter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "",
                initialWeiValue);
    }

    public static Counter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
            BigInteger gasLimit) {
        return new Counter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Counter load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Counter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
