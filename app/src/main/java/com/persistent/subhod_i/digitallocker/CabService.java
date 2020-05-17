package com.persistent.subhod_i.digitallocker;

import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

package com.persistent.subhod_i.digitallocker;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
//import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
//import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class CabService extends Contract {

    // private static final String BINARY = "0x608060405234801561001057600080fd5b506040805180820190915260078082527f417765736f6d6500000000000000000000000000000000000000000000000000602090920191825261005591600091610270565b5060408051808201909152600881527f4472697665722031000000000000000000000000000000000000000000000000602082015261009c906001600160e01b036101ff16565b60408051808201909152600881527f447269766572203200000000000000000000000000000000000000000000000060208201526100e2906001600160e01b036101ff16565b60408051808201909152600881527f44726976657220330000000000000000000000000000000000000000000000006020820152610128906001600160e01b036101ff16565b60408051808201909152600881527f4472697665722034000000000000000000000000000000000000000000000000602082015261016e906001600160e01b036101ff16565b60408051808201909152600881527f447269766572203500000000000000000000000000000000000000000000000060208201526101b4906001600160e01b036101ff16565b60408051808201909152600881527f447269766572203600000000000000000000000000000000000000000000000060208201526101fa906001600160e01b036101ff16565b61030b565b60028054600190810191829055604080516080810182528381526020808201868152600083850181905260608401819052958652848252929094208151815591518051919492936102569390850192910190610270565b506040820151600282015560609091015160039091015550565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102b157805160ff19168380011785556102de565b828001600101855582156102de579182015b828111156102de5782518255916020019190600101906102c3565b506102ea9291506102ee565b5090565b61030891905b808211156102ea57600081556001016102f4565b90565b6103a88061031a6000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80638a6655d614610051578063bd8d1e821461007c578063e5ee4eb514610096578063f79e545014610113575b600080fd5b61007a6004803603606081101561006757600080fd5b50803590602081013590604001356101bd565b005b61008461022d565b60408051918252519081900360200190f35b61009e610233565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100d85781810151838201526020016100c0565b50505050905090810190601f1680156101055780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101306004803603602081101561012957600080fd5b50356102c1565b6040518085815260200180602001848152602001838152602001828103825285818151815260200191508051906020019080838360005b8381101561017f578181015183820152602001610167565b50505050905090810190601f1680156101ac5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b6000831180156101cf57506002548311155b6101d857600080fd5b60008381526001602090815260409182902060028101859055600301839055815185815291517ffff3c900d938d21d0990d786e819f29b8d05c1ef587b462b939609625b684b169281900390910190a1505050565b60025481565b6000805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156102b95780601f1061028e576101008083540402835291602001916102b9565b820191906000526020600020905b81548152906001019060200180831161029c57829003601f168201915b505050505081565b600160208181526000928352604092839020805481840180548651600296821615610100026000190190911695909504601f8101859004850286018501909652858552909491939290919083018282801561035d5780601f106103325761010080835404028352916020019161035d565b820191906000526020600020905b81548152906001019060200180831161034057829003601f168201915b505050505090806002015490806003015490508456fea265627a7a72305820c06c99f80d9711d6fc1048cdc76208159b41b8c0c55c2affdbdd60947d27fc6f64736f6c634300050a0032";

    private static  final  String BINARY ="0x608060405234801561001057600080fd5b506040518060400160405280600781526020017f417765736f6d65000000000000000000000000000000000000000000000000008152506000908051906020019061005c929190610287565b506100a16040518060400160405280600881526020017f44726976657220310000000000000000000000000000000000000000000000008152506101fa60201b60201c565b6100e56040518060400160405280600881526020017f44726976657220320000000000000000000000000000000000000000000000008152506101fa60201b60201c565b6101296040518060400160405280600881526020017f44726976657220330000000000000000000000000000000000000000000000008152506101fa60201b60201c565b61016d6040518060400160405280600881526020017f44726976657220340000000000000000000000000000000000000000000000008152506101fa60201b60201c565b6101b16040518060400160405280600881526020017f44726976657220350000000000000000000000000000000000000000000000008152506101fa60201b60201c565b6101f56040518060400160405280600881526020017f44726976657220360000000000000000000000000000000000000000000000008152506101fa60201b60201c565b6103ac565b6002600081548092919060010191905055506040518060800160405280600254815260200182815260200160008152602001600081525060016000600254815260200190815260200160002060008201518160000155602082015181600101908051906020019061026c929190610307565b50604082015181600201556060820151816003015590505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102c857805160ff19168380011785556102f6565b828001600101855582156102f6579182015b828111156102f55782518255916020019190600101906102da565b5b5090506103039190610387565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061034857805160ff1916838001178555610376565b82800160010185558215610376579182015b8281111561037557825182559160200191906001019061035a565b5b5090506103839190610387565b5090565b6103a991905b808211156103a557600081600090555060010161038d565b5090565b90565b61040b806103bb6000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80638a6655d614610051578063bd8d1e8214610093578063e5ee4eb5146100b1578063f79e545014610134575b600080fd5b6100916004803603606081101561006757600080fd5b810190808035906020019092919080359060200190929190803590602001909291905050506101f0565b005b61009b610273565b6040518082815260200191505060405180910390f35b6100b9610279565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100f95780820151818401526020810190506100de565b50505050905090810190601f1680156101265780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101606004803603602081101561014a57600080fd5b8101908080359060200190929190505050610317565b6040518085815260200180602001848152602001838152602001828103825285818151815260200191508051906020019080838360005b838110156101b2578082015181840152602081019050610197565b50505050905090810190601f1680156101df5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b60008311801561020257506002548311155b61020b57600080fd5b816001600085815260200190815260200160002060020181905550806001600085815260200190815260200160002060030181905550827ffff3c900d938d21d0990d786e819f29b8d05c1ef587b462b939609625b684b1660405160405180910390a2505050565b60025481565b60008054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561030f5780601f106102e45761010080835404028352916020019161030f565b820191906000526020600020905b8154815290600101906020018083116102f257829003601f168201915b505050505081565b6001602052806000526040600020600091509050806000015490806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103c95780601f1061039e576101008083540402835291602001916103c9565b820191906000526020600020905b8154815290600101906020018083116103ac57829003601f168201915b505050505090806002015490806003015490508456fea165627a7a7230582050bc9a18162998f6f81250c8ea834ac955f96276be11692f22ba9bd10faac2550029";
    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_DRIVERSCOUNT = "driversCount";

    public static final String FUNC_DRIVER1 = "Driver1";

    public static final String FUNC_DRIVERS = "drivers";

    public static final Event VOTEDEVENT_EVENT = new Event("votedEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected CabService(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CabService(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CabService(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CabService(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> sendLocation(BigInteger _DriverId, BigInteger lat, BigInteger longi) {
        final Function function = new Function(
                FUNC_VOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_DriverId),
                        new org.web3j.abi.datatypes.generated.Uint256(lat),
                        new org.web3j.abi.datatypes.generated.Uint256(longi)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> driversCount() {
        final Function function = new Function(FUNC_DRIVERSCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> Driver1() {
        final Function function = new Function(FUNC_DRIVER1,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple4<BigInteger, String, BigInteger, BigInteger>> drivers(BigInteger param0) {
        final Function function = new Function(FUNC_DRIVERS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<BigInteger, String, BigInteger, BigInteger>>(new Callable<Tuple4<BigInteger, String, BigInteger, BigInteger>>() {
            @Override
            public Tuple4<BigInteger, String, BigInteger, BigInteger> call() throws Exception {
                List<Type> results = executeCallMultipleValueReturn(function);
                return new Tuple4<BigInteger, String, BigInteger, BigInteger>(
                        (BigInteger) results.get(0).getValue(),
                        (String) results.get(1).getValue(),
                        (BigInteger) results.get(2).getValue(),
                        (BigInteger) results.get(3).getValue());
            }
        });
    }

    public List<VotedEventEventResponse> getVotedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTEDEVENT_EVENT, transactionReceipt);
        ArrayList<VotedEventEventResponse> responses = new ArrayList<VotedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotedEventEventResponse typedResponse = new VotedEventEventResponse();
            //typedResponse.log = eventValues.getLog();
            typedResponse.indexed_DriverId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, VotedEventEventResponse>() {
            @Override
            public VotedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTEDEVENT_EVENT, log);
                VotedEventEventResponse typedResponse = new VotedEventEventResponse();
                //typedResponse.log = log;
                typedResponse.indexed_DriverId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTEDEVENT_EVENT));
        return votedEventEventFlowable(filter);
    }

    @Deprecated
    public static CabService load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CabService(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CabService load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CabService(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CabService load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CabService(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CabService load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CabService(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CabService> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CabService.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    private static RemoteCall<CabService> deployRemoteCall(Class<CabService> cabServiceClass, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String binary, String s) {
    }


    public static RemoteCall<CabService> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CabService.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CabService> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CabService.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CabService> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CabService.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class VotedEventEventResponse extends BaseEventResponse {
        public BigInteger indexed_DriverId;
    }
}
