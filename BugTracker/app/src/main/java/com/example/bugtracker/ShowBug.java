package com.example.bugtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShowBug extends AppCompatActivity {

    ListView buglistview;
    ImageView bacc;
    TextView empty;
    Intent bugDetailsI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_bug);
        buglistview = (ListView)findViewById(R.id.buglistview);
        bacc = (ImageView) findViewById(R.id.bacc);
        empty = (TextView) findViewById(R.id.empty);
        bugDetailsI = new Intent(this,BugDetails.class);

        bacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(ShowBug.this);
        List<BugModel> allbugs = databaseHelper2.getBugs();
        ArrayAdapter bugArrAdap = new ArrayAdapter<BugModel>(ShowBug.this, android.R.layout.simple_list_item_1,allbugs);
        buglistview.setAdapter(bugArrAdap);
        if(allbugs.isEmpty())
        {
            empty.setVisibility(View.VISIBLE);
        }

        buglistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomePage.bugobj = (BugModel) parent.getItemAtPosition(position);
                startActivity(bugDetailsI);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(ShowBug.this);
        List<BugModel> allbugs = databaseHelper2.getBugs();
        ArrayAdapter bugArrAdap = new ArrayAdapter<BugModel>(ShowBug.this, android.R.layout.simple_list_item_1,allbugs);
        buglistview.setAdapter(bugArrAdap);
        if(allbugs.isEmpty())
        {
            empty.setVisibility(View.VISIBLE);
        }
    }
}