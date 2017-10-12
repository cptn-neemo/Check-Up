package com.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adefault.checkup.R;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLogInButton;
    private TextView mSignUpText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mEmailField = (EditText) findViewById(R.id.LI_email_field);
        mPasswordField = (EditText) findViewById(R.id.LI_password_field);
        mLogInButton = (Button) findViewById(R.id.LI_login_button);
        mSignUpText = (TextView) findViewById(R.id.LI_signup_text);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mSignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add intent to sign up activity
            }
        });
    }
}
