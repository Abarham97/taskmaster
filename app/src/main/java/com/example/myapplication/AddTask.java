package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.State;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTask extends AppCompatActivity {
    CompletableFuture<List<Team>> teamFuture = new CompletableFuture<>();
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private ImageView selectedImageView;
    private String filePath;

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
                Spinner states=  findViewById(R.id.spinner);
//                Team team= (Team) ((Spinner) findViewById(R.id.TeamSpinner)).getSelectedItem();
                Spinner team =findViewById(R.id.TeamSpinner);
                List<Team> contacts= null;
                try {
                    contacts=teamFuture.get();
                }catch (InterruptedException ie){
                    Log.e("TAG", " InterruptedException while getting contacts");
                }catch (ExecutionException ee){
                    Log.e("TAG"," ExecutionException while getting contacts");
                }

                String selectedContactString = team.getSelectedItem().toString();
                Team selectedContact = contacts.stream().filter(c -> c.getName().equals(selectedContactString)).findAny().orElseThrow(RuntimeException::new);

                Log.d("team", team.getSelectedItem().toString());
                Task task = Task.builder().title(doSome).description(myTask).dateCreated(new Temporal.DateTime(new Date(), 0))
                        .state((State) states.getSelectedItem()).teamName(selectedContact).build();
                Log.d("task", task.toString());

                   Amplify.API.mutate(
                           ModelMutation.create(task),
                           successResponse -> {
                               Log.i("ISma", "Task saved successfully");
                            toast.show();
                            startActivity(tomainActivity);
                           },
                           failureResponse -> {
                               Log.e("ISma", "Failed to save task: " + failureResponse.toString());
                              toast.show();
                           }

                   );
                Log.d("DES",task.getDescription());


                Snackbar.make(findViewById(R.id.Dosomthing), "Product saved!", Snackbar.LENGTH_SHORT).show();

            }
        });
        Spinner teamsSpinner = (Spinner) findViewById(R.id.TeamSpinner);
        Amplify.API.query(
                ModelQuery.list(Team.class),
                success ->
                {
                    Log.i("TAG", "TeamADDED");
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
                    Log.i("TAG", "Did not read Teams successfully");
                }
        );

        selectedImageView = findViewById(R.id.pic);

        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(),
                uri -> {
                    if (uri != null) {
                        try {
                            selectedImageView.setImageURI(uri);
                            filePath = getRealPathFromURI(uri);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("PHTACT", "Error setting image URI: " + e.getMessage());
                        }
                    } else {
                        Log.d("PHTACT", "No media selected");
                        filePath = null;
                    }
                });


    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Intent callingIntent = getIntent();
        if (callingIntent != null && callingIntent.getType() != null && callingIntent.getType().equals("text/plain")) {
            String callingText = callingIntent.getStringExtra(Intent.EXTRA_TEXT);

            if (callingText != null) {

                String cleanedText = cleanText(callingText);


                ((EditText) findViewById(R.id.addProductProductNameEditTExt)).setText(cleanedText);
            }
        }

        if(callingIntent != null && callingIntent.getType() != null && callingIntent.getType().startsWith("image") ){
            Uri incomingImageFileUri= callingIntent.getParcelableExtra(Intent.EXTRA_STREAM);

            if (incomingImageFileUri != null){
                InputStream incomingImageFileInputStream = null;

                try {
                    incomingImageFileInputStream = getContentResolver().openInputStream(incomingImageFileUri);

                    ImageView productImageView = findViewById(R.id.imageView);

                    if (productImageView != null) {

                        productImageView.setImageBitmap(BitmapFactory.decodeStream(incomingImageFileInputStream));
                    }else {
                        Log.e("TAG", "ImageView is null for some reasons");
                    }
                }catch (FileNotFoundException fnfe){
                    Log.e("TAG"," Could not get file stram from the URI "+fnfe.getMessage(),fnfe);
                }
            }
        }

    }


    public void onAddImageButtonClicked(View view) {
        if (pickMedia != null) {
            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        } else {
            Log.e("PhotoPicker", "pickMedia is null");
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            return filePath;
        }
    }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (item.getItemId() == android.R.id.home) {
                    onBackPressed();
                    return true;
                }
                return super.onOptionsItemSelected(item);
            }
    private String cleanText(String text) {

        text = text.replaceAll("\\b(?:https?|ftp):\\/\\/\\S+\\b", "");


        text = text.replaceAll("\"", "");

        return text;
    }
        }