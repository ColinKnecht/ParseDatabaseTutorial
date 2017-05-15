package com.colinknecht.parsedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.app.ProgressDialog;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.LogInCallback;
import com.parse.SignUpCallback;
import java.text.ParseException;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    EditText etUserName;
    EditText etPassword;

    Button btnLogin;
    Button btnRegister;

    TextView tvUserName;
    TextView tvPassword;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.editTextUsername);
        etPassword = (EditText) findViewById(R.id.editTextPassword);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnRegister = (Button) findViewById(R.id.buttonRegister);

        tvUserName = (TextView) findViewById(R.id.textViewUserName);
        tvPassword = (TextView) findViewById(R.id.textViewPassword);

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground("Username", "Password", new LogInCallback() {
                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            //Login Successful
                            //you can display sth or do sth
                            //For example Welcome + ParseUser.getUsername()

                        } else {
                            //Login Fail
                            //get error by calling e.getMessage()
                        }
                    }

                });
            }//login OnClick
        });//login Listener

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(etUserName.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            //Register Successful
                            //you can display sth or do sth
                        } else {
                            //Register Fail
                            //get error by calling e.getMessage()
                        }
                    }
                });
            }
        });

    }//OnCreate


}//MainActivity
