    package com.example.myapplication;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;


    public class MainActivity extends AppCompatActivity {
        public static final String DATABASE_NAME = "Tasks";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setUpListRecyclerView();
            Button addTask=findViewById(R.id.ADD_TASK);
            Intent intent=new Intent(MainActivity.this, AddTask.class);
            addTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });

            Button allTask=findViewById(R.id.ALL_TASK);
            Intent intent1=new Intent(MainActivity.this, AllTasks.class);
            allTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent1);
                }
            });

            Button setting=findViewById(R.id.settingPage);
            Intent intent2=new Intent(MainActivity.this, Settings.class);
            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent2);
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
        private void setUpListRecyclerView() {
            Log.d("abod", "setUpListRecyclerView: ");
            RecyclerView taskListRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            taskListRecyclerView.setLayoutManager(layoutManager);

//            List<Task> tasks = taskDataBase.taskDao().findAll();


//            TaskAdapter adapter = new TaskAdapter(tasks, this);
//            taskListRecyclerView.setAdapter(adapter);
        }

    }