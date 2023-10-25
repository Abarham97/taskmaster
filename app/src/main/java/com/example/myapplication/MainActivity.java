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

    import java.util.ArrayList;
    import java.util.List;


    public class MainActivity extends AppCompatActivity {

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

//            Button work=findViewById(R.id.work);
//            String workTitle = work.getText().toString();
//            Intent intent3=new Intent(MainActivity.this, TaskDetail.class);
//            work.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    intent3.putExtra("workTitle", workTitle);
//                    startActivity(intent3);
//                }
//            });
//
//            Button gym=findViewById(R.id.Gym);
//            String gymTitle = gym.getText().toString();
//            Intent intent4=new Intent(MainActivity.this, TaskDetail.class);
//            gym.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    intent4.putExtra("workTitle", gymTitle);
//                    startActivity(intent4);
//                }
//            });
//            Button personal=findViewById(R.id.personal);
//            String personalTitle = gym.getText().toString();
//            Intent intent5=new Intent(MainActivity.this, TaskDetail.class);
//            personal.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    intent5.putExtra("workTitle", personalTitle);
//                    startActivity(intent5);
//                }
//            });


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
            Log.d("SHANAB", "setUpListRecyclerView: ");
            RecyclerView taskListRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            taskListRecyclerView.setLayoutManager(layoutManager);


            List<Task> tasks = new ArrayList<>();

            tasks.add(new Task("Task 1", "Description for Task 1", Task.State.NEW));
            tasks.add(new Task("Task 2", "Description for Task 2", Task.State.ASSIGNED));
            tasks.add(new Task("Task 3", "Description for Task 3", Task.State.IN_PROGRESS));
            tasks.add(new Task("Task 4", "Description for Task 4", Task.State.NEW));
            tasks.add(new Task("Task 5", "Description for Task 5", Task.State.COMPLETE));

            TaskAdapter adapter = new TaskAdapter(tasks, this);
            taskListRecyclerView.setAdapter(adapter);
        }

    }