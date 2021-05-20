package com.example.bugtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AllBug extends AppCompatActivity {

    ListView buglistview;
    ImageView bacc;
    TextView empty;
    Intent bugDetailsI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_bug);
        buglistview = (ListView)findViewById(R.id.buglistview);
        bacc = (ImageView) findViewById(R.id.bacc);
        bugDetailsI = new Intent(this,BugDetails.class);
        empty = (TextView) findViewById(R.id.empty);

        bacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(AllBug.this);
        List<BugModel> allbugs = databaseHelper2.getAllBugs();
        ArrayAdapter bugArrAdap = new ArrayAdapter<BugModel>(AllBug.this, android.R.layout.simple_list_item_1,allbugs);
        buglistview.setAdapter(bugArrAdap);

        if(allbugs.isEmpty())
        {
            empty.setVisibility(View.VISIBLE);
        }

        //CODE WILL SHOW USERS LIST IN ALLBUGS
//        DatabaseHelper databaseHelper = new DatabaseHelper(AllBug.this);
//        List<UserModel> allusers = databaseHelper.getUsers();
//        ArrayAdapter bugArrAdap = new ArrayAdapter<UserModel>(AllBug.this, android.R.layout.simple_list_item_1,allusers);
//        buglistview.setAdapter(bugArrAdap);

        buglistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(Login.usernametxt.equals("admin"))
//                {
//                    BugModel clickedBug = (BugModel) parent.getItemAtPosition(position);
//                    databaseHelper2.deleteBug(clickedBug);
//
//                    DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(AllBug.this);
//                    List<BugModel> allbugs = databaseHelper2.getAllBugs();
//                    ArrayAdapter bugArrAdap = new ArrayAdapter<BugModel>(AllBug.this, android.R.layout.simple_list_item_1,allbugs);
//                    buglistview.setAdapter(bugArrAdap);
//                    if(allbugs.isEmpty())
//                    {
//                        empty.setVisibility(View.VISIBLE);
//                    }
//                }
                HomePage.bugobj = (BugModel) parent.getItemAtPosition(position);
                startActivity(bugDetailsI);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(AllBug.this);
        List<BugModel> allbugs = databaseHelper2.getAllBugs();
        ArrayAdapter bugArrAdap = new ArrayAdapter<BugModel>(AllBug.this, android.R.layout.simple_list_item_1,allbugs);
        buglistview.setAdapter(bugArrAdap);
        if(allbugs.isEmpty())
        {
            empty.setVisibility(View.VISIBLE);
        }
    }
}