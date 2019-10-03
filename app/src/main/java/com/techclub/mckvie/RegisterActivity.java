package com.techclub.mckvie;

import android.content.Intent;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private String dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);


        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button_register).setOnClickListener(this);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Department));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    dept = "CSE";
                } else if (i == 1) {
                    dept = "ME";
                } else if (i == 2) {
                    dept = "IT";
                } else if (i == 3) {
                    dept = "ECE";
                } else if (i == 4) {
                    dept = "EE";
                } else if (i == 5) {
                    dept = "AUE";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {

        TextInputLayout editTextName = findViewById(R.id.edit_text_name);
        TextInputLayout editTextEmail = findViewById(R.id.edit_text_email);
        TextInputLayout editTextPassword = findViewById(R.id.edit_text_password);
        TextInputLayout editTextId = findViewById(R.id.edit_text_id);
        TextInputLayout editTextConfirmPassword = findViewById(R.id.edit_text_confirm_password);
        TextInputLayout editTextRoll = findViewById(R.id.edit_text_roll);
        TextInputLayout editTextBatch = findViewById(R.id.edit_text_year);
        TextInputLayout editTextPhn = findViewById(R.id.edit_text_phn);

        final String name = editTextName.getEditText().getText().toString().trim();
        final String email = editTextEmail.getEditText().getText().toString().trim();
        final String password = editTextPassword.getEditText().getText().toString().trim();
        final String id = editTextId.getEditText().getText().toString().trim();
        final String roll = editTextRoll.getEditText().getText().toString().trim();
        final String phn = editTextPhn.getEditText().getText().toString().trim();
        final String batch = editTextBatch.getEditText().getText().toString().trim();
        final String confirm_pass = editTextConfirmPassword.getEditText().getText().toString().trim();
        final String admin = "false";

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        } else {
            editTextName.setError(null);
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            return;
        } else {
            editTextEmail.setError(null);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        } else {
            editTextEmail.setError(null);
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            return;
        } else {
            editTextPassword.setError(null);
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            return;
        } else {
            editTextPassword.setError(null);
        }

        if(!password.equals(confirm_pass)) {
            editTextConfirmPassword.setError("Password doesn't Match");
            return;
        } else {
            editTextConfirmPassword.setError(null);
        }

        if (id.isEmpty()) {
            editTextId.setError(getString(R.string.input_error_phone));
            return;
        } else {
            editTextId.setError(null);
        }

        if (id.length() != 6) {
            editTextId.setError(getString(R.string.input_error_phone_invalid));
            return;
        } else {
            editTextId.setError(null);
        }

        if (roll.isEmpty()) {
            editTextRoll.setError("Roll No. is required");
            return;
        } else {
            editTextRoll.setError(null);
        }

        if (roll.length() != 2) {
            editTextRoll.setError("Enter valid Roll No.");
            return;
        } else {
            editTextRoll.setError(null);
        }

        if (batch.isEmpty()) {
            editTextBatch.setError("Batch Year is required");
            return;
        } else {
            editTextBatch.setError(null);
        }

        if (batch.length() != 4) {
            editTextBatch.setError("Invalid Input");
            return;
        } else {
            editTextBatch.setError(null);
        }

        if (phn.isEmpty()) {
            editTextPhn.setError("Phone No. is required");
            return;
        } else {
            editTextPhn.setError(null);
        }

        if (phn.length() != 10) {
            editTextPhn.setError("Invalid Phone No.");
            return;
        } else {
            editTextPhn.setError(null);
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email,
                                    id,
                                    admin,
                                    dept,
                                    roll,
                                    phn,
                                    batch
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(RegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Oops, something seem to have went wrong", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                registerUser();
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}
