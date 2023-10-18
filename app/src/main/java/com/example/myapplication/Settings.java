package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class Settings extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String userName="username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Button saveButton= findViewById(R.id.save);
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

                Snackbar.make(findViewById(R.id.settings), "Settings Saved", Snackbar.LENGTH_SHORT).show();
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