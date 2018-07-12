package com.launch.storage;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
public class TokenRewards extends Contract {
    private static final String BINARY = "608060405260038054600160a060020a0319163017905534801561002257600080fd5b5060008054600160a060020a03191633179055600180805560025561035c8061004c6000396000f3006080604052600436106100a35763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663091c76f581146100a557806327ea6f2b146100ba57806347fc4306146100d25780638b7afe2e146100e75780638da5cb5b1461010e5780639ec5a8941461013f578063a4d66daf14610154578063c7a29c6f14610169578063f2fde38b14610181578063f6b4dfb4146101a2575b005b3480156100b157600080fd5b506100a36101b7565b3480156100c657600080fd5b506100a360043561020b565b3480156100de57600080fd5b506100a3610227565b3480156100f357600080fd5b506100fc61027f565b60408051918252519081900360200190f35b34801561011a57600080fd5b5061012361028f565b60408051600160a060020a039092168252519081900360200190f35b34801561014b57600080fd5b506100fc61029e565b34801561016057600080fd5b506100fc6102a4565b34801561017557600080fd5b506100a36004356102aa565b34801561018d57600080fd5b506100a3600160a060020a03600435166102c6565b3480156101ae57600080fd5b50610123610321565b600154670de0b6b3a7640000023331106101d057600080fd5b6002546040513391670de0b6b3a76400000280156108fc02916000818181858888f19350505050158015610208573d6000803e3d6000fd5b50565b600054600160a060020a0316331461022257600080fd5b600155565b600054600160a060020a0316331461023e57600080fd5b60008054600354604051600160a060020a0392831693919092163180156108fc02929091818181858888f19350505050158015610208573d6000803e3d6000fd5b600354600160a060020a03163190565b600054600160a060020a031681565b60025481565b60015481565b600054600160a060020a031633146102c157600080fd5b600255565b600054600160a060020a031633146102dd57600080fd5b600160a060020a03811615156102f257600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600354600160a060020a0316815600a165627a7a72305820b318524066964e581faabe98c11f40db837ea9711aa2d7a26f547570089ffbf50029";

    protected TokenRewards(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenRewards(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> tokenRewards() {
        final Function function = new Function(
                "tokenRewards", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setLimit(BigInteger _limit) {
        final Function function = new Function(
                "setLimit", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> contractWithdraw() {
        final Function function = new Function(
                "contractWithdraw", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> contractBalance() {
        final Function function = new Function("contractBalance", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> rewards() {
        final Function function = new Function("rewards", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> limit() {
        final Function function = new Function("limit", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setRewards(BigInteger _rewards) {
        final Function function = new Function(
                "setRewards", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rewards)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final Function function = new Function(
                "transferOwnership", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> contractAddress() {
        final Function function = new Function("contractAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<TokenRewards> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenRewards.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TokenRewards> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenRewards.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static TokenRewards load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenRewards(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static TokenRewards load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenRewards(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
