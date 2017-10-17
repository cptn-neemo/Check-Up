package com.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adefault.checkup.MainActivity;
import com.example.adefault.checkup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mCheckPasswordField;
    private Button mSignUpButton;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailField = (EditText) findViewById(R.id.SU_email_field);
        mPasswordField = (EditText) findViewById(R.id.SU_password_field);
        mCheckPasswordField = (EditText) findViewById(R.id.SU_password_check);
        mSignUpButton = (Button) findViewById(R.id.SU_signup_button);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEmailField.getText()) || TextUtils.isEmpty(mPasswordField.getText())
                        || TextUtils.isEmpty(mCheckPasswordField.getText())) {
                    Toast.makeText(SignUpActivity.this, "Please enter all the required fields.", Toast.LENGTH_LONG);
                }
                else {
                    String email = mEmailField.getText().toString();
                    String password = mPasswordField.getText().toString();
                    String password_check = mCheckPasswordField.getText().toString();

                    if (password.equals(password_check)) {
                        createAccount(email, password);
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void createAccount(String email, String password) {
        mFirebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("createUserEmailPassword", "user created");

                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Problem with creating user",
                                    Toast.LENGTH_LONG).show();
                        }

                        Intent goToMain = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(goToMain);
                    }
                });
    }
}
