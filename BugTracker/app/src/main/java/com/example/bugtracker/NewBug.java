package com.example.bugtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewBug extends AppCompatActivity {

    int success,priority;
    TextInputLayout bugdesc_lo,steps2rep_lo;
    EditText bugdesc,steps2rep;
    String bd,s2r,creatd;
    RadioButton lowprio,midprio,highprio;
    Button submit,bacc;
    ImageView bac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bug);

        bugdesc_lo = (TextInputLayout)findViewById(R.id.bugdesc_lo);
        steps2rep_lo = (TextInputLayout)findViewById(R.id.steps2rep_lo);
        bugdesc = (EditText) findViewById(R.id.bugdesc);
        steps2rep = (EditText) findViewById(R.id.steps2rep);
        lowprio = (RadioButton) findViewById(R.id.lowprio);
        midprio = (RadioButton) findViewById(R.id.midprio);
        highprio = (RadioButton) findViewById(R.id.highprio);
        submit = (Button) findViewById(R.id.submit);
        bacc = (Button) findViewById(R.id.bacc);
        bac = (ImageView) findViewById(R.id.bac);

        bacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                success = 2;
                bd = bugdesc.getText().toString();
                s2r = steps2rep.getText().toString();

                if(bd.isEmpty())
                {
                    bugdesc_lo.setErrorEnabled(true);
                    bugdesc_lo.setError("Please Enter Bug Description");
                }
                else{
                    success-=1;
                }
                if(s2r.isEmpty())
                {
                    steps2rep_lo.setErrorEnabled(true);
                    steps2rep_lo.setError("Please Enter Bug Description");
                }
                else{
                    success-=1;
                }
                if(success==0)
                {
                    creatd = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
                    if(lowprio.isChecked())
                    {
                        priority=3;
                    }
                    if(midprio.isChecked())
                    {
                        priority=2;
                    }
                    if(highprio.isChecked())
                    {
                        priority=1;
                    }
                    DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(NewBug.this);
                    BugModel bugModel = new BugModel(-1,bd,s2r,priority,Login.usernametxt,creatd,"","",0,"");
                    boolean b = databaseHelper2.addBug(bugModel);
                    if(b == true)
                    {
//                        Toast.makeText(NewBug.this, ""+bugModel.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(NewBug.this, "Bug Reported Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(NewBug.this, "Bug Report Failed", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }

            }
        });

        bugdesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(bugdesc_lo.isErrorEnabled()==true)
                {
                    bugdesc_lo.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        steps2rep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(steps2rep_lo.isErrorEnabled()==true)
                {
                    steps2rep_lo.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}