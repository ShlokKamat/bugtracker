package com.example.bugtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BugDetails extends AppCompatActivity {

    ImageView bacc,delete;
    TextView bugdesc,steps2rep,priority,reporter,created,status,soln,processor,lastupdated;
    RadioGroup stat;
    RadioButton stat0,stat1;
    LinearLayout resolvedbox,unresolvedbox;
    Button save,cancel,baccc;
    TextInputLayout soln_lo;
    EditText solntxt;
    String lstup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bug_details);
        DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(BugDetails.this);
        stat = (RadioGroup)findViewById(R.id.stat);
        stat0 = (RadioButton)findViewById(R.id.stat0);
        stat1 = (RadioButton)findViewById(R.id.stat1);
        resolvedbox = (LinearLayout)findViewById(R.id.resolvedbox);
        unresolvedbox = (LinearLayout)findViewById(R.id.unresolvedbox);
        delete = (ImageView)findViewById(R.id.delete);
        bacc = (ImageView) findViewById(R.id.bacc);
        bugdesc = (TextView)findViewById(R.id.bugdesc);
        steps2rep = (TextView)findViewById(R.id.steps2rep);
        priority = (TextView)findViewById(R.id.priority);
        reporter = (TextView)findViewById(R.id.reporter);
        created = (TextView)findViewById(R.id.created);
        status = (TextView)findViewById(R.id.status);
        soln = (TextView)findViewById(R.id.soln);
        processor = (TextView)findViewById(R.id.processor);
        lastupdated = (TextView)findViewById(R.id.lastupdated);
//        = (TextView)findViewById();

        soln_lo = (TextInputLayout)findViewById(R.id.soln_lo);
        solntxt = (EditText)findViewById(R.id.solntxt);
        save = (Button)findViewById(R.id.save);
        cancel = (Button)findViewById(R.id.cancel);
        baccc = (Button)findViewById(R.id.baccc);

        if(!Login.usernametxt.equals(HomePage.bugobj.getReporter()))
        {
            delete.setVisibility(View.GONE);
        }
        bugdesc.setText(HomePage.bugobj.getBugDesc());
        steps2rep.setText(HomePage.bugobj.getSteps2rep());
        if(HomePage.bugobj.getPriority()==1)
        {
            priority.setText("High");
        }
        if(HomePage.bugobj.getPriority()==2)
        {
            priority.setText("Medium");
        }
        if(HomePage.bugobj.getPriority()==3)
        {
            priority.setText("Low");
        }
        reporter.setText(HomePage.bugobj.getReporter());
        created.setText(HomePage.bugobj.getCreated());
        if(HomePage.bugobj.getStatus()==0)
        {
            stat0.setChecked(true);
            resolvedbox.setVisibility(View.GONE);
            unresolvedbox.setVisibility(View.GONE);
        }
        else{
            unresolvedbox.setVisibility(View.GONE);
            stat.setVisibility(View.GONE);
            status.setText("Resolved");
            soln.setText(HomePage.bugobj.getSoln());
            processor.setText(HomePage.bugobj.getProcessor());
            lastupdated.setText(HomePage.bugobj.getLastUpdated());
        }
//        reporter.setText(HomePage.bugobj.getReporter());
//        reporter.setText(HomePage.bugobj.getReporter());
//        reporter.setText(HomePage.bugobj.getReporter());
//        reporter.setText(HomePage.bugobj.getReporter());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BugDetails.this);

                builder.setMessage("Delete this bug report?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(BugDetails.this);
                                databaseHelper2.deleteBug(HomePage.bugobj);
                                Toast.makeText(BugDetails.this, "Bug Report Deleted", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        stat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.stat0:
                    {
                        unresolvedbox.setVisibility(View.GONE);
                        break;
                    }
                    case R.id.stat1:
                    {
                        unresolvedbox.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sol;
                sol = solntxt.getText().toString();

                if(sol.isEmpty())
                {
                    soln_lo.setErrorEnabled(true);
                    soln_lo.setError("Please Enter a Solution");
                }
                else{
                    lstup = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
                    HomePage.bugobj.setSoln(sol);
                    HomePage.bugobj.setStatus(1);
                    HomePage.bugobj.setLastUpdated(lstup);
                    HomePage.bugobj.setProcessor(Login.usernametxt);
                    databaseHelper2.updateBug(HomePage.bugobj);
                    Toast.makeText(BugDetails.this, "Bug Resolved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        solntxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(soln_lo.isErrorEnabled()==true)
                {
                    soln_lo.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}