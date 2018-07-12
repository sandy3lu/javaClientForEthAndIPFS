package com.launch.storage;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
public class StorageCoin extends Contract {
    private static final String BINARY = "608060405260008054600160a060020a03191633179055670de0b6b3a764000060018190556002556706f05b59d3b2000060035561144f806100426000396000f3006080604052600436106100ae5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416631c68994881146100b057806338d85335146100c5578063616269d8146100dd578063727ba276146101485780638b121265146101a1578063a9910054146102bd578063aff5948314610316578063bdbb119b14610364578063c9980c72146103bd578063d11a42ec14610416578063dfec92061461042e575b005b3480156100bc57600080fd5b506100ae610446565b3480156100d157600080fd5b506100ae6004356104a1565b3480156100e957600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101369436949293602493928401919081908401838280828437509497506104bd9650505050505050565b60408051918252519081900360200190f35b34801561015457600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100ae94369492936024939284019190819084018382808284375094975061052a9650505050505050565b3480156101ad57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261013694369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f818a01358b0180359182018390048302840183018552818452989b60ff8b35169b909a90999401975091955091820193509150819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506106ea9650505050505050565b3480156102c957600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100ae94369492936024939284019190819084018382808284375094975061075b9650505050505050565b6040805160206004803580820135601f810184900484028501840190955284845261013694369492936024939284019190819084018382808284375094975050933594506107ea9350505050565b34801561037057600080fd5b506040805160206004803580820135601f8101849004840285018401909552848452610136943694929360249392840191908190840183828082843750949750610afc9650505050505050565b6040805160206004803580820135601f81018490048402850184019095528484526100ae94369492936024939284019190819084018382808284375094975050843595505050602083013592604001359150610b869050565b34801561042257600080fd5b506100ae600435610f4a565b34801561043a57600080fd5b506100ae600435610f66565b60008054600160a060020a0316331461045e57600080fd5b5060008054604051600160a060020a03909116918291303180156108fc0292909190818181858888f1935050505015801561049d573d6000803e3d6000fd5b5050565b600054600160a060020a031633146104b857600080fd5b600155565b60006004826040518082805190602001908083835b602083106104f15780518252601f1990920191602091820191016104d2565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922060020154925050505b919050565b60006004826040518082805190602001908083835b6020831061055e5780518252601f19909201916020918201910161053f565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054600160a060020a0316925050508015156105a357600080fd5b600160a060020a03811633146105b857600080fd5b60006004836040518082805190602001908083835b602083106105ec5780518252601f1990920191602091820191016105cd565b51815160209384036101000a6000190180199092169116179052920194855250604080519485900382018520805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0397909716969096179095553384820181905285855287519585019590955286517f4d2e2dd93986088dba2b7f68a9a8bede1b3210e7fb42cc0307dcd1cf94aa4b8b9588959094509250829160608301919086019080838360005b838110156106ab578181015183820152602001610693565b50505050905090810190601f1680156106d85780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15050565b60008060006106fc8888888888610f82565b915081151561070a57600080fd5b5060025430908131101561071d57600080fd5b600254604051339180156108fc02916000818181858888f1935050505015801561074b573d6000803e3d6000fd5b5050600254979650505050505050565b60008054600160a060020a0316331461077357600080fd5b6004826040518082805190602001908083835b602083106107a55780518252601f199092019160209182019101610786565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054600160a060020a0316925050508015156105b857600080fd5b6000806000806000600160a060020a03166004876040518082805190602001908083835b6020831061082d5780518252601f19909201916020918201910161080e565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054600160a060020a031692909214159150610874905057600080fd5b600185101561088257600080fd5b6004866040518082805190602001908083835b602083106108b45780518252601f199092019160209182019101610895565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922060019081015490549095508502870293505050348211156108ff57600080fd5b30905084601e0262015180026004876040518082805190602001908083835b6020831061093d5780518252601f19909201916020918201910161091e565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420600201548b519501946004948c9450925082918401908083835b602083106109a15780518252601f199092019160209182019101610982565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030183206002019390935550600160a060020a03831691506108fc8415029084906000818181858888f19350505050158015610a07573d6000803e3d6000fd5b5060405133903484900380156108fc02916000818181858888f19350505050158015610a37573d6000803e3d6000fd5b507fb59378508f4844bd660ffbfb717b2e33f19348355371c659d9ec40bf72f07aa7863387604051808060200184600160a060020a0316600160a060020a03168152602001838152602001828103825285818151815260200191508051906020019080838360005b83811015610ab7578181015183820152602001610a9f565b50505050905090810190601f168015610ae45780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a150949350505050565b6000806004836040518082805190602001908083835b60208310610b315780518252601f199092019160209182019101610b12565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054600160a060020a03169250505033811415610b7b5760019150610b80565b600291505b50919050565b6000806001851015610b9757600080fd5b600b8510610ba457600080fd5b600d8310610bb157600080fd5b82610bbb8561104b565b60015487020202915034821115610bd157600080fd5b6004866040518082805190602001908083835b60208310610c035780518252601f199092019160209182019101610be4565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054600160a060020a0316159150610c46905057600080fd5b309050336004876040518082805190602001908083835b60208310610c7c5780518252601f199092019160209182019101610c5d565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0396909616959095179094555050875187926004928a9290918291908401908083835b60208310610d095780518252601f199092019160209182019101610cea565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420600101949094555050875162278d0086024201926004928a9290918291908401908083835b60208310610d785780518252601f199092019160209182019101610d59565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420600201949094555050875186926004928a9290918291908401908083835b60208310610de05780518252601f199092019160209182019101610dc1565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030183206003019390935550600160a060020a03831691506108fc8415029084906000818181858888f19350505050158015610e46573d6000803e3d6000fd5b5060405133903484900380156108fc02916000818181858888f19350505050158015610e76573d6000803e3d6000fd5b507f0213ba1ae1c076de5649654b858f30c5dab64c60ada8a3157c8fab57f9b9023f8633868887604051808060200186600160a060020a0316600160a060020a03168152602001858152602001848152602001838152602001828103825287818151815260200191508051906020019080838360005b83811015610f04578181015183820152602001610eec565b50505050905090810190601f168015610f315780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1505050505050565b600054600160a060020a03163314610f6157600080fd5b600355565b600054600160a060020a03163314610f7d57600080fd5b600255565b60008080606081610f958989898961109c565b9350610fa18a8a611145565b92508215610ff557610fb28a6111d5565b9150600090505b8151811015610ff557815161010090950294829082908110610fd757fe5b016020015160f860020a908190048102049490940193600101610fb9565b604080518681528415156020820152600160a060020a0386168183015290517ff2ae03af717c75199032f47c6729b4eea727d5f2e1d6e5af8ac0471ba7e80bc19181900360600190a15050505095945050505050565b600061080082111561105f57506014610525565b6104008211156110715750600a610525565b6101f482111561108357506005610525565b60c882111561109457506002610525565b506001919050565b60008060008060006110b56110b08a6111d5565b61140a565b93506110c36110b0886111d5565b92506110d16110b0876111d5565b604080516000808252602080830180855289905260ff8d16838501526060830188905260808301859052925193955060019360a08084019493601f19830193908390039091019190865af115801561112d573d6000803e3d6000fd5b5050604051601f1901519a9950505050505050505050565b6000806000846040518082805190602001908083835b6020831061117a5780518252601f19909201916020918201910161115b565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902091506111b56110b0856111d5565b9050818114156111c857600192506111cd565b600092505b505092915050565b606080606060008060606000808896508651604051908082528060200260200182016040528015611210578160200160208202803883390190505b509550600094505b86518510156112b357868581518110151561122f57fe5b016020015160f860020a908190048102049350606084111561126e57855160561985019087908790811061125f57fe5b602090810290910101526112a8565b604284111561128b57855160361985019087908790811061125f57fe5b60308403868681518110151561129d57fe5b602090810290910101525b600190940193611218565b8551600290600101046040519080825280601f01601f1916602001820160405280156112e9578160200160208202803883390190505b509250600091508551600116600114156113605785600081518110151561130c57fe5b9060200190602002015160f860020a0283600081518110151561132b57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600191505b50805b85518110156113fd57858160010181518110151561137d57fe5b90602001906020020151868281518110151561139557fe5b906020019060200201516010020160f860020a0283836002848115156113b757fe5b04018151811015156113c557fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600201611363565b5090979650505050505050565b805160009060201461141b57600080fd5b5060200151905600a165627a7a723058209282d7ea1c2e3496e4bcc74bd86784ddb029f98bae1b2481c2d6ee2af8e2f1b50029";

    protected StorageCoin(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StorageCoin(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<UploadInfoEventResponse> getUploadInfoEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("uploadInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<UploadInfoEventResponse> responses = new ArrayList<UploadInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UploadInfoEventResponse typedResponse = new UploadInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileName = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.fileSizeInM = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.copyNum = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.months = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UploadInfoEventResponse> uploadInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("uploadInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, UploadInfoEventResponse>() {
            @Override
            public UploadInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                UploadInfoEventResponse typedResponse = new UploadInfoEventResponse();
                typedResponse.log = log;
                typedResponse.fileName = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.owner = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.fileSizeInM = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.copyNum = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.months = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public List<ReNewFileInfoEventResponse> getReNewFileInfoEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("reNewFileInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<ReNewFileInfoEventResponse> responses = new ArrayList<ReNewFileInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReNewFileInfoEventResponse typedResponse = new ReNewFileInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileName = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.months = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReNewFileInfoEventResponse> reNewFileInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("reNewFileInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReNewFileInfoEventResponse>() {
            @Override
            public ReNewFileInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                ReNewFileInfoEventResponse typedResponse = new ReNewFileInfoEventResponse();
                typedResponse.log = log;
                typedResponse.fileName = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.owner = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.months = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public List<DownloadInfoEventResponse> getDownloadInfoEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("downloadInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<DownloadInfoEventResponse> responses = new ArrayList<DownloadInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DownloadInfoEventResponse typedResponse = new DownloadInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileName = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<DownloadInfoEventResponse> downloadInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("downloadInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, DownloadInfoEventResponse>() {
            @Override
            public DownloadInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                DownloadInfoEventResponse typedResponse = new DownloadInfoEventResponse();
                typedResponse.log = log;
                typedResponse.fileName = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<DeleteInfoEventResponse> getDeleteInfoEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("deleteInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<DeleteInfoEventResponse> responses = new ArrayList<DeleteInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeleteInfoEventResponse typedResponse = new DeleteInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fileName = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<DeleteInfoEventResponse> deleteInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("deleteInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, DeleteInfoEventResponse>() {
            @Override
            public DeleteInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                DeleteInfoEventResponse typedResponse = new DeleteInfoEventResponse();
                typedResponse.log = log;
                typedResponse.fileName = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<ProveInfoEventResponse> getProveInfoEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("proveInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<ProveInfoEventResponse> responses = new ArrayList<ProveInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProveInfoEventResponse typedResponse = new ProveInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.countNum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.hashresult = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.addressCal = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ProveInfoEventResponse> proveInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("proveInfo", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ProveInfoEventResponse>() {
            @Override
            public ProveInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                ProveInfoEventResponse typedResponse = new ProveInfoEventResponse();
                typedResponse.log = log;
                typedResponse.countNum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.hashresult = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.addressCal = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> getFundBack() {
        final Function function = new Function(
                "getFundBack", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setUploadReward(BigInteger _reward) {
        final Function function = new Function(
                "setUploadReward", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reward)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getDate(String fileHash) {
        final Function function = new Function("getDate", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(fileHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> deleteFileByOwner(String fileName) {
        final Function function = new Function(
                "deleteFileByOwner", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(fileName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> verifyStorageContinue(String _count, String hash, BigInteger v, String r, String s) {
        final Function function = new Function(
                "verifyStorageContinue", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_count), 
                new org.web3j.abi.datatypes.Utf8String(hash), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.Utf8String(r), 
                new org.web3j.abi.datatypes.Utf8String(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> deleteFile(String fileName) {
        final Function function = new Function(
                "deleteFile", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(fileName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> reNewFileDate(String fileName, BigInteger months, BigInteger weiValue) {
        final Function function = new Function(
                "reNewFileDate", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(fileName), 
                new org.web3j.abi.datatypes.generated.Uint256(months)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> checkOwner(String fileName) {
        final Function function = new Function("checkOwner", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(fileName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> uploadFile(String fileName, BigInteger copyNum, BigInteger fileSizeInM, BigInteger months, BigInteger weiValue) {
        final Function function = new Function(
                "uploadFile", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(fileName), 
                new org.web3j.abi.datatypes.generated.Uint256(copyNum), 
                new org.web3j.abi.datatypes.generated.Uint256(fileSizeInM), 
                new org.web3j.abi.datatypes.generated.Uint256(months)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> setSharedReward(BigInteger _reward) {
        final Function function = new Function(
                "setSharedReward", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reward)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setSaveFileReward(BigInteger _reward) {
        final Function function = new Function(
                "setSaveFileReward", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reward)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<StorageCoin> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(StorageCoin.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<StorageCoin> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(StorageCoin.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static StorageCoin load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StorageCoin(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static StorageCoin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StorageCoin(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class UploadInfoEventResponse {
        public Log log;

        public String fileName;

        public String owner;

        public BigInteger fileSizeInM;

        public BigInteger copyNum;

        public BigInteger months;
    }

    public static class ReNewFileInfoEventResponse {
        public Log log;

        public String fileName;

        public String owner;

        public BigInteger months;
    }

    public static class DownloadInfoEventResponse {
        public Log log;

        public String fileName;

        public String buyer;
    }

    public static class DeleteInfoEventResponse {
        public Log log;

        public String fileName;

        public String operator;
    }

    public static class ProveInfoEventResponse {
        public Log log;

        public BigInteger countNum;

        public Boolean hashresult;

        public String addressCal;
    }
}
