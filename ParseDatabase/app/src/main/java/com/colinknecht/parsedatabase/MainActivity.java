package com.colinknecht.parsedatabase;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;
import android.content.DialogInterface;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.LogInCallback;
import com.parse.SignUpCallback;
import java.text.ParseException;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    String userName;
    String passWord;

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
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("hk09tKoPysUEoTuZcF0itTXzfIUGweqq7RxQEMbR")
                .clientKey("qY8zQn0mEuesl3eXsP6zfJNN9WgdajCZiS4xUxyb")
                .server("https://parseapi.back4app.com/").build()
        );

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
                userName = etUserName.getText().toString();
                passWord = etPassword.getText().toString();

                ParseUser.logInInBackground(userName, passWord, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            //Login Successful
                            //you can display sth or do sth
                            //For example Welcome + ParseUser.getUsername()
                            Toast.makeText(getApplicationContext(), "Has Joined", Toast.LENGTH_SHORT).show();

                        } else {
                            //Login Fail
                            //get error by calling e.getMessage()
                            Toast.makeText(getApplicationContext(), "User Does Not Exist", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Login Button - onClick done: Login Fail ");
                            e.getMessage();
                        }
                    }

                });
            }//login OnClick
        });//login Listener

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = etUserName.getText().toString();
                passWord = etPassword.getText().toString();

                if (userName.equals("") || passWord.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    ParseUser user = new ParseUser();
                    user.setUsername(userName);
                    user.setPassword(passWord);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            if (e == null) {
                                Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "User Has Already Registered", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "Register - done: User Already Registered");
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }//onClick
        });//btnRegister

//        void parseLogin() {
//            ParseUser.logInInBackground(etUserName.getText().toString(), etPassword.getText().toString(), new LogInCallback() {
//                @Override
//                public void done(ParseUser parseUser, com.parse.ParseException e) {
//                    if (parseUser != null) {
//                        progressDialog.dismiss();
//                        alertDisplayer("Login Successful","Welcome "+parseUser.getUsername());
//                    }
//                    else {
//                        progressDialog.dismiss();
//                        alertDisplayer("Login Failed", e.getMessage()+" Please Try Again");
//                    }
//                }//done
//            });//loginBackground
//        }//parseLogin

//        void alertDisplayer(String title,String message) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
//                    .setTitle(title)
//                    .setMessage(message)
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//            AlertDialog ok = builder.create();
//            ok.show();
//        }//alertDisplayer

    }///////////////////////////////////////////OnCreate


}//MainActivity
