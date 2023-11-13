package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.State;
import com.amplifyframework.datastore.generated.model.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("taskTitle");

        Button addTaskActivity = (Button) findViewById(R.id.addTaskbtn);
        Toast toast = Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT);
        Spinner taskStateSpinner = (Spinner) findViewById(R.id.spinner);
        taskStateSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
         State.values()
        ));
        Intent tomainActivity = new Intent(AddTask.this,MainActivity.class);
        addTaskActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doSome = ((EditText) findViewById(R.id.Dosomthing)).getText().toString();
                String myTask = ((EditText) findViewById(R.id.Mytask)).getText().toString();

                Task task = Task.builder().title(doSome).description(myTask).dateCreated(new Temporal.DateTime(new Date(), 0)).build();

                   Amplify.API.mutate(
                           ModelMutation.create(task),
                           successResponse -> {
                               Log.i("TAG", "Task saved successfully");
                            toast.show();
                            startActivity(tomainActivity);
                           },
                           failureResponse -> {
                               Log.e("TAG", "Failed to save task: " + failureResponse.toString());
                              toast.show();
                           }

                   );
                Log.d("DES",task.getDescription());


                Snackbar.make(findViewById(R.id.Dosomthing), "Product saved!", Snackbar.LENGTH_SHORT).show();

            }
        });}

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (item.getItemId() == android.R.id.home) {
                    onBackPressed();
                    return true;
                }
                return super.onOptionsItemSelected(item);
            }
        }