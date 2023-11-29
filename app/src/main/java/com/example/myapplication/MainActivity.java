    package com.example.myapplication;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.amplifyframework.api.graphql.model.ModelMutation;
    import com.amplifyframework.core.Amplify;
    import com.amplifyframework.core.model.temporal.Temporal;
    import com.amplifyframework.datastore.generated.model.Team;

    import java.util.Arrays;
    import java.util.Date;
    import java.util.List;


    public class MainActivity extends AppCompatActivity {
        public static final String DATABASE_NAME = "Tasks";
        public static final String TAG = "MainTaskActivity";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setUpListRecyclerView();

            setUpLoginAndLogoutButton();
            Button addTask=findViewById(R.id.ADD_TASK);
            Intent intent=new Intent(MainActivity.this, AddTask.class);

//            createTeams();
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

        private void createTeams() {
            List<String> teamNames = Arrays.asList("Team1", "Team2", "Team3");

            for (String teamName : teamNames) {
                Team team = Team.builder()
                        .name(teamName)
                        .dateCreated(new Temporal.DateTime(new Date(), 0))
                        .build();

                createTeam(team);
            }
        }

        private void createTeam(Team team) {
            Amplify.API.mutate(
                    ModelMutation.create(team),
                    response -> Log.i("Teams", "createTeam: Created"),
                    fail -> Log.i("Teams", "createTeam: Failed")
            );
        }



        private void setUpLoginAndLogoutButton() {
            Button loginButton = (Button) findViewById(R.id.TaskAppLoginButton);
            loginButton.setOnClickListener(v ->
            {
                Intent goToLogInIntent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(goToLogInIntent);
            });

            Button logoutButton = (Button) findViewById(R.id.TaskAppLogoutButton);
            logoutButton.setOnClickListener(v ->
            {
                Amplify.Auth.signOut(
                        () ->
                        {
                            Log.i(TAG, "Logout succeeded");
                            runOnUiThread(() ->
                            {
//                                ((TextView) findViewById(R.id.textView10)).setText("");
                            });
                            Intent goToLogInIntent = new Intent(MainActivity.this, loginActivity.class);
                            startActivity(goToLogInIntent);
                        },
                        failure ->
                        {
                            Log.i(TAG, "Logout failed");
                            runOnUiThread(() ->
                            {
                                Toast.makeText(MainActivity.this, "Log out failed", Toast.LENGTH_LONG);
                            });
                        }
                );
            });


        }}