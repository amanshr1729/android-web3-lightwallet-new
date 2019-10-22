package com.persistent.subhod_i.digitallocker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import org.web3j.crypto.Credentials;

public class MainActivity extends AppCompatActivity {
    Wallet wallet = new Wallet();
    Button login, create;
    TextView result;
    EditText password, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Welcome to Ethereum-mobile-wallet", Toast.LENGTH_LONG).show();
        RegisterView();
        loadDefaultWallet();
        checkPermissions();
        addEventListners();
    }

    private void RegisterView() {
        login = (Button) findViewById(R.id.login);
        create = (Button) findViewById(R.id.create);
        //result = (TextView) findViewById(R.id.result);
        password = (EditText) findViewById(R.id.password);
        username = (EditText) findViewById(R.id.username);
    }

    private void addEventListners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String passwordText = password.getText().toString();
                    //Credentials credentials = wallet.loadCredentials(passwordText);
                    //result.setText(credentials.getAddress() + " Loaded successfully");
                    //ethereumId.setText(credentials.getAddress());
                    //Credentials credentials = Credentials.create("0x0cd0014ce3d428d895b562899d923c21767b4cc518a7ec1485a90ad01f1e2358");
                    //result.setText(credentials.getAddress() + " Loaded successfully");

                    String usr=username.getText().toString();
                    String pass=password.getText().toString();

                    String publicKey="";
                    String privateKey="";
                    int driverID=0;

                    if(usr!=null&&pass!=null)
                    {
                        if(usr.equals("driver1")&&pass.equals("pass1"))
                        {
                            publicKey="0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f";
                            privateKey="0x0cd0014ce3d428d895b562899d923c21767b4cc518a7ec1485a90ad01f1e2358";
                            driverID=1;

                        }
                        else if(usr.equals("driver2")&&pass.equals("pass2"))
                        {
                            publicKey="0x0bdf6a6d62d0340715b914802b7a38afa1501622";
                            privateKey="0x2aae8e26f15a28d47b74e830187310261b7c4e2ebb295d21c55541a0807e9b7e";
                            driverID=2;
                        }
                        else if(usr.equals("driver3")&&pass.equals("pass3"))
                        {
                            publicKey="0x8b9ac6a5b4c5b643f30bacf30f37404f56308a36";
                            privateKey="0xc411da6337dbc1f54bd970b7d976970f7d14d0dd87de0d22957f4814e310ae56";
                            driverID=3;
                        }
                        else if(usr.equals("driver4")&&pass.equals("pass4"))
                        {
                            publicKey="0xce137173e79db41377f43528524037aacdb92d71";
                            privateKey="0x4331743d5a4e21f615942d2419d40d5a76bfa36f77289817b71c21ab9e8fe737";
                            driverID=4;
                        }

                        Intent homeIntent = new Intent(MainActivity.this,
                                HomeActivity.class);
                        homeIntent.putExtra("username", publicKey);
                        homeIntent.putExtra("password", privateKey);
                        homeIntent.putExtra("driverid", driverID);
                        startActivity(homeIntent);
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG);
                    //result.setText(e.toString());
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fileName = wallet.createWallet();
                   //result.setText(fileName);
                    Toast.makeText(getApplicationContext(),fileName,Toast.LENGTH_LONG);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG);
                }
            }
        });
    }

    private void loadDefaultWallet() {
       // ethereumId.setText("0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f ");
    }

    private void checkPermissions() {
        String TAG = "Permisiion";
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted1");
            } else {
                Log.v(TAG, "Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted1");
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted2");
            } else {
                Log.v(TAG, "Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted2");
        }
    }
}
