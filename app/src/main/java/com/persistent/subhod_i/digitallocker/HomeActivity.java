package com.persistent.subhod_i.digitallocker;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.github.clans.fab.FloatingActionButton;

import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
//import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.Collections;

/**
 * Created by subhod_i on 23-03-2018.
 */

public class HomeActivity extends Activity {
    String ethereum, password;
    Web3j web3j;
    Credentials credentials, credentials2;
    TextView ethereumId;
    TextView accountBalance;
    EditText latin, lonin;
    String strTokenAmount;
    FloatingActionButton quorum, ropsten, mainnet, quorumDeploy, quorumTransaction, quorumQuery;
    BigInteger balance;
    String contractAddress = "";
    String hashrec = "", latinstr, loninstr;
    int driverID = 0;
    ContractGasProvider gasProvider = new ContractGasProvider() {
        @Override
        public BigInteger getGasPrice(String contractFunc) {
            return BigInteger.valueOf(100);
        }

        @Override
        public BigInteger getGasPrice() {
            return BigInteger.valueOf(100);
        }

        @Override
        public BigInteger getGasLimit(String contractFunc) {
            return BigInteger.valueOf(100000);
        }

        @Override
        public BigInteger getGasLimit() {
            return BigInteger.valueOf(100000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ethereumId = (TextView) findViewById(R.id.username);
        accountBalance = (TextView) findViewById(R.id.accountBalance);

        latin = findViewById(R.id.latin);
        lonin = findViewById(R.id.lonin);


        try {

            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendInputLocation(View v) {
        latinstr = latin.getText().toString();
        loninstr = lonin.getText().toString();

        try {
            new LongOperation().execute("mainnetTransaction");
            Toast.makeText(getApplicationContext(),"Location SENT"+"Latitude:" +latinstr+"Longitude:" + loninstr,Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws Exception {
        ethereum = getIntent().getExtras().getString("username");
        password = getIntent().getExtras().getString("password");
        driverID = getIntent().getExtras().getInt("driverid");
        String strAddress = ethereum;
        // ethereumId.setText(ethereum);

        ethereumId.setText(strAddress);

        latin.setText(0);
        lonin.setText(0);
        new LongOperation().execute("loadBalance");
    }

    public void loadBalance() throws Exception {
        Web3j web3 = Web3j.build(new HttpService("http://3.17.172.8:8545"));
        String strAddress =ethereumId.getText().toString();

        EthGetBalance ethGetBalance = web3.ethGetBalance(strAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
        balance = ethGetBalance.getBalance();
        java.math.BigDecimal tokenValue = Convert.fromWei(String.valueOf(balance), Convert.Unit.ETHER);
        strTokenAmount = String.valueOf(tokenValue);

    }

    public void sendGpsLocation(View view) throws Exception {

        latinstr = String.valueOf(findLatitude());
        loninstr = String.valueOf(findLongitude());
        Log.v("Loki",latinstr+loninstr);

        try {
            new LongOperation().execute("mainnetTransaction");
            Toast.makeText(getApplicationContext(),"Location SENT"+"Latitude:" +latinstr+"Longitude:" + loninstr,Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int findLatitude() throws Exception {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },4);
//            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.ACCESS_FINE_LOCATION  },4);
//            Toast.makeText(getApplicationContext(),"Location permission not enabled",Toast.LENGTH_LONG).show();
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        loadBalance();

        return  (int)latitude;

    }


    public int findLongitude(){
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        return (int)longitude ;

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
                        credentials2 = Credentials.create(password);
                        Contract contract = new Contract(web3j, credentials2,gasProvider);
                        hashrec=contract.open("0x8F117C17b29421B338804F9fEE0A2D348B2dAF60",driverID,Integer.parseInt(latinstr),Integer.parseInt(loninstr));


                                loadBalance();


                        Log.v("halwa",hashrec);
                       return  hashrec;


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
                if (result == "ropstenTransaction" || result == "mainnetTransaction" || result == "quorumTransaction" || result == "loadbalance") {
                    accountBalance.setText(strTokenAmount);

                    Toast.makeText(getApplicationContext(), "Location SENT" + "Latitude:" + latinstr + "Longitude:" + loninstr, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
                }
                else {
                    // Contract trasnaction, update response view
                    //response.setText(result);
                    Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();

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
