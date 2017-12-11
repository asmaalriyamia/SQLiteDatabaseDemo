package com.example.sasmob.sqlitedatabasedemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.sasmob.sqlitedatabasedemo.database_module.DatabaseHelperClass;
import com.example.sasmob.sqlitedatabasedemo.database_module.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText name , department;
String name1, department1;
ListView listView;
    Student student;
    //ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // lst = (ListView)findViewById(R.id.list_information);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void save(View view) {
     name = (EditText) findViewById(R.id.name);
     department = (EditText) findViewById(R.id.department);
     name1= name.getText().toString();
     department1 = department.getText().toString();
       student = new Student();
        student.setName(name1);
        student.setDepartment(department1);
        DatabaseHelperClass myhelper = new DatabaseHelperClass(this);
        long l = myhelper.InsertStudentRecord(student);
        if (l>0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Sorry Not inserted", Toast.LENGTH_SHORT).show();
        }


    }

    public void display(View view) {
        listView = (ListView) findViewById(R.id.list_information);
        DatabaseHelperClass helper = new DatabaseHelperClass(this);
        Cursor c=helper.getAllStudents();
        String[] columnName = {"_id","name","department"};
        int[] to= {R.id._id,R.id.nameStudent, R.id.departmentStudent};
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,R.layout.card_view,c,columnName,to);
        listView.setAdapter(adapter);


    }

    public void delete(View view) {

    }

    public void update(View view) {
        
    }
}
