package com.example.bugtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    public static BugModel bugobj;
    Intent gotoLogin;
    int back_count=0;
    TextView welcome;
    Button newbug,showbug,mybug,resbug,allbug;
    Intent newbugI,showbugI,mybugI,resbugI,allbugI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText("Welcome "+Login.usernametxt+",");
        newbug = (Button)findViewById(R.id.newbug);
        newbugI = new Intent(this,NewBug.class);

        showbug = (Button) findViewById(R.id.showbug);
        showbugI = new Intent(this,ShowBug.class);

        mybug = (Button)findViewById(R.id.mybug);
        mybugI = new Intent(this,MyBug.class);

        resbug = (Button)findViewById(R.id.resbug);
        resbugI = new Intent(this,ResBug.class);

        allbug = (Button)findViewById(R.id.allbug);
        allbugI = new Intent(this,AllBug.class);

        gotoLogin = new Intent(this,Login.class);

        newbug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_count=0;
                startActivity(newbugI);
            }
        });

        showbug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_count=0;
                startActivity(showbugI);
            }
        });

        mybug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_count=0;
                startActivity(mybugI);
            }
        });

        resbug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_count=0;
                startActivity(resbugI);
            }
        });

        allbug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_count=0;
                startActivity(allbugI);
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
            if(back_count==0)
            {
                Toast.makeText(this, "Press again to log out", Toast.LENGTH_SHORT).show();
                back_count++;
                return true;
            }
            if(back_count==1)
            {
                back_count=0;
                startActivity(gotoLogin);
                finish();
//                return super.onKeyDown(keyCode,event);
            }
        }
        return true;
    }
}