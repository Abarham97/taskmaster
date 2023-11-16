package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Settings extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private CompletableFuture<List<Team>> teamFuture = new CompletableFuture<>();
    public static final String userName="username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Button saveButton= findViewById(R.id.save);
        Spinner teamsSpinner = (Spinner) findViewById(R.id.spinner2);
        Amplify.API.query(
                ModelQuery.list(Team.class),
                success ->
                {

                    ArrayList<String> teamName = new ArrayList<>();
                    ArrayList<Team> teams = new ArrayList<>();
                    for(Team team: success.getData()){
                        teams.add(team);
                        teamName.add(team.getName());

                    }
                    teamFuture.complete(teams);
                    runOnUiThread(() ->
                    {
                        teamsSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                (android.R.layout.simple_spinner_item),
                                teamName
                        ));
                    });
                },
                failure-> {
                    teamFuture.complete(null);
                    Log.i("SettingsActivity", "Did not read Team successfully");
                }
        );
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toolbar toolbar = findViewById(R.id.toolbar2);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                setTitle("Setting");
                SharedPreferences.Editor preferneceEditor= sharedPreferences.edit();

                EditText userNicknameEditText = (EditText) findViewById(R.id.username);
                String userName = userNicknameEditText.getText().toString();

                preferneceEditor.putString(userName, userName);
                preferneceEditor.apply();

                Snackbar.make(findViewById(R.id.settingsspinner), "Settings Saved", Snackbar.LENGTH_SHORT).show();
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