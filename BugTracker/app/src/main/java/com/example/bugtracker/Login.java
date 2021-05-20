package com.example.bugtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Login extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    int success;
    TextView signuphere;
    EditText username;
    TextInputEditText password;
    TextInputLayout usernamelo,passwordlo;
    public static String usernametxt,passwordtxt;
    Button login;
    Intent login_success,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Hard-coding "admin" user
        databaseHelper = new DatabaseHelper(Login.this);
        UserModel userModel = new UserModel("admin","admin");
        boolean userCreated = databaseHelper.addUser(userModel);
        //Getting all users
        List<UserModel> userList = databaseHelper.getUsers();

        username=(EditText)findViewById(R.id.username);
        usernamelo=(TextInputLayout)findViewById(R.id.username_lo);
        password=(TextInputEditText) findViewById(R.id.password);
        passwordlo=(TextInputLayout)findViewById(R.id.password_lo);
        login=(Button)findViewById(R.id.login_b);
        login_success = new Intent(this,HomePage.class);

        signuphere=(TextView)findViewById(R.id.signuphere);
        signup = new Intent(this,SignUp.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                success=2;
                usernametxt=username.getText().toString();
                passwordtxt=password.getText().toString();
                if(usernametxt.isEmpty())
                {
                    usernamelo.setErrorEnabled(true);
                    usernamelo.setError("Please Enter Username");
                }
                else if(usernametxt.length()<5)
                {
                    usernamelo.setErrorEnabled(true);
                    usernamelo.setError("Username must be >4 characters");
                }
                else{
                    success-=1;
                }
                if(passwordtxt.isEmpty())
                {
                    passwordlo.setErrorEnabled(true);
                    passwordlo.setError("Please Enter Password");
                }
                else if(passwordtxt.length()<5)
                {
                    passwordlo.setErrorEnabled(true);
                    passwordlo.setError("Password must be >4 characters");
                }
                else{
                    success-=1;
                }
                if(success==0)
                {
                    int x=userList.size();
                    int i=0;
                    int flag=0;
                    while(x>0)
                    {
                        if(userList.get(i).toString().equals("UserModel{Username='"+usernametxt+"', Password='"+passwordtxt+"'}"))
                        {
                            flag=1;
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                            break;
                        }
                        i++;
                        x--;
                    }
                    if(flag==0)
                    {
                        Toast.makeText(Login.this, "User doesn't exist/Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        password.setText("");
                        startActivity(login_success);
                        finish();
                    }
                }
//                startActivity(login_success);
//                finish();
            }

        });

        signuphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signup);
                finish();
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(usernamelo.isErrorEnabled()==true)
                {
                    usernamelo.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passwordlo.isErrorEnabled()==true)
                {
                    passwordlo.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}