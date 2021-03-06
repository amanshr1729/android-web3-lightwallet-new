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
    EditText password, ethereumId;

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
        result = (TextView) findViewById(R.id.result);
        password = (EditText) findViewById(R.id.password);
        ethereumId = (EditText) findViewById(R.id.ethereumId);
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
                    Credentials credentials = Credentials.create("0x0cd0014ce3d428d895b562899d923c21767b4cc518a7ec1485a90ad01f1e2358");
                    result.setText(credentials.getAddress() + " Loaded successfully");
                    Intent homeIntent = new Intent(MainActivity.this,
                            HomeActivity.class);
                    homeIntent.putExtra("ethereumId", ethereumId.getText().toString());
                    homeIntent.putExtra("password", passwordText);
                    startActivity(homeIntent);
                } catch (Exception e) {
                    result.setText(e.toString());
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fileName = wallet.createWallet();
                    result.setText(fileName);
                } catch (Exception e) {
                    result.setText(e.toString());
                }
            }
        });
    }

    private void loadDefaultWallet() {
        ethereumId.setText("0xd1c6377487fd190c6d2c2a2e2ace5c1e02e4461f ");
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
