package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class sighUpActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";

    public static final String SIGNUP_EMAIL_TAG = "Signup_Email_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigh_up);



        Button signupSubmitButton= (Button) findViewById(R.id.signupSubmitButton);
        signupSubmitButton.setOnClickListener(v ->
        {
            String username = ((EditText)findViewById(R.id.signupUsernameEditText)).getText().toString();
            String password = ((EditText)findViewById(R.id.signupPasswordEditText)).getText().toString();

            Amplify.Auth.signUp(username,
                    password,
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.email(), username)
                            .userAttribute(AuthUserAttributeKey.nickname(), "T")
                            .build(),
                    good ->
                    {
                        Log.i(TAG, "Signup succeeded: "+ good.toString());
                        Intent goToVerifyIntent= new Intent(sighUpActivity.this, verifyAccountActivity.class);
                        goToVerifyIntent.putExtra(SIGNUP_EMAIL_TAG, username);
                        startActivity(goToVerifyIntent);
                    },
                    bad ->
                    {
                        Log.i(TAG, "Signup failed with username: "+ username+ " with this message: "+ bad.toString());
                        runOnUiThread(() ->
                        {
                            Toast.makeText(sighUpActivity.this, "Signup failed", Toast.LENGTH_LONG);
                        });
                    }
            );
        });
    }
}