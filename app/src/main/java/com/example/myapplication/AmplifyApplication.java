package com.example.myapplication;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;

public class AmplifyApplication extends Application {

    public static final String TAG= "buystuffapplication";
    @Override
    public void onCreate() {
        super.onCreate();

        try{
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
        }catch (AmplifyException ae){
            Log.e("ampl", "Error initializing Amplify" + ae.getMessage(), ae);
        }
    }
}
