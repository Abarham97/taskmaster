package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.State;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TaskDetail extends AppCompatActivity {

    public static final String TAG= "editProductActivity";
    private CompletableFuture<Task> productCompletableFuture=null;
    private CompletableFuture<List<Team>> contactFuture = null;
    private Task productToEdit= null;
    private EditText nameEditText;
    private EditText descriptionEditText;

    private Spinner productCategorySpinner = null;

    private Spinner contactSpinner = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        productCompletableFuture = new CompletableFuture<>();
        contactFuture = new CompletableFuture<>();

        setUpEditableUIElement();
        setUpSaveButton();
        setUpDeleteButton();
    }

    private void setUpEditableUIElement() {
        Intent callingIntent = getIntent();
        String productId = null;

        if(callingIntent != null){
            productId = callingIntent.getStringExtra(AllTasks.PRODUCT_ID_TAG);
        }

        String productId2 = productId;

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success ->
                {
                    Log.i(TAG,"Read products Successfully");

                    for (Task databaseproduct: success.getData()){
                        if(databaseproduct.getId().equals(productId2)){
                            productCompletableFuture.complete(databaseproduct);
                        }
                    }

                    runOnUiThread(() ->
                    {
                        //Update UI element
                    });
                },
                failure -> Log.i(TAG, "Did not read product successfully")
        );

        try {
            productToEdit = productCompletableFuture.get();
        }catch (InterruptedException ie){
            Log.e(TAG, "InterruptedException while getting product");
            Thread.currentThread().interrupt();
        }catch (ExecutionException ee){
            Log.e(TAG, "ExecutionException while getting product");
        }

        nameEditText = ((EditText) findViewById(R.id.editTaskName));
        nameEditText.setText(productToEdit.getTitle());
        descriptionEditText = ((EditText) findViewById(R.id.editDesc));
        descriptionEditText.setText(productToEdit.getDescription());
        setUpSpinners();
    }

    private void setUpSpinners()
    {
        contactSpinner = (Spinner) findViewById(R.id.editTeam);

        Amplify.API.query(
                ModelQuery.list(Team.class),
                success ->
                {
                    Log.i(TAG, "Read contacts successfully!");
                    ArrayList<String> contactNames = new ArrayList<>();
                    ArrayList<Team> contacts = new ArrayList<>();
                    for (Team contact : success.getData())
                    {
                        contacts.add(contact);
                        contactNames.add(contact.getName());
                    }
                    contactFuture.complete(contacts);

                    runOnUiThread(() ->
                    {
                        contactSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                android.R.layout.simple_spinner_item,
                                contactNames));
                        contactSpinner.setSelection(getSpinnerIndex(contactSpinner, productToEdit.getTeamName().getName()));
                    });
                },
                failure -> {
                    contactFuture.complete(null);
                    Log.i(TAG, "Did not read contacts successfully!");
                }
        );

        productCategorySpinner = (Spinner) findViewById(R.id.editTaskCatrgory);
        productCategorySpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                State.values()));
        productCategorySpinner.setSelection(getSpinnerIndex(productCategorySpinner, productToEdit.getState().toString()));
    }

    private int getSpinnerIndex(Spinner spinner, String stringValueToCheck){
        for (int i = 0;i < spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(stringValueToCheck)){
                return i;
            }
        }

        return 0;
    }

    private void setUpSaveButton()
    {
        Button saveButton = (Button)findViewById(R.id.editProductSaveButton);
        saveButton.setOnClickListener(v ->
        {
            List<Team> contacts = null;
            String contactToSaveString = contactSpinner.getSelectedItem().toString();
            try
            {
                contacts = contactFuture.get();
            }
            catch (InterruptedException ie)
            {
                Log.e(TAG, "InterruptedException while getting product");
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException ee)
            {
                Log.e(TAG, "ExecutionException while getting product");
            }
            Team contactToSave = contacts.stream().filter(c -> c.getName().equals(contactToSaveString)).findAny().orElseThrow(RuntimeException::new);
            Task productToSave = Task.builder()
                    .title(nameEditText.getText().toString())
                    .id(productToEdit.getId())
                    .dateCreated(productToEdit.getDateCreated())
                    .description(descriptionEditText.getText().toString())
                    .teamName(contactToSave)
                    .state(productCategoryFromString(productCategorySpinner.getSelectedItem().toString()))
                    .build();

            Amplify.API.mutate(
                    ModelMutation.update(productToSave),  // making a GraphQL request to the cloud
                    successResponse ->
                    {
                        Log.i(TAG, "EditProductActivity.onCreate(): edited a product successfully");
                        // TODO: Display a Snackbar
                        Snackbar.make(findViewById(R.id.editProductAcivity), "Product saved!", Snackbar.LENGTH_SHORT).show();
                    },  // success callback
                    failureResponse -> Log.i(TAG, "EditProductActivity.onCreate(): failed with this response: " + failureResponse)  // failure callback
            );
        });
    }

    public static State productCategoryFromString(String inputProductCategoryText){
        for (State productCategory : State.values()){
            if(productCategory.toString().equals(inputProductCategoryText)){
                return productCategory;
            }
        }
        return null;
    }

    private void setUpDeleteButton(){
        Button deleteButton = (Button) findViewById(R.id.editProductDeleteButton);
        deleteButton.setOnClickListener(v ->{
            Amplify.API.mutate(
                    ModelMutation.delete(productToEdit),
                    successResponse ->
                    {
                        Log.i(TAG, "EditProductActivity.onCreate(): deleted a product successfully");
                        Intent goToProductListActivity = new Intent(TaskDetail.this, AllTasks.class);
                        startActivity(goToProductListActivity);
                    },
                    failureResponse -> Log.i(TAG,"EditProductActivity.onCreate(): failed with this response: "+ failureResponse)
            );
        });
    }
}