package com.persistent.subhod_i.digitallocker;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.github.clans.fab.FloatingActionButton;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
//import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.Console;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * Created by subhod_i on 23-03-2018.
 */

public class HomeActivity extends Activity {
    String ethereum, password;
    Web3j web3j;
    Credentials credentials,credentials2;
    TextView ethereumId;
    TextView accountBalance;
    TextView response;
    String strTokenAmount;
    FloatingActionButton quorum, ropsten, mainnet, quorumDeploy, quorumTransaction, quorumQuery;
    BigInteger balance;
    String contractAddress = "";
    String hashrec="";
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
            return BigInteger.valueOf(47000);
        }

        @Override
        public BigInteger getGasLimit() {
            return BigInteger.valueOf(47000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ethereumId = (TextView) findViewById(R.id.ethereumId);
        accountBalance = (TextView) findViewById(R.id.accountBalance);
        response = (TextView) findViewById(R.id.response);
        quorum = (FloatingActionButton) findViewById(R.id.quorum);
        ropsten = (FloatingActionButton) findViewById(R.id.ropsten);
        mainnet = (FloatingActionButton) findViewById(R.id.mainnet);
        quorumDeploy = (FloatingActionButton) findViewById(R.id.quorumDeploy);
        quorumTransaction = (FloatingActionButton) findViewById(R.id.quorumTransaction);
        quorumQuery = (FloatingActionButton) findViewById(R.id.quorumQuery);

        try {
            addEventListeners();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addEventListeners() {
        quorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new LongOperation().execute("quorumTransaction");
                } catch (Exception e) {

                }
            }
        });

        ropsten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new LongOperation().execute("ropstenTransaction");
                } catch (Exception e) {

                }
            }
        });

        mainnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new LongOperation().execute("mainnetTransaction");
                } catch (Exception e) {

                }
            }
        });

        quorumDeploy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new LongOperation().execute("quorumContractDeploy");
                } catch (Exception e) {

                }
            }
        });

        quorumTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new LongOperation().execute("quorumContractTransaction");
                } catch (Exception e) {

                }
            }
        });

        quorumQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new LongOperation().execute("quorumContractQuery");
                } catch (Exception e) {

                }
            }
        });
    }

    private void loadData() throws Exception {
        ethereum = getIntent().getExtras().getString("ethereumId");
        password = getIntent().getExtras().getString("password");
        String strAddress="0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f";
        // ethereumId.setText(ethereum);
        ethereumId.setText(strAddress);
        new LongOperation().execute("loadBalance");
    }

    public void loadBalance() throws Exception {
        Web3j web3 = Web3j.build(new HttpService("http://3.17.172.8:8545"));
        //String strAddress="0x0bdf6a6d62d0340715b914802b7a38afa1501622";
         String strAddress ="0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f";

        EthGetBalance ethGetBalance = web3.ethGetBalance(strAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
        balance = ethGetBalance.getBalance();
        java.math.BigDecimal tokenValue = Convert.fromWei(String.valueOf(balance), Convert.Unit.ETHER);
        strTokenAmount = String.valueOf(tokenValue);

    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Wallet wallet = new Wallet();
            String hash="";
            try {
                switch (params[0]) {
                    case "mainnetTransaction":
                        web3j = wallet.constructWeb3("http://3.17.172.8:8545");
                        //credentials = wallet.loadCredentials(password);
                        //0xcaa04847adbfaf2110494f0cd1160dc8e6036303d163636cf323df853efe3300
                        //credentials = Credentials.create("0x2aae8e26f15a28d47b74e830187310261b7c4e2ebb295d21c55541a0807e9b7e");



                        //keshav wala address

                       credentials2 = Credentials.create("0x0cd0014ce3d428d895b562899d923c21767b4cc518a7ec1485a90ad01f1e2358");


                        Contract contract = new Contract(web3j, credentials2,gasProvider);
                       // Election contract2 = Election.load(credentials2.getAddress(),web3j,credentials2,BigInteger.valueOf(0), BigInteger.valueOf(100000));
                       // Election.deploy(web3j,credentials,BigInteger.valueOf(0), BigInteger.valueOf(100000));
                        // Election contract2 = Election.load(credentials.getAddress(),web3j,credentials2,BigInteger.valueOf(0), BigInteger.valueOf(100000));
                       // contract.deploy();
                       // contract.open("0x0bdf6a6d62d0340715b914802b7a38afa1501622");
                        hashrec=contract.open(credentials2.getAddress());
                       // contract.open(credentials2.getAddress());
                       //contract.open(credentials2.getAddress());
//                        contract.open("0xD86A2D5f86872766DDFFd727747BaF47E9Ab0C29");
//                        contract.open("0xe1205e915445077556FD6dFB315407a50576100D");
                        //contract2.vote(BigInteger.valueOf(1),BigInteger.valueOf(123),BigInteger.valueOf(100));

//                        loadBalance();


                                loadBalance();


                        //return "Contract deployed successfully:Mainnet " +hashrec ;


                      //String result = contract2.vote(BigInteger.valueOf(1),BigInteger.valueOf(123),BigInteger.valueOf(100)).send().toString();
                        //Log.v("halwa",result);
                        Log.v("halwa",hashrec);
                       return  hashrec;
                      // wallet.sendTransaction(web3j, credentials);

//                        Transaction transaction = new Transaction(
//                                "0x0bdf6a6d62d0340715b914802b7a38afa1501622", null, BigInteger.TEN, BigInteger.TEN, "0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f", BigInteger.TEN, "Halwa");
//
                        //wallet.sendTransaction(BigInteger.ONE,BigInteger.TEN,"0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f","halwa",BigInteger.TEN);

//                        return "mainnetTransaction";


                    default:
                        web3j = wallet.constructWeb3("http://3.17.172.8:8545");
                        loadBalance();
                        return "loadbalance";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                // Ether send trasnaction, update balance
                if (result == "ropstenTransaction" || result == "mainnetTransaction" || result == "quorumTransaction" || result == "loadbalance")
                    accountBalance.setText(strTokenAmount);
                else {
                    // Contract trasnaction, update response view
                    response.setText(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    public String asciiToHex(String asciiValue) {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString() + "".join("", Collections.nCopies(32 - (hex.length() / 2), "00"));
    }
}
