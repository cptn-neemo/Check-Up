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
import android.widget.TextView;
import android.widget.Toast;

import com.example.adefault.checkup.MainActivity;
import com.example.adefault.checkup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLogInButton;
    private TextView mSignUpText;

    private FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mFireBaseAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mEmailField = (EditText) findViewById(R.id.LI_email_field);
        mPasswordField = (EditText) findViewById(R.id.LI_password_field);
        mLogInButton = (Button) findViewById(R.id.LI_login_button);
        mSignUpText = (TextView) findViewById(R.id.LI_signup_text);

        mFireBaseAuth = FirebaseAuth.getInstance();

        mFireBaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user  = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Intent goToMain = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(goToMain);
                }

            }
        };

        mSignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUp = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(goToSignUp);
            }
        });

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LogInActivity.this, "Please enter all the required fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    signIn(username, password);
                }
            }
        });

    }

    private void signIn(String email, String password) {
       mFireBaseAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Task: ", "successful");
                       Intent goToMain = new Intent(LogInActivity.this, MainActivity.class);
                       startActivity(goToMain);

                       if (!task.isComplete()) {
                           Log.d("Task", "Not successful");
                       }
                   }
               });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mFireBaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFireBaseAuth.removeAuthStateListener(mFireBaseAuthListener);
    }
}
