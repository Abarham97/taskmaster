package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.Objects;

public class TaskDetail extends AppCompatActivity {
    public static final String TASK_NAME_TAG = "Task Title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        String taskTitle = getIntent().getStringExtra("workTitle");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(taskTitle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Task task = null;
        setTitle(task.getTitle());
        TextView description = (TextView) findViewById(R.id.titleTextView);
        TextView state = (TextView) findViewById(R.id.State);
//        description.setText(task.getBody());
        state.setText(task.getState().toString());


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