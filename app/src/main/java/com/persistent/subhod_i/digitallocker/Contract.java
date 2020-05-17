package com.persistent.subhod_i.digitallocker;

import org.web3j.protocol.Web3j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

import org.web3j.tx.gas.ContractGasProvider;

/**
 * Created by subhod_i on 22-03-2018.
 */

public class Contract {
    private Web3j web3j;
    private Credentials credentials;
    ContractGasProvider gasProvider = new ContractGasProvider() {
        @Override
        public BigInteger getGasPrice(String contractFunc) {
            return BigInteger.TEN;
        }

        @Override
        public BigInteger getGasPrice() {
            return BigInteger.TEN;
        }

        @Override
        public BigInteger getGasLimit(String contractFunc) {
            return BigInteger.valueOf(470000);
        }

        @Override
        public BigInteger getGasLimit() {
            return BigInteger.valueOf(470000);
        }
    };


    public Contract(Web3j web3j, Credentials credentials,ContractGasProvider gasProvider) {
        this.web3j = web3j;
        this.credentials = credentials;
        this.gasProvider=gasProvider;
    }

    public String deploy() throws Exception {
//        Simple_sol_simple contract = Simple_sol_simple.deploy(
//                web3j, credentials,
//                BigInteger.valueOf(0),
//                BigInteger.valueOf(4700000)).send();
//        return contract.getContractAddress();

       ////////my work
//        Election contract = Election.deploy(web3j, credentials, BigInteger.valueOf(0), BigInteger.valueOf(4700000)).send();
//        return  contract.getContractAddress();
        //////my new work
        String address = credentials.getAddress();
        Election contract = Election.load(address,web3j,credentials,gasProvider);
        RemoteCall<TransactionReceipt> transactionReceipt = contract.vote(BigInteger.valueOf(1), BigInteger.valueOf(47),BigInteger.valueOf(320000));
        TransactionReceipt receipt =transactionReceipt.send();
        return receipt.toString();
}

    public String open( String contractAddress,int driverID,int latitude,int longitude) throws Exception {
        CabService contract = new CabService(contractAddress,web3j,credentials,gasProvider);


   // Election contract = Election.load(contractAddress,web3j,credentials,gasProvider);
    RemoteCall<TransactionReceipt> transactionReceipt = contract.sendLocation(BigInteger.valueOf(driverID), BigInteger.valueOf(latitude),BigInteger.valueOf(longitude));
    TransactionReceipt receipt =transactionReceipt.send();
    return receipt.toString();
    }
//L
//    public byte[] query(String key, String contractAddress) throws Exception {
//        Simple_sol_simple contract = Simple_sol_simple.load(
//                contractAddress, web3j, credentials, BigInteger.valueOf(0), BigInteger.valueOf(100000));
//        return contract.query(key).send();
//    }
}
