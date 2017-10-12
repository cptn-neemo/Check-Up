package com.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adefault.checkup.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mCheckPasswordField;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailField = (EditText) findViewById(R.id.SU_email_field);
        mPasswordField = (EditText) findViewById(R.id.SU_password_field);
        mCheckPasswordField = (EditText) findViewById(R.id.SU_password_check);
        mSignUpButton = (Button) findViewById(R.id.SU_signup_button);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();
                String password_check = mCheckPasswordField.getText().toString();

                if (password.equals(password_check)) {
                    //TODO: Add FirebaseAuth
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
