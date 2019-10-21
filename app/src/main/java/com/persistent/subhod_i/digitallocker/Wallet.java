package com.persistent.subhod_i.digitallocker;

import android.os.Environment;
import android.util.Log;

import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
//import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * Created by subhod_i on 16-03-2018.
 */

public class Wallet {
    Web3j web3j ;
    public String createWallet() throws Exception {
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getPath();
        String fileName = WalletUtils.generateLightNewWalletFile("password", new File(path));
        return path + fileName;
    }

//    public Credentials loadCredentials(String password) throws Exception {
//        Credentials credentials = WalletUtils.loadCredentials(
//                password,
//                "/storage/emulated/0/Download/UTC--2019-07-16T17-40-00.131--b54a7bbcf318bd9c8d0fea785ef42bd7caa8047a.json");
//        Log.i("Loading credentials", "Credentials loaded");
//        return credentials;
//    }

    public Web3j constructWeb3(String URL) throws IOException {
        Web3j web3 = Web3j.build(new HttpService(URL));  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion;
        web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        Log.i("Web3 verison", clientVersion);
        return web3;
    }

    public String sendTransaction(Web3j web3, Credentials credentials) throws Exception {
        TransactionReceipt transferReceipt = Transfer.sendFunds(web3, credentials,
                "0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f",  // you can put any address here
                BigDecimal.ONE, Convert.Unit.FINNEY)  // 1 wei = 10^-18 Ether
                .send();

        return transferReceipt.getTransactionHash();
//        Transaction transaction = (Transaction) Transaction.createFunctionCallTransaction("0x0bdf6a6d62d0340715b914802b7a38afa1501622 ",  BigInteger.valueOf(4), BigInteger.valueOf(20), BigInteger.valueOf(21000), "0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f", "Halwa");
//
//        EthSendTransaction transactionResponse = web3.ethSendTransaction(transaction).sendAsync().get();
//        String transactionHash = transactionResponse.getTransactionHash();
//        return transactionHash;

    }
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value)
            throws IOException {

        Transaction transaction = new Transaction("0x0bdf6a6d62d0340715b914802b7a38afa1501622 "
                , null, gasPrice, gasLimit, to, value, data);

        return web3j.ethSendTransaction(transaction)
                .send();

    }
    public String createBipWallet() throws Exception {
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getPath();
        Bip39Wallet bip39Wallet = WalletUtils.generateBip39Wallet("password", new File(path));
        String filename = bip39Wallet.getFilename();
        String mnemonic = bip39Wallet.getMnemonic();
        return "Success";
    }

    public void checkWalletExist() {

    }
}
