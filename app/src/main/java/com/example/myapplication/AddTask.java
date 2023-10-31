package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

public class AddTask extends AppCompatActivity {
    TaskDataBase taskDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("taskTitle");
        taskDatabase = Room.databaseBuilder(getApplicationContext(), TaskDataBase.class, "Tasks")
                .allowMainThreadQueries()
                .build();
        Button addTaskActivity = (Button) findViewById(R.id.addTaskbtn);
        Toast toast = Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT);
        Spinner taskStateSpinner = (Spinner) findViewById(R.id.spinner);
        taskStateSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Task.State.values()
        ));
        Intent tomainActivity = new Intent(this,MainActivity.class);
        addTaskActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task(
                        ((EditText) findViewById(R.id.Dosomthing)).getText().toString(),
                        ((EditText) findViewById(R.id.Mytask)).getText().toString(),
                        Task.State.fromString(taskStateSpinner.getSelectedItem().toString()));
                taskDatabase.taskDao().insertTask(task);
                toast.show();
                startActivity(tomainActivity);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}