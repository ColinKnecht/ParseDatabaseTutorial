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

        progressDialog = new ProgressDialog(MainActivity.this);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MP6H592QYgcXg3SwdqFO1BAUtQz1ukNFSAuKDR2n")
                .clientKey("iL0IbxexpJFKh4Kx3aym8o6fbHrOWxeLGy1vmT7L")
                .server("https://parseapi.back4app.com/").build()
        );

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
                            Log.e(TAG, "buttonLogin - done: Login Fail");
                            e.getMessage();
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
                            Log.e(TAG, "buttonRegister - done: Register Fail");
                            e.getMessage();
                        }
                    }
                });
            }
        });

//        void parseLogin() {
//            ParseUser.logInInBackground(etUserName.getText().toString(), etPassword.getText().toString(), new LogInCallback() {
//                @Override
//                public void done(ParseUser parseUser, com.parse.ParseException e) {
//                    if (parseUser != null) {
//                        progressDialog.dismiss();
//                        //getUserDetailFromParse();
//                    } else {
//                        progressDialog.dismiss();
//                        Log.e(TAG, "parseLogin - done: LoginIn Fail");
//                        //alertDisplayer("Login Fail", e.getMessage()+" Please re-try");
//                    }
//                }
//            });
//        }//parseLogin

//        void getUserDetailFromParse(){
//        ParseUser user = ParseUser.getCurrentUser();
//        tvUserName.setText(user.getUsername());
//        //t_email.setText(user.getEmail());
//        //alertDisplayer("Welcome Back", "User:" + tvUserName.getText().toString());
//
//        }//getUserDetailFromParse

    }//OnCreate


}//MainActivity
